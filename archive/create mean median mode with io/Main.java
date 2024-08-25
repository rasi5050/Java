import java.io.*;
import java.util.*;
public class Main {
    static float findMean(ArrayList<Integer> arr) {
        int sum = 0;
        for (int i = 0; i < arr.size(); i++) {
            sum += arr.get(i);
        }
        return (float)sum/arr.size();
    }

    static Set<Integer> findMode(ArrayList<Integer> arr) {
        // to find mode
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int n: arr) {
            freqMap.put(n, freqMap.getOrDefault(n, 0)+1);
        }

        int MaxFreq=0;
        for (int f: freqMap.values()) {
            if (f>MaxFreq) {
                MaxFreq=f;
            }
        }

        Set<Integer> modeSet = new HashSet<>();
        for (int key: freqMap.keySet()) {
            if (freqMap.get(key)==MaxFreq) {
                modeSet.add(key);
            }
        }

        return modeSet;
    }

    static float findMedian(ArrayList<Integer> arr) {
        if (arr.size()==1) return arr.get(0);
        return (arr.size()%2==0) ? (arr.get((arr.size()/2)-1) + arr.get(arr.size()/2))/2f : arr.get(((int)arr.size()/2)); 
    }

    public static void main(String[] args) throws IOException {
     

            
        ArrayList<Integer> arr = new ArrayList<Integer>();


        while (true) {
        System.out.println("Add number to array");
        int num = Integer.parseInt(System.console().readLine());
        System.out.println(num);

        arr.add(num);
        
        Collections.sort(arr);

        System.out.println("Current array is: "+arr);


        

        float mean = findMean(arr);
        float median = findMedian(arr);
        Set<Integer> mode = findMode(arr);

        System.out.println("mean is: "+mean);
        System.out.println("median is: "+median);
        System.out.println("mode is/are: "+mode+"\n");

        }
    }
}
