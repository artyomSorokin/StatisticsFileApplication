package sorokin.process;

import org.apache.log4j.Logger;
import sorokin.dao.linestatisticsdao.FileDao;
import sorokin.dao.linestatisticsdao.FileDaoImpl;
import sorokin.entity.FileEntity;
import sorokin.entity.LineEntity;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class FileHandler implements Runnable{


    private LineEntity lineEntity;
    private List<LineEntity> lineEntities;
    private final static FileDao FILE_DAO = new FileDaoImpl();
    private final String PATH;
    private Logger log = Logger.getLogger(FileHandler.class);

    public FileHandler(String PATH) {
        this.PATH = PATH;
    }

    private Map<String, List<String>> readLineFromFile(String path){
        log.info("Read lines from file");
        Map<String, List<String>> fileMap = new HashMap<>();
        try(InputStream input = new FileInputStream(path);
        Scanner scanner = new Scanner(input, "WINDOWS-1251")) {
            List<String> lineList = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.length() != 0) {
                    lineList.add(line);
                }
            }
            fileMap.put(path, lineList);
        }
        catch(Exception e){
            log.error("Problem with read file");
        }
        return fileMap;
    }

    private void createFileEntity(Map<String, List<String>> fileList) {
        log.info("Create FileEntity");
        for (Map.Entry<String, List<String>> entry : fileList.entrySet()) {
            FileEntity fileEntity = new FileEntity();
            List<LineEntity> entities = createListLineEntity(entry.getValue());
            fileEntity.setAbsolutePath(entry.getKey());
            String query = entry.getKey().replaceAll("[\\p{Punct}\\s]+", "_");
            fileEntity.setQuery(query);
            fileEntity.setEntityList(entities);
            fileEntity.setCountLine(entry.getValue().size());
            fileEntity.setFileLength(StatisticsFile.findFileLength(fileEntity));
            fileEntity.setFileLongestWord(StatisticsFile.findFileLongestWord(fileEntity));
            fileEntity.setFileShortestWordFile(StatisticsFile.findFileShortestWord(fileEntity));
            fileEntity.setFileAverageWordLength(StatisticsFile.findFileAverageWordLength(fileEntity));
            writeIntoDb(fileEntity);
        }
    }

    private List<LineEntity> createListLineEntity(List<String> lines){
        log.info("Create list of lineEntity");
        Map<String, List<String>> splitLineMap = splitLine(lines);
        List<LineEntity> entities = getLineEntitiesList(splitLineMap);
        return entities;
    }

    private Map<String, List<String>> splitLine(List<String> lineList){
        log.info("Split Line on words");
        Map<String, List<String>> wordMap = new HashMap<String, List<String>>();
        for(String line : lineList) {
            List<String> wordList = new ArrayList<String>();
            String[] wordArray = line.split("[\\p{Punct}\\s]+");
            for (String word : wordArray) {
                if (word.length() != 0)
                    wordList.add(word.trim());
            }
            wordMap.put(line, wordList);
        }
        return wordMap;
    }

    private List<LineEntity> getLineEntitiesList(Map<String, List<String>> wordMap) {
        log.info("Get list of LineEntity");
        lineEntities = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry : wordMap.entrySet()) {
            String longestWord = StatisticsLine.findLongest(entry.getValue());
            String shortestWord = StatisticsLine.findShortest(entry.getValue());
            int averageLength = StatisticsLine.averageLength(entry.getValue());
            lineEntity = createLineEntity(entry.getKey(), entry.getKey().length(), longestWord, shortestWord, averageLength);
            lineEntities.add(lineEntity);
        }
        return lineEntities;

    }

    private LineEntity createLineEntity(String line, int lineLength, String longestWord, String shortestWord, int averageWordLength){
        log.info("Create LineEntity");
        lineEntity = new LineEntity();
        lineEntity.setLine(line);
        lineEntity.setLineLength(lineLength);
        lineEntity.setLongestWord(longestWord);
        lineEntity.setShortestWord(shortestWord);
        lineEntity.setAverageWordLength(averageWordLength);
        return lineEntity;
    }

    private void writeIntoDb(FileEntity fileEntity){
        log.info("Write FileEntity into database");
        List<LineEntity> lineEntities = fileEntity.getEntityList();
        FILE_DAO.insertIntoLineTable(fileEntity.getAbsolutePath(),fileEntity.getQuery(), lineEntities);
        FILE_DAO.insertIntoFileTable(fileEntity);
    }

    @Override
    public void run() {
        log.info("Start handle file");
        Map<String, List<String>> fileMap = readLineFromFile(PATH);
        createFileEntity(fileMap);
    }
}
