//import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.ArrayList;

public class SherlockAndTheBeast {

    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //int t = in.nextInt();
        //for(int a0 = 0; a0 < t; a0++){


        String h = "0123456789";
        System.out.println(h.substring(9, 11));



            int n = 2194;
            int fire=0;

            //First case, all 5s
            if (n%3==0) {
                String ln = "";
                for (int i=0; i<n; i++) {
                    ln = ln + "5";
                }
                System.out.println(new BigInteger(ln));
                fire=1;

            }
            //next case: atleast one set of 555, and ...
            else {

                ArrayList<String> possibleNums = new ArrayList<String>(1);
                int multiplier=0;
                while (multiplier<=n) {
                    if ((n - multiplier) % 5 == 0) {
                        int num5s = multiplier;
                        String ln5 = "";
                        for (int j = 0; j < num5s; j++) {
                            ln5 = ln5 + "5";
                        }
                        int num3s = n - num5s;
                        String ln3 = "";
                        for (int j = 0; j < num3s; j++) {
                            ln3 = ln3 + "3";
                        }

                        possibleNums.add(ln5 + ln3);

                    }
                    multiplier = multiplier + 3;
                }

                /*
                for (int i=0; i<((n/5)); i++) {
                    System.out.println((n-(i+1)*3));
                    if ((n-(i+1)*3)%5==0 && (n-(i+1)*3)>=0) {
                        System.out.println(i);
                        int num5s = (i+1)*3;
                        String ln5 = "";
                        for (int j=0; j<num5s; j++) {
                            ln5 = ln5 + "5";
                        }
                        int num3s = n - num5s;
                        String ln3 = "";
                        for (int j=0; j<num3s; j++) {
                            ln3 = ln3 + "3";
                        }
                        possibleNums.add(ln5 + ln3);
                    }
                }
*/

                BigInteger largest = new BigInteger("0");

                for (int j=0; j<possibleNums.size(); j++) {
                    if (largest.compareTo(new BigInteger(possibleNums.get(j)))==-1) {
                        largest = new BigInteger(possibleNums.get(j));
                    }
                }


                if (largest.compareTo(new BigInteger("0"))!=0) {
                    System.out.println(largest);
                    fire=1;
                }
            }


            if (n%5==0 && fire!=1) {
                String ln3 = "";
                for (int j=0; j<n; j++) {
                    ln3 = ln3 + "3";
                }
                System.out.println(ln3);
            } else if (fire!=1) {System.out.println(-1);}
        }
    //}
}
                                        
                                       

