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
//        Scanner scanner = new Scanner(new File("C:\\Users\\admin\\Desktop\\AdventOfCode2018\\advent-of-code-2018\\src\\resources\\4.txt"));
        Scanner scanner = new Scanner(new File("C:\\Users\\KGOMUL01\\Desktop\\AdventOfCode2018\\src\\resources\\4.txt"));

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
        Map<String, Map<Integer, Integer>> detailedSleepingSheet = new HashMap<>();

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
                        for (Integer i = Integer.valueOf(sleepTime); i < Integer.valueOf(wakeupTime); i++){
                            Map<Integer, Integer> sleepingTimeSheet;
                            if (!detailedSleepingSheet.containsKey(guardNumber)){
                                sleepingTimeSheet = new HashMap<>();
                            } else {
                                sleepingTimeSheet = detailedSleepingSheet.get(guardNumber);
                            }
                            if (sleepingTimeSheet.containsKey(i)){
                                sleepingTimeSheet.put(i, sleepingTimeSheet.get(i) + 1);
                            } else {
                                sleepingTimeSheet.put(i, 1);
                            }
                            detailedSleepingSheet.put(guardNumber, sleepingTimeSheet);
                        }
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
        System.out.println(detailedSleepingSheet);

        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> entry : sleepingSheet.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
            }
        }
        System.out.println(maxEntry);

        Map<Integer, Integer> maxValue = detailedSleepingSheet.get(maxEntry.getKey());
        Map.Entry<Integer, Integer> maxDetailedEntry = null;
        for (Map.Entry<Integer, Integer> entry : maxValue.entrySet())
        {
            if (maxDetailedEntry == null || entry.getValue().compareTo(maxDetailedEntry.getValue()) > 0)
            {
                maxDetailedEntry = entry;
            }
        }
        System.out.println(maxDetailedEntry);
        System.out.println(Integer.valueOf(maxEntry.getKey().substring(1)) * maxDetailedEntry.getKey());

        Integer maxMinute = 0;
        Integer minute = 0;
        Integer guardNumber = 0;

        // Part B
        for (String key : detailedSleepingSheet.keySet()){
            Map<Integer, Integer> value = detailedSleepingSheet.get(key);
            for (Map.Entry<Integer, Integer> entry : value.entrySet()){
                if (maxMinute < entry.getValue()){
                    System.out.println("Max minute updated: " + maxMinute + " with minute: " + entry.getKey().toString() +
                    " and guardNumber: " + key);
                    maxMinute = entry.getValue();
                    minute = entry.getKey();
                    guardNumber = Integer.valueOf(key.substring(1));
                }
            }
        }
        System.out.println(minute * guardNumber);
    }
}
