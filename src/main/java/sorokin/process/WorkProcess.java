package sorokin.process;

import org.apache.log4j.Logger;
import sorokin.dao.linestatisticsdao.FileDao;
import sorokin.dao.linestatisticsdao.FileDaoImpl;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class WorkProcess {

    private final static FileDao FILE_DAO = new FileDaoImpl();
    private Logger log = Logger.getLogger(WorkProcess.class);

    private void prepareDb(){
        log.info("Start check or create database");
        FILE_DAO.createTableLineTable();
        FILE_DAO.createTableFileTable();
        FILE_DAO.clearDb();
    }

    public String handleTextFromUser(String text){
        log.info("Write user text into file");
        File checkFile = new File("D:\\UsersFile\\fileName.txt");
        if(checkFile.exists()){
            checkFile.delete();
        }
        File newFile = new File("D:\\UsersFile\\fileName.txt");
        String absolutePath = newFile.getAbsolutePath();
        try(FileOutputStream fos = new FileOutputStream(absolutePath)) {
            byte bytes[] = text.getBytes("UTF-8");
            fos.write(bytes);
            fos.close();
        } catch (Exception e) {
            log.error("Problem with write user text into file");
            log.error(e.getMessage());
        }
        return absolutePath;
    }

    public void start(String path){
        log.info("Start handle specified path of directory or file");
        prepareDb();
        List<String> emptyFileList = new ArrayList<>();
        List<String> fileList = WorkWithFileDirectory.getFileTxtList(path, emptyFileList);
        ExecutorService service = Executors.newFixedThreadPool(10);
        for(String filePath : fileList){
            service.submit(new FileHandler(filePath));
        }
        service.shutdown();
    }

}
