package sorokin.process;


import sorokin.entity.FileEntity;
import sorokin.entity.LineEntity;

import java.util.ArrayList;
import java.util.List;

public class StatisticsFile {

    private static List<LineEntity> lineEntities;

    public static int findFileLength(FileEntity fileEntity){
        lineEntities = fileEntity.getEntityList();
        int sum = 0;
        for(LineEntity lineEntity : lineEntities){
            sum = sum + lineEntity.getLineLength();
        }
        return sum;
    }

    public static String findFileLongestWord(FileEntity fileEntity){
        lineEntities = fileEntity.getEntityList();
        List<String> listWord = new ArrayList<>();
        for(LineEntity lineEntity : lineEntities){
           listWord.add(lineEntity.getLongestWord());
        }
        String longestWord = StatisticsLine.findLongest(listWord);
        return longestWord;
    }

    public static String findFileShortestWord(FileEntity fileEntity){
        lineEntities = fileEntity.getEntityList();
        List<String> listWord = new ArrayList<>();
        for(LineEntity lineEntity : lineEntities){
            listWord.add(lineEntity.getShortestWord());
        }
        String shortestWord = StatisticsLine.findShortest(listWord);
        return shortestWord;
    }

    public static int findFileAverageWordLength(FileEntity fileEntity){
        lineEntities = fileEntity.getEntityList();
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
