import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        MeanMedianMode mmm = new MeanMedianMode();
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
                System.out.println("mean of array: " + arr + " is " + mmm.findMean(arr));
                    break;
                case "median":
                    
                    System.out.println("median of array: " + arr + " is " + mmm.findMedian(arr));
                    break;
                case "mode":
                    System.out.println("mode/s of array: " + arr + " is " + mmm.findMode(arr));
                    break;
                case "all":
                System.out.println("mean of array: " + arr + " is " + mmm.findMean(arr));
                System.out.println("median of array: " + arr + " is " + mmm.findMedian(arr));
                System.out.println("mode/s of array: " + arr + " is " + mmm.findMode(arr));
    
                    break;
                default:
                    System.out.println("Invalid operation specified \n");
            }
        }
    }
}
