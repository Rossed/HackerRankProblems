/**
 * Created by Edwards on 25/12/2016.
 */
import java.util.*;

public class Quicksort1Partition {
    static void partition(int[] ar) {
        ArrayList<Integer> equal = new ArrayList<Integer>();
        ArrayList<Integer> left = new ArrayList<Integer>();
        ArrayList<Integer> right = new ArrayList<Integer>();
        int partition = ar[0];
        for (int i=0; i<ar.length; i++) {
            if (ar[i]==partition) {
                equal.add(ar[i]);
            } else if (ar[i]<partition) {
                left.add(ar[i]);
            } else if (ar[i]>partition) {
                right.add(ar[i]);
            }
        }

        ArrayList<Integer> concatenation = concatanateArrayLists(left, equal);
        concatenation = concatanateArrayLists(concatenation, right);

        printArray(convertArrayListToArray(concatenation));

    }

    public static int[] convertArrayListToArray(ArrayList<Integer> arraylist) {
        int[] array = new int[arraylist.size()];
        for (int i=0; i<arraylist.size(); i++) {
            array[i] = arraylist.get(i);
        }
        return array;
    }

    public static ArrayList<Integer> concatanateArrayLists(ArrayList<Integer> firstList, ArrayList<Integer> secondList) {
        ArrayList<Integer> concatanatedList = new ArrayList<Integer>();

        for (int i=0; i<firstList.size(); i++) {
            concatanatedList.add(firstList.get(i));
        }

        for (int i=0; i<secondList.size(); i++) {
            concatanatedList.add(secondList.get(i));
        }

        return concatanatedList;
    }

    static void printArray(int[] ar) {
        for(int n: ar){
            System.out.print(n+" ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int[] ar = new int[n];
//        for(int i=0;i<n;i++){
//            ar[i]=in.nextInt();
//        }
        int[] ar = {4, 5, 3, 7, 2};
        partition(ar);
    }
}
