package main.java.com.kevingomulia;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class QuestionSix {

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\KGOMUL01\\Desktop\\AdventOfCode2018\\src\\resources\\6.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        Integer maxX = 0, maxY = 0, coordinate = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] lineSplit = line.split(", ");
                Integer X = Integer.valueOf(lineSplit[0]);
                Integer Y = Integer.valueOf(lineSplit[1]);
                if (X > maxX) {
                    maxX = X;
                }
                if (Y > maxY) {
                    maxY = Y;
                }
                map.put(coordinate, Arrays.asList(X, Y));
                coordinate++;
                line = bufferedReader.readLine();
            }
            System.out.println(maxX.toString() + ", " + maxY.toString());
            bufferedReader.close();
            System.out.println(map);

            Integer[][] matrix = new Integer[maxX + 1][maxY + 1];
            int regionCount = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = countManhattanDistance(map, i, j);
                    regionCount = countAllManhattanDistance(map, i, j, regionCount);
                }
            }
            System.out.println("Region Count: " + regionCount);
//            for (int i = 0; i < matrix.length; i++) {
//                for (int j = 0; j < matrix[i].length; j++) {
//                    System.out.print(matrix[i][j] + " ");
//                }
//                System.out.println();
//            }
            countArea(matrix);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int countAllManhattanDistance(Map<Integer, List<Integer>> map, int i, int j, int regionCount) {
        Integer totalManhattanDistance = 0;
        Integer maxDistance = 10000;
        for (Integer key : map.keySet()) {
            Integer keyXValue = map.get(key).get(0);
            Integer keyYValue = map.get(key).get(1);
            Integer distance = Math.abs(keyXValue - i) + Math.abs(keyYValue - j);
            totalManhattanDistance += distance;
        }
        if (totalManhattanDistance < maxDistance){
            regionCount++;
        }
        return regionCount;
    }

    public static Integer countManhattanDistance(Map<Integer, List<Integer>> map, int i, int j) {
        Integer closestCoordinate = 999, minimumDistance = 999;
        for (Integer key : map.keySet()) {
            Integer keyXValue = map.get(key).get(0);
            Integer keyYValue = map.get(key).get(1);
            Integer distance = Math.abs(keyXValue - i) + Math.abs(keyYValue - j);
            if (distance < minimumDistance) {
                minimumDistance = distance;
                closestCoordinate = key;
            } else if (distance == minimumDistance) {
                closestCoordinate = 999;
            }
        }
        return closestCoordinate;
    }

    public static void countArea(Integer[][] matrix) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (map.containsKey(matrix[i][j])) {
                    map.put(matrix[i][j], map.get(matrix[i][j]) + 1);
                } else {
                    map.put(matrix[i][j], 1);
                }
            }
        }
        System.out.println(map);
        disqualifyInfiniteArea(map, matrix);
    }

    public static void disqualifyInfiniteArea(Map<Integer, Integer> map, Integer[][] matrix) {
        Map<Integer, Integer> newMap = new HashMap<>(map);
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                if (i == 0 || j == 0 || i == matrix.length - 1 || j == matrix[i].length - 1)
                {
                    set.add(matrix[i][j]);
                }
            }
        }
        System.out.println(set);
        for (Integer k : map.keySet()){
            if (set.contains(k)){
                newMap.remove(k);
            }
        }
        System.out.println(newMap);
        Map.Entry<Integer, Integer> maxEntry = null;

        for (Map.Entry<Integer, Integer> entry : newMap.entrySet()) {

            if (maxEntry == null
                    || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        System.out.println(maxEntry);
    }
}
