import java.util.ArrayList;

/**
 * Created by Edwards on 23/03/2016.
 */
public class Grid {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */


        //Scanner in = new Scanner(System.in);
        //int t = in.nextInt();

        //for (int i=0; i<t; i++) {

            int R = 4;//in.nextInt();
            int C = 6;//in.nextInt();


            String G[] = {"123412",
                            "561212",
                            "123612",
                            "781234"};
        String P[] = {"12", "34"};
        int r=2;
        int c=2;


            int count=0;

        /*
            for (int j=0; j<R; j++) {

                G[j] = in.next();
                //System.out.println(G[j]);
            }

            int r = in.nextInt();
            int c = in.nextInt();

            String P[] = new String[r];

            for (int j=0; j<r; j++) {
                P[j] = in.next();
            }
*/

            boolean succeed = false;
            outerloop:
            for (int j=0; j<R; j++) {
                for (int k=0; k<C; k++) {
                    if (Character.getNumericValue(G[j].charAt(k)) == Character.getNumericValue(P[0].charAt(0))) {
                        if(Checker(j, k, G, R, P, r, c)) {
                            System.out.println("YES");
                            succeed = true;
                            break outerloop;

                        }

                    }
                }
            }

            if (!succeed) {
                System.out.println("NO");
            }

        //}
    }



    static boolean Checker(int rowPos, int colPos, String[] G, int R, String[] P, int r, int c) {

        for (int i=0; i<r; i++) {

            if (G[rowPos+i].substring(colPos).length() >= c && (R-rowPos)>=r) {
                System.out.println("rowPos: "+rowPos);
                System.out.println("colPos: "+colPos);
                System.out.println(G[rowPos+i].substring(colPos, colPos+c));
                String ln = G[rowPos+i].substring(colPos, colPos+c);
                if (ln.equals(P[i])) {
                    continue;
                } else {return false;}
            } else {return false;}
        }
        return true;
    }
}
