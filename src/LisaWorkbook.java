import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by Edwards on 25/03/2016.
 */
public class LisaWorkbook {
    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();

        ArrayList<ArrayList<Integer>> chapters = new ArrayList<ArrayList<Integer>>(1);

        for (int i=0; i<n; i++) {
            int t = in.nextInt();

            int pages = (int) Math.ceil(t/k);


        }
    }
}
