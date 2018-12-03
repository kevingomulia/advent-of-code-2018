package main.java.com.kevingomulia;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class QuestionOne {

    public static void main(String[] args) throws Exception {
        System.out.println(OneA());
        System.out.println(OneB());
    }

    public static int OneA() throws Exception {
        int total = 0;
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\KGOMUL01\\Desktop\\AdventOfCode2018\\src\\resources\\1.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.startsWith("+")) {
                    total += Integer.valueOf(line.substring(1));
                } else {
                    total -= Integer.valueOf(line.substring(1));
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total;
    }

    public static int OneB() throws Exception {
        boolean dupesFound = false;
        int total = 0;
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\KGOMUL01\\Desktop\\AdventOfCode2018\\src\\resources\\1.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        Set<Integer> set = new HashSet<>();

        while (!dupesFound) {
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.startsWith("+")) {
                    total += Integer.valueOf(line.substring(1));
                } else {
                    total -= Integer.valueOf(line.substring(1));
                }
                if (set.contains(total)) {
                    dupesFound = true;
                    break;
                } else {
                    set.add(total);
                }
                line = bufferedReader.readLine();
            }
            // Reset to beginning of file
            fileInputStream.getChannel().position(0);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        }
        bufferedReader.close();
        return total;
    }

}
