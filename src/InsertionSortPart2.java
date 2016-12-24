import java.io.*;
import java.util.*;
/**
 * Created by Edwards on 24/12/2016.
 */
public class InsertionSortPart2 {
    public static void insertionSortPart2(int[] ar)
    {
        // Fill up the code for the required logic here
        // Manipulate the array as required
        // The code for Input/Output is already provided
        for (int i=1; i<ar.length; i++) {
            int e = ar[i];
            int j = i-1;
            while (j>(-1) && e < ar[j]) {
                ar[j+1] = ar[j];
                ar[j] = e;

                j--;
            }
            printArray(ar);
        }
        if (ar.length==0 || ar.length==1) {
            printArray(ar);
        }

    }



    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int s = in.nextInt();
//        int[] ar = new int[s];
//        for(int i=0;i<s;i++){
//            ar[i]=in.nextInt();
//        }
        int[] ar = {7};
        insertionSortPart2(ar);

    }
    private static void printArray(int[] ar) {
        for(int n: ar){
            System.out.print(n+" ");
        }
        System.out.println("");
    }
}
