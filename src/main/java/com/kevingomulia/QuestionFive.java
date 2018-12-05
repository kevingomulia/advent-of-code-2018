package main.java.com.kevingomulia;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class QuestionFive {

    public static void main(String[] args) throws Exception {
        FiveA();
        FiveB();
    }

    public static void FiveA() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\KGOMUL01\\Desktop\\AdventOfCode2018\\src\\resources\\5.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        try {
            String line = bufferedReader.readLine();
            List<Character> list = new ArrayList<>();

            for (char c : line.toCharArray()) {
                list.add(c);
            }

            System.out.println(list);
            list = reduceList(list);

            System.out.println(list);
            System.out.println(list.size());
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void FiveB() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\KGOMUL01\\Desktop\\AdventOfCode2018\\src\\resources\\5.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        try {
            String line = bufferedReader.readLine();
            int minLength = Integer.MAX_VALUE;
            for (Character c : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {
                String modifiedLine;
                modifiedLine = line.replaceAll(c.toString(), "");
                modifiedLine = modifiedLine.replaceAll(c.toString().toUpperCase(), "");
                List<Character> list = new ArrayList<>();

                for (char chr : modifiedLine.toCharArray()) {
                    list.add(chr);
                }

                list = reduceList(list);
                if (list.size() < minLength){
                    minLength = list.size();
                }
            }
            System.out.println(minLength);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Character> reduceList(List<Character> list) {
        int size = list.size();
        for (int i = 0; i < size - 1; i++) {
            Character firstChar = list.get(i);
            Character secondChar = list.get(i + 1);
            if (((Character.isLowerCase(firstChar) && Character.isUpperCase(secondChar)) ||
                    (Character.isUpperCase(firstChar) && Character.isLowerCase(secondChar))) &&
                    (Character.toLowerCase(firstChar) == Character.toLowerCase(secondChar))) {
                list.remove(i + 1);
                list.remove(i);
                size -= 2;
                if (i >= 2) i -= 2;
                else i = -1;
            }
        }
        return list;
    }
}
