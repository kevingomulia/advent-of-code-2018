package main.java.com.kevingomulia;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class QuestionFour {

    public static void main(String[] args) throws Exception {
        FourA();
    }

    public static void FourA() throws Exception {
        Scanner scanner = new Scanner(new File("C:\\Users\\admin\\Desktop\\AdventOfCode2018\\advent-of-code-2018\\src\\resources\\4.txt"));

        ArrayList<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
        scanner.close();

        Collections.sort(list);
//        for (String s : list){
//            System.out.println(s);
//        }
        Map<String, Integer> sleepingSheet = new HashMap<>();

        ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()) {
            String stringToCheck = iterator.next();
            if (stringToCheck.contains("Guard")) {
                boolean changeGuard = false;
                while (!changeGuard) {
                    Integer totalSleepTime = 0;
                    String guardNumber = stringToCheck.substring(25, stringToCheck.length() - 13);
                    while (iterator.hasNext() && !iterator.next().contains("Guard")) {
                        iterator.previous();
                        String sleepTime = iterator.next().substring(15, 17);
                        String wakeupTime = iterator.next().substring(15, 17);
                        totalSleepTime += Integer.valueOf(wakeupTime) - Integer.valueOf(sleepTime);
                    }

                    if (sleepingSheet.containsKey(guardNumber)) {
                        sleepingSheet.put(guardNumber, sleepingSheet.get(guardNumber) + totalSleepTime);
                    } else {
                        sleepingSheet.put(guardNumber, totalSleepTime);
                    }
                    changeGuard = true;
                    iterator.previous();
                }
            }
        }

        System.out.println(sleepingSheet);
        // #1549 sleeps the most

//        for (int i = 0; i < list.size(); i++) {
//            String stringToCheck = list.get(i).substring(19);
//            if (stringToCheck.startsWith("Guard")) {
//                String guardNumber = stringToCheck.substring(6, stringToCheck.length() - 13);
//                System.out.println(guardNumber);
//            }
//        }
    }
}
