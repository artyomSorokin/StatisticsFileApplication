package process;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sorokin.entity.FileEntity;
import sorokin.entity.LineEntity;
import sorokin.process.StatisticsFile;

import java.util.ArrayList;
import java.util.List;

public class StatisticsFileTest {

    private FileEntity fileEntity;
    @Before
    public void setUp(){
        List<LineEntity> lineEntityList = new ArrayList<>();
        LineEntity lineEntity1 = new LineEntity();
        String line1 = "The grass is always greener on the other side (of the fence)";
        lineEntity1.setLine(line1);
        lineEntity1.setLineLength(line1.length());
        lineEntity1.setLongestWord("greener");
        lineEntity1.setShortestWord("is");
        lineEntity1.setAverageWordLength(3);
        lineEntityList.add(lineEntity1);

        LineEntity lineEntity2 = new LineEntity();
        String line2 = "A leopard cannot change its spots";
        lineEntity2.setLine(line2);
        lineEntity2.setLineLength(line2.length());
        lineEntity2.setLongestWord("leopard");
        lineEntity2.setShortestWord("A");
        lineEntity2.setAverageWordLength(4);
        lineEntityList.add(lineEntity2);

        LineEntity lineEntity3 = new LineEntity();
        String line3 = "To know where the bodies are buried";
        lineEntity3.setLine(line3);
        lineEntity3.setLineLength(line3.length());
        lineEntity3.setLongestWord("bodies");
        lineEntity3.setShortestWord("To");
        lineEntity3.setAverageWordLength(4);
        lineEntityList.add(lineEntity3);

        fileEntity = new FileEntity();
        fileEntity.setEntityList(lineEntityList);
    }

    @Test
    public void findFileLengthNormalWorkTest(){
        int result = StatisticsFile.findFileLength(fileEntity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, 128);
    }

    @Test(expected = NullPointerException.class)
    public void findFileLengthNullTest(){
        int result = StatisticsFile.findFileLength(null);
    }

    @Test(expected = NullPointerException.class)
    public void findFileLengthEmptyFileEntityTest(){
        int result = StatisticsFile.findFileLength(new FileEntity());
    }

    @Test
    public void findFileLengthEmptyListTest(){
        FileEntity fileEntity = new FileEntity();
        fileEntity.setEntityList(new ArrayList<LineEntity>());
        int result = StatisticsFile.findFileLength(fileEntity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, 0);
    }

    @Test
    public void findFileLongestWordNormalWorkTest(){
        String longestWord = StatisticsFile.findFileLongestWord(fileEntity);
        Assert.assertNotNull(longestWord);
        Assert.assertEquals(longestWord, "greener");
    }

    @Test(expected = NullPointerException.class)
    public void findFileLongestWordNullTest(){
        String longestWord = StatisticsFile.findFileLongestWord(null);
    }

    @Test(expected = NullPointerException.class)
    public void findFileLongestWordEmptyFileEntityTest(){
        String longestWord = StatisticsFile.findFileLongestWord(new FileEntity());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void findFileLongestWordEmptyListTest(){
        FileEntity fileEntity = new FileEntity();
        fileEntity.setEntityList(new ArrayList<LineEntity>());
        String longestWord = StatisticsFile.findFileLongestWord(fileEntity);
    }

    @Test
    public void findFileShortestWordNormalWorkTest(){
        String longestWord = StatisticsFile.findFileShortestWord(fileEntity);
        Assert.assertNotNull(longestWord);
        Assert.assertEquals(longestWord, "A");
    }

    @Test(expected = NullPointerException.class)
    public void findFileShortestWordNullTest(){
        String longestWord = StatisticsFile.findFileShortestWord(null);
    }

    @Test(expected = NullPointerException.class)
    public void findFileShortestWordEmptyFileEntityTest(){
        String longestWord = StatisticsFile.findFileShortestWord(new FileEntity());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void findFileShortestWordEmptyListTest(){
        FileEntity fileEntity = new FileEntity();
        fileEntity.setEntityList(new ArrayList<LineEntity>());
        String longestWord = StatisticsFile.findFileShortestWord(fileEntity);
    }

    @Test
    public void findFileAverageWordLengthNormalWorkTest(){
        int result = StatisticsFile.findFileAverageWordLength(fileEntity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, 3);
    }

    @Test(expected = NullPointerException.class)
    public void findFileAverageWordNullTest(){
        int result = StatisticsFile.findFileAverageWordLength(null);
    }

    @Test(expected = NullPointerException.class)
    public void findFileAverageWordEmptyFileEntityTest(){
        int result = StatisticsFile.findFileAverageWordLength(new FileEntity());
    }

    @Test(expected = ArithmeticException.class)
    public void findFileAverageWordEmptyListTest(){
        FileEntity fileEntity = new FileEntity();
        fileEntity.setEntityList(new ArrayList<LineEntity>());
        int result = StatisticsFile.findFileAverageWordLength(fileEntity);
    }
}
