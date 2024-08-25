import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
public class Main {


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
                    MeanMedianMode.findMean(arr);
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
