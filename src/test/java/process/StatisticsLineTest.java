package process;



import org.junit.Assert;
import org.junit.Test;
import sorokin.process.StatisticsLine;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatisticsLineTest {

    @Test
    public void averageLengthFullListTest(){
        List<String> stringList = Arrays.asList(new String[]{"forward","application","time","zoom"});
        int result = StatisticsLine.averageLength(stringList);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, 6);
    }

    @Test(expected = NullPointerException.class)
    public void averageLengthNullTest(){
        int result = StatisticsLine.averageLength(null);
    }

    @Test(expected = ArithmeticException.class)
    public void averageLengthEmptyListTest(){
        List<String> stringList = new ArrayList<String>();
        int result = StatisticsLine.averageLength(stringList);
    }

    @Test
    public void averageLengthListWithOneWordTest(){
        List<String> stringList = Arrays.asList(new String[]{"application"});
        int result = StatisticsLine.averageLength(stringList);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, 11);
    }

    @Test
    public void findLongestFullListTest() {
        List<String> stringList = Arrays.asList(new String[]{"forward","application","time","zoom"});
        String result = StatisticsLine.findLongest(stringList);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, "application");
    }

    @Test(expected = NullPointerException.class)
    public void findLongestNullTest() {
        String result = StatisticsLine.findLongest(null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void findLongestEmptyListTest() {
        List<String> stringList = new ArrayList<String>();
        String result = StatisticsLine.findLongest(stringList);
    }

    @Test()
    public void findLongestListWithOneWordTest() {
        List<String> stringList = Arrays.asList(new String[]{"time"});
        String result = StatisticsLine.findLongest(stringList);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, "time");
    }

    @Test()
    public void findLongestSameLengthWordsTest() {
        List<String> stringList = Arrays.asList(new String[]{"Hammer", "Summer"});
        String result = StatisticsLine.findLongest(stringList);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, "Hammer");
    }

    @Test()
    public void findLongestSameWordsTest() {
        List<String> stringList = Arrays.asList(new String[]{"summer", "Summer"});
        String result = StatisticsLine.findLongest(stringList);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, "summer");
    }

    @Test()
    public void findLongestEmptyWordsTest() {
        List<String> stringList = Arrays.asList(new String[]{"", ""});
        String result = StatisticsLine.findLongest(stringList);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, "");
    }

    @Test
    public void findShortestFullListTest() {
        List<String> stringList = Arrays.asList(new String[]{"forward","application","time","Camel"});
        String result = StatisticsLine.findShortest(stringList);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, "time");
    }

    @Test(expected = NullPointerException.class)
    public void findShortestNullTest() {
        String result = StatisticsLine.findShortest(null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void findShortestEmptyListTest() {
        List<String> stringList = new ArrayList<String>();
        String result = StatisticsLine.findShortest(stringList);
    }

    @Test()
    public void findShortestListWithOneWordTest() {
        List<String> stringList = Arrays.asList(new String[]{"time"});
        String result = StatisticsLine.findShortest(stringList);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, "time");
    }

    @Test()
    public void findShortestSameLengthWordsTest() {
        List<String> stringList = Arrays.asList(new String[]{"Hammer", "Summer"});
        String result = StatisticsLine.findShortest(stringList);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, "Hammer");
    }

    @Test()
    public void findShortestSameWordsTest() {
        List<String> stringList = Arrays.asList(new String[]{"summer", "Summer"});
        String result = StatisticsLine.findShortest(stringList);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, "summer");
    }

    @Test()
    public void findShortestEmptyWordsTest() {
        List<String> stringList = Arrays.asList(new String[]{"", ""});
        String result = StatisticsLine.findShortest(stringList);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, "");
    }
}
