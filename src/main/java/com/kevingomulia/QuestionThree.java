package main.java.com.kevingomulia;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionThree {

    public static void main(String[] args) throws Exception{
        System.out.println(Three());
    }

    public static int Three() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\KGOMUL01\\Desktop\\AdventOfCode2018\\src\\resources\\3.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        int count = 0;
        Pattern pattern = Pattern.compile("^\\#([0-9]+) \\@ ([0-9]+),([0-9]+): ([0-9]+)x([0-9]+)");
        String[][] matrix = new String[1000][1000];

        Map<String, Integer> map = new HashMap<>();

        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String lineNumber = matcher.group(1);
                    Integer leftEdge = Integer.valueOf(matcher.group(2));
                    Integer topEdge = Integer.valueOf(matcher.group(3));
                    Integer width = Integer.valueOf(matcher.group(4));
                    Integer height = Integer.valueOf(matcher.group(5));

                    map.put(lineNumber, (width * height)); // Part 2

                    for (int i = topEdge; i < (topEdge + height); i++){
                        for (int j = leftEdge; j < (leftEdge + width); j++) {
                            if (matrix[i][j] == null){
                                matrix[i][j] = lineNumber;
                            } else {
                                matrix[i][j] = "x";
                            }
                        }
                    }
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == "x"){
                    count++;
                } // Part 2
                else if (matrix[i][j] != null){
                    if (map.get(matrix[i][j]) - 1 == 0){
                        System.out.println("Line Number: " + matrix[i][j]);
                        break;
                    }
                    map.put(matrix[i][j], map.get(matrix[i][j]) - 1);
                }
            }
        }

        return count;
    }
}
