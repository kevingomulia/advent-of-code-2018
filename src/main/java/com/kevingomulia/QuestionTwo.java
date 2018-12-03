package main.java.com.kevingomulia;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionTwo {

    public static void main(String[] args) throws Exception {
        System.out.println(TwoB());
    }

    public static int TwoA() throws Exception {
        int twoCount = 0;
        int threeCount = 0;

        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\KGOMUL01\\Desktop\\AdventOfCode2018\\src\\resources\\2.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                char[] splitArray = line.toCharArray();
                Map<Character, Integer> map = new HashMap<>();
                for (char chr : splitArray) {
                    if (!map.containsKey(chr)) {
                        map.put(chr, 1);
                    } else {
                        map.put(chr, map.get(chr) + 1);
                    }
                }
                if (map.containsValue(3)) {
                    threeCount += 1;
                }
                if (map.containsValue(2)) {
                    twoCount += 1;
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return threeCount * twoCount;
    }

    public static String TwoB() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\KGOMUL01\\Desktop\\AdventOfCode2018\\src\\resources\\2.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        String line;
        List<String> list = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            list.add(line);
        }

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j <= list.size(); j++) {
                for (int k = j + 1; k < list.size(); k++) {
                    StringBuilder stringOne = new StringBuilder(list.get(j));
                    StringBuilder stringTwo = new StringBuilder(list.get(k));

                    stringOne.deleteCharAt(i);
                    stringTwo.deleteCharAt(i);

                    if (stringOne.toString().equals(stringTwo.toString())) {
                        System.out.println(stringOne.toString());
                        break;
                    }

                }
            }
        }

        return "";
    }

}
