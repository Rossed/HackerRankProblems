import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Edwards on 21/12/2016.
 */
public class CoinChangeProblem2 {

    //Array lists to store solutions
    static ArrayList<Integer> solution = new ArrayList<Integer>();
    static ArrayList<ArrayList<Integer>> solutionList = new ArrayList<ArrayList<Integer>>();

    public static int coin(int N, int[] C, int stage) {
        int amount = N;
        int previousStage = stage;
        stage = ((stage-1)*C.length)+1;

        //Iterate through all items in C
        outerloop:
        for (int i=0; i<C.length; i++) {
            //Solution found
            if (N == C[i]) {
                //Add current item to solution
                solution.add(C[i]);
                //Create a temporary ArrayList that can be sorted out of solution
                ArrayList<Integer> temp = new ArrayList<Integer>(solution);
                //Set up check boolean
                boolean contained = false;
                //If solutionList is empty, immediately add the new solution
                if (solutionList.size() == 0) {
                    solutionList.add(temp);
                //Else, check to see if solution is already contained
                } else {
                    for (int j = 0; j < solutionList.size(); j++) {
                        if (compareArrayLists(solutionList.get(j), temp)) {
                            contained = true;
                        }
                    }
                    //Add solution to solutionList if not contained
                    if (!contained) {
                        solutionList.add(temp);
                    }
                }
                //Remove last item in solution so the program can continue
                solution.remove(solution.size() - 1);

            //Recursive call: if amount is less item in C[i]
            } else if(C[i] < N){
                solution.add(C[i]);
                coin(N - C[i], C, stage);
            //If amount is bigger than item in C[i], break out of loop (i.e. skip all remaining checks)
            } else if (C[i] > N) {
                break outerloop;
            }
        }

        //If solution is greater than zero, return solution but remove the last item so the program can continue
        if(solution.size()>0){
            return solution.remove(solution.size() - 1);
        } else {
        //If solution is zero, i.e. program has finished checking all return the number
        //of solutions found (i.e. the size of solutionList)
            return solutionList.size();
        }
    }

    public static boolean compareArrayLists(ArrayList<Integer> listOne, ArrayList<Integer> listTwo) {

        if (listOne.size() != listTwo.size()) {
            return false;
        }
        Collections.sort(listOne);
        Collections.sort(listTwo);

        for (int i=0; i<listOne.size();i++) {
            if (listOne.get(i)!=listTwo.get(i)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
//        Scanner in = new Scanner(System.in);
//        int N = in.nextInt();
//        int C_i = in.nextInt();
//        int C[] = new int[C_i];
//        for (int i=0; i<C_i; i++) {
//            C[i] = in.nextInt();
//        }
//        int solutions = 0;

//        int N = 15;
//        int C[] = {49, 22, 45, 6, 11, 20, 30, 10, 46, 8, 32, 48, 2, 41, 43, 5, 39, 16, 28, 44, 14, 4, 27, 36};

//        int N = 4;
//        int C[] = {1,2,3};

        int N=10;
        int C[] = {2,5,3,6};

//        int N=20;
//        int C[] = {1, 4, 7, 10};

        int stage = 1;

        Arrays.sort(C);
        System.out.println(coin(N, C, stage));
    }
}
