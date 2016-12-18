package sorokin.process;

import java.util.List;


public class StatisticsLine {
    public static int averageLength(List<String> stringList){
        int sumLetters = 0;
        int countWords = 0;
        for(String word : stringList){
            sumLetters = sumLetters + word.length();
            countWords = countWords + 1;
        }
        int averageLength = sumLetters / countWords;
        return averageLength;
    }



    public static String findLongest(List<String> strArr) {
        String pretender = strArr.get(0);
        for (int i = 1; i < strArr.size(); i++) {
            if (pretender.length() < strArr.get(i).length()) {
                pretender = strArr.get(i);
            }
        }
        return pretender;
    }

    public static String findShortest(List<String> strArr) {
        String pretender = strArr.get(0);
        for (int i = 1; i < strArr.size(); i++) {
            if (pretender.length() > strArr.get(i).length()) {
                pretender = strArr.get(i);
                System.out.println(pretender);
            }
        }
        return pretender;
    }


}
