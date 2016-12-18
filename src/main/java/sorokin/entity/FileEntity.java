package sorokin.entity;


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
}
