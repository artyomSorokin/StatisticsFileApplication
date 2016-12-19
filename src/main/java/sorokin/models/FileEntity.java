package sorokin.models;


import sorokin.util.StatisticsLineUtil;

import java.util.ArrayList;
import java.util.List;

public class FileEntity {

    private String absolutePath;
    private String query;
    private List<LineEntity> entityList;
    private int fileLength;
    private String fileLongestWord;
    private String fileShortestWordFile;
    private int fileAverageWordLength;
    private int countLine;

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<LineEntity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<LineEntity> entityList) {
        this.entityList = entityList;
    }

    public int getFileLength() {
        return fileLength;
    }

    public void setFileLength(int fileLength) {
        this.fileLength = fileLength;
    }

    public String getFileLongestWord() {
        return fileLongestWord;
    }

    public void setFileLongestWord(String fileLongestWord) {
        this.fileLongestWord = fileLongestWord;
    }

    public String getFileShortestWordFile() {
        return fileShortestWordFile;
    }

    public void setFileShortestWordFile(String fileShortestWordFile) {
        this.fileShortestWordFile = fileShortestWordFile;
    }

    public int getFileAverageWordLength() {
        return fileAverageWordLength;
    }

    public void setFileAverageWordLength(int fileAverageWordLength) {
        this.fileAverageWordLength = fileAverageWordLength;
    }

    public int getCountLine() {
        return countLine;
    }

    public void setCountLine(int countLine) {
        this.countLine = countLine;
    }

    public int findFileLength(){
        List<LineEntity> lineEntities = this.getEntityList();
        int sum = 0;
        for(LineEntity lineEntity : lineEntities){
            sum = sum + lineEntity.getLineLength();
        }
        return sum;
    }

    public String findFileLongestWord(){
        List<LineEntity> lineEntities = this.getEntityList();
        List<String> listWord = new ArrayList<>();
        for(LineEntity lineEntity : lineEntities){
            listWord.add(lineEntity.getLongestWord());
        }
        String longestWord = StatisticsLineUtil.findLongest(listWord);
        return longestWord;
    }

    public String findFileShortestWord(){
        List<LineEntity> lineEntities = this.getEntityList();
        List<String> listWord = new ArrayList<>();
        for(LineEntity lineEntity : lineEntities){
            listWord.add(lineEntity.getShortestWord());
        }
        String shortestWord = StatisticsLineUtil.findShortest(listWord);
        return shortestWord;
    }

    public int findFileAverageWordLength(){
        List<LineEntity> lineEntities = this.getEntityList();
        int count = 0;
        int sum = 0;
        for(LineEntity lineEntity : lineEntities){
            sum = sum + lineEntity.getAverageWordLength();
            count++;
        }
        int result = sum/count;
        return result;
    }
}
