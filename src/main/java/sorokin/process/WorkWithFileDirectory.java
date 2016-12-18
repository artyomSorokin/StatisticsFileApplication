package sorokin.process;


import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;

public class WorkWithFileDirectory {

    private static Logger log = Logger.getLogger(WorkWithFileDirectory.class);

    public static List<String> getFileTxtList(String path, List<String> list) {
        log.info("Start scan path of directory or file");
        if (path != null && !path.equals("")) {
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            } else if (dir.isDirectory()) {
                File[] files = dir.listFiles();

                for (File file : files) {
                    System.out.println(file.getAbsolutePath());
                    if (file.isFile()) {
                        if (file.getName().endsWith(".txt")) {
                            list.add(file.getAbsolutePath());
                        }
                    } else if (file.isDirectory()) {
                        getFileTxtList(file.getAbsolutePath(), list);
                    }
                }
            } else if (dir.isFile()) {
                if (dir.getName().endsWith(".txt")) {
                    list.add(dir.getAbsolutePath());
                }
            }
        }
        return list;
    }
}