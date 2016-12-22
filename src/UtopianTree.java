import java.io.*;
import java.util.*;

public class UtopianTree {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        //Scanner in = new Scanner(System.in);
        int t = 1; // in.nextInt();

        for (int i=0; i<t; i++) {
            int height = 1;
            int c = 4; //in.nextInt();

            while (c>=0) {

                if (c!=0) {
                    height=height*2;
                    c--;
                    System.out.println("height: "+height);
                    System.out.println("Cycles: "+c);
                }

                if (c==0) {
                    System.out.println(height);
                    c--;
                    //System.out.println("height: "+height);
                    //System.out.println("Cycles: "+c);
                } else {
                    height++;
                    c--;
                    System.out.println("height: "+height);
                    System.out.println("Cycles: "+c);
                }

            }


        }

    }
}