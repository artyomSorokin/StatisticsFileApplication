package process;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sorokin.models.FileEntity;
import sorokin.models.LineEntity;


import java.util.ArrayList;
import java.util.List;

public class FileEntityTest {

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
        int result = fileEntity.findFileLength();
        Assert.assertNotNull(result);
        Assert.assertEquals(result, 128);
    }

    @Test(expected = NullPointerException.class)
    public void findFileLengthNullTest(){
        fileEntity.setEntityList(null);
        int result = fileEntity.findFileLength();
    }

    @Test
    public void findFileLengthEmptyFileEntityTest(){
        fileEntity.setEntityList(new ArrayList<LineEntity>());
        int result = fileEntity.findFileLength();
        Assert.assertNotNull(result);
        Assert.assertEquals(result, 0);
    }

    @Test
    public void findFileLongestWordNormalWorkTest(){
        String longestWord = fileEntity.findFileLongestWord();
        Assert.assertNotNull(longestWord);
        Assert.assertEquals(longestWord, "greener");
    }

    @Test(expected = NullPointerException.class)
    public void findFileLongestWordNullTest(){
        fileEntity.setEntityList(null);
        String longestWord = fileEntity.findFileLongestWord();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void findFileLongestWordEmptyFileEntityTest(){
        fileEntity.setEntityList(new ArrayList<LineEntity>());
        String longestWord = fileEntity.findFileLongestWord();
    }

    @Test
    public void findFileShortestWordNormalWorkTest(){
        String longestWord = fileEntity.findFileShortestWord();
        Assert.assertNotNull(longestWord);
        Assert.assertEquals(longestWord, "A");
    }

    @Test(expected = NullPointerException.class)
    public void findFileShortestWordNullTest(){
        fileEntity.setEntityList(null);
        String longestWord = fileEntity.findFileShortestWord();
    }

   @Test(expected = IndexOutOfBoundsException.class)
    public void findFileShortestWordEmptyListTest(){
        fileEntity.setEntityList(new ArrayList<LineEntity>());
        String longestWord = fileEntity.findFileShortestWord();
    }

    @Test
    public void findFileAverageWordLengthNormalWorkTest(){
        int result = fileEntity.findFileAverageWordLength();
        Assert.assertNotNull(result);
        Assert.assertEquals(result, 3);
    }

    @Test(expected = NullPointerException.class)
    public void findFileAverageWordNullTest(){
        fileEntity.setEntityList(null);
        int result = fileEntity.findFileAverageWordLength();
    }

    @Test(expected = ArithmeticException.class)
    public void findFileAverageWordEmptyListTest(){
        fileEntity.setEntityList(new ArrayList<LineEntity>());
        int result = fileEntity.findFileAverageWordLength();
    }
}
