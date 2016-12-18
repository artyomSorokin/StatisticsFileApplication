package sorokin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "lineStatistics")
public class StatisticsLineEntity implements Serializable{

    private long id;
    private String fileName;
    private String query;
    private String line;
    private int lineLength;
    private String longestWord;
    private String shortestWord;
    private int averageWordLength;

    @Id()
    @Column(name="ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Column(name="FILENAME")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    @Column(name="QUERY")
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Column(name="LINE")
    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
    @Column(name="LINELENGTH")
    public int getLineLength() {
        return lineLength;
    }

    public void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }
    @Column(name="LONGESTWORD")
    public String getLongestWord() {
        return longestWord;
    }

    public void setLongestWord(String longestWord) {
        this.longestWord = longestWord;
    }
    @Column(name="SHORTESTWORD")
    public String getShortestWord() {
        return shortestWord;
    }

    public void setShortestWord(String shortestWord) {
        this.shortestWord = shortestWord;
    }
    @Column(name="AVERAGEWORDLENGTH")
    public int getAverageWordLength() {
        return averageWordLength;
    }

    public void setAverageWordLength(int averageWordLength) {
        this.averageWordLength = averageWordLength;
    }

    @Override
    public String toString() {
        return "StaticFileEntity{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", line='" + line + '\'' +
                ", lineLength=" + lineLength +
                ", longestWord='" + longestWord + '\'' +
                ", shortestWord='" + shortestWord + '\'' +
                ", averageWordLength=" + averageWordLength +
                '}';
    }
}
