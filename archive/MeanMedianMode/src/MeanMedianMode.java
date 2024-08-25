import java.util.*;

public class MeanMedianMode {

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
}
