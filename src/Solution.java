import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution.


        Scanner in = new Scanner(System.in);
        int num1 = in.nextInt();
        int num2 = in.nextInt();

        */

        int num1 = 1701;
        int num2 = 3768;

        //Order numbers, with num1 being larger than num2
        if (num1<num2) {
            int tempNum = num1;
            num1 = num2;
            num2 = tempNum;
        }

        int answer = GCD(num1, num2);
        System.out.println(answer);
    }
    static int GCD(int num1, int num2) {

        int q = num1/num2;
        int r = num1%num2;

        if (r==0) {
            return num2;
        } else {
            num1 = num2;
            num2 = r;
            return GCD(num1, num2);
        }
    }
}
