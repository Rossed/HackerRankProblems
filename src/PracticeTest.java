import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


/**
 * Created by Edwards on 23/03/2016.
 */
public class PracticeTest {
    public static void main(String[] args) {


        StairCase(6);
    }

    static void StairCase(int n) {


        int numHash = n-1;

        for (int i=0; i<n; i++) {
            String line = "";
            for (int j=0; j<n; j++) {
                if (j >= numHash) {
                    line = line + "#";
                } else {
                    line = line + " ";
                }
            }
            System.out.println(line);
            numHash--;

        }


    }
}
