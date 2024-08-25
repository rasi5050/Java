import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static float findMean(ArrayList<Integer> arr) {
        int sum = 0;
        for (int i = 0; i < arr.size(); i++) {
            sum += arr.get(i);
        }
        float mean = (float) sum / arr.size();
        System.out.println("mean of array: " + arr + " is " + mean);
        return mean;
    }

    static Set<Integer> findMode(ArrayList<Integer> arr) {
        // to find mode
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int n : arr) {
            freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);
        }

        int MaxFreq = 0;
        for (int f : freqMap.values()) {
            if (f > MaxFreq) {
                MaxFreq = f;
            }
        }

        Set<Integer> modeSet = new HashSet<>();
        for (int key : freqMap.keySet()) {
            if (freqMap.get(key) == MaxFreq) {
                modeSet.add(key);
            }
        }

        Set<Integer> mode = modeSet;
        System.out.println("mode/s of array: " + arr + " is " + mode);
        return mode;
    }

    static float findMedian(ArrayList<Integer> arr) {
        if (arr.size() == 1)
            return arr.get(0);
        float median = (arr.size() % 2 == 0) ? (arr.get((arr.size() / 2) - 1) + arr.get(arr.size() / 2)) / 2f
                : arr.get(((int) arr.size() / 2));
        System.out.println("median of array: " + arr + " is " + median);
        return median;
    }

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < args.length; i += 2) {
            String operation = args[i];
            String arrAsStr = args[i + 1];

            String[] arrStrs = arrAsStr.split(",");
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (int j = 0; j < arrStrs.length; j++) {
                arr.add(Integer.parseInt(arrStrs[j]));
            }

            Collections.sort(arr);

            switch (operation) {
                case "mean":
                    findMean(arr);
                    System.out.println();
                    break;
                case "median":
                    findMedian(arr);
                    System.out.println("");
                    break;
                case "mode":
                    findMedian(arr);
                    System.out.println("");
                    break;
                case "all":
                    findMean(arr);
                    findMedian(arr);
                    findMode(arr);
                    System.out.println("");
                    break;
                default:
                    System.out.println("Invalid operation specified \n");
            }
        }
    }
}
