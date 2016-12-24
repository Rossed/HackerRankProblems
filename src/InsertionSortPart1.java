import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/**
 * Created by Edwards on 24/12/2016.
 */
public class InsertionSortPart1 {



    public static void insertIntoSorted(int[] ar) {
        // Fill up this function
        if (ar.length<=1) {
            printArray(ar);
        } else if(ar.length>0) {
            int e = ar[ar.length - 1];
            int i = ar.length - 2;
            while (i>(-1) && e < ar[i]) {
                System.out.println(i);
                ar[i + 1] = ar[i];
                i--;
                printArray(ar);
            }
            ar[i + 1] = e;
            printArray(ar);
        }
    }


    /* Tail starts here */
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int s = in.nextInt();
//        int[] ar = new int[s];
//        for(int i=0;i<s;i++){
//            ar[i]=in.nextInt();
//        }
        int[] ar = {2, 3, 4, 5, 6, 7, 8, 9, 10, 1};
        insertIntoSorted(ar);
    }


    private static void printArray(int[] ar) {
        for(int n: ar){
            System.out.print(n+" ");
        }
        System.out.println("");
    }
}
