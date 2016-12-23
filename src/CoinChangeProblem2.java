import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Edwards on 21/12/2016.
 */
public class CoinChangeProblem2 {

    //Array lists to store solutions
    static ArrayList<Integer> solution = new ArrayList<Integer>();
    static ArrayList<ArrayList<Integer>> solutionList = new ArrayList<ArrayList<Integer>>();

    //HashMap where key is amount, and value is and ArrayList of the solutions
    static HashMap<Integer, ArrayList<ArrayList<Integer>>> solutionMap = new HashMap<Integer, ArrayList<ArrayList<Integer>>>();

    //HashMap for checking which amounts have already been checked, this is needed for cases where
    // an amount has been checked but there is no solution
    static HashMap<Integer, Integer> checkMap = new HashMap<Integer, Integer>();

    public static int coin(int N, int[] C, int stage) {
        System.out.println("_____________________________________________");
        System.out.println("solution: "+solution);
        System.out.println("checking: "+N);
        int previousStage = stage;
        stage = ((stage-1)*C.length)+1;

        //If N has been already checked
        if (checkMap.get(N)!=null) {
            System.out.println("IF");
            //If there is a solution
            int prevN = (solution.get(solution.size() - 1)+N);
            if (solutionMap.get(N)!=null) {
                //ArrayList where new solutions will be stored to then be added to solutionMap
                ArrayList<ArrayList<Integer>> newSolutionList = new ArrayList<ArrayList<Integer>>();
                System.out.println("getting: "+(((solution.get(solution.size() - 1)+N))));
                if (solutionMap.get(((solution.get(solution.size() - 1)+N)))!=null) {
                    newSolutionList = solutionMap.get(((solution.get(solution.size() - 1) + N)));
                }
                for (int i=0; i<solutionMap.get(N).size(); i++) {
                    ArrayList<Integer> newSolution = concatenateNumberToArrayList(solution.get(solution.size() - 1), solutionMap.get(N).get(i));
                    boolean contained = false;
                    if (solutionMap.get(prevN)!=null){
                        for (int j = 0; j < solutionMap.get(prevN).size(); j++) {
                            System.out.println("comparing: " + newSolution + " with " + solutionMap.get(prevN).get(j));
                            if (compareArrayLists(newSolution, solutionMap.get(prevN).get(j))) {
                                contained = true;
                            }
                        }
                        if (!contained) {
                            System.out.println("Adding: " + solution);
                            newSolutionList.add(newSolution);
                        }
                    } else {
                        newSolutionList.add(newSolution);
                    }
                }

                solutionMap.put(((solution.get(solution.size() - 1) + N)), newSolutionList);
            }
        } else {
            System.out.println("ELSE");
            //Iterate through all items in C
            outerloop:
            for (int i = 0; i < C.length; i++) {
                System.out.println("N: "+N);
                System.out.println("C[i]: "+C[i]);
                System.out.println(solutionMap);
                //Solution found
                if (N == C[i]) {
                    System.out.println("used");
                    /************* Record in SolutionMap ******************/
                    //Add current item to solution
                    solution.add(C[i]);
                    //Create local ArrayList which is then added to another ArrayList of Arraylists
                    ArrayList<Integer> localSolution = new ArrayList<Integer>();
                    localSolution.add(C[i]);
                    //Record in solutionMap for N
                    ArrayList<ArrayList<Integer>> newSolutionList = new ArrayList<ArrayList<Integer>>();
                    if (solutionMap.get(N)!=null) {
                        newSolutionList = solutionMap.get(N);
                    }
                    newSolutionList.add(localSolution);
                    solutionMap.put(N, newSolutionList);
                    //Add the solution all the way up the tree for each N
                    int tempN = 0;
                    for (int j=0; j<solution.size(); j++) {
                        tempN = tempN + solution.get(solution.size()-1-j);
                        if (solutionMap.get(tempN) == null) {
                            ArrayList<Integer> firstNewSolution = slice(solution, solution.size() - 1 - j, solution.size());
                            ArrayList<ArrayList<Integer>> firstNewSolutionArrayList = new ArrayList<ArrayList<Integer>>();
                            firstNewSolutionArrayList.add(firstNewSolution);
                            solutionMap.put(tempN, firstNewSolutionArrayList);
                        } else {
                            boolean contained = false;
                            for (int k = 0; k < solutionMap.get(tempN).size(); k++) {
                                if (compareArrayLists(slice(solution, solution.size() - 1 - j, solution.size())
                                        , solutionMap.get(tempN).get(k))) {
                                    contained = true;
                                }
                            }
                            if (!contained) {
                                solutionMap.get(tempN).add(slice(solution, solution.size() - 1 - j, solution.size()));
                            }

                        }
                    }
                    /*********************** END **************************/

                    /******************* Add solution to solution list **********************/
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
                    /*********************** END **************************/

                    //Recursive call: if amount is less item in C[i]
                } else if (C[i] < N) {
                    solution.add(C[i]);
                    coin(N - C[i], C, stage);

                    //If amount is bigger than item in C[i], break out of loop (i.e. skip all remaining checks)
                } else if (C[i] > N) {
                    break outerloop;
                }
            }
            //Signals that that N has been checked
            checkMap.put(N, 1);
        }

        System.out.println(solutionMap);

        //If solution is greater than zero, return solution but remove the last item so the program can continue
        if(solution.size()>0){
            return solution.remove(solution.size() - 1);
        } else {
        //If solution is zero, i.e. program has finished checking all return the number
        // of solutions found (i.e. the size of solutionList)
            return solutionMap.get(N).size();
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

    public static ArrayList<Integer> concatenateArrayLists(ArrayList<Integer> firstList, ArrayList<Integer> secondList) {
        ArrayList<Integer> concatenatedList = new ArrayList<Integer>();

        for (int i=0; i<firstList.size(); i++) {
            concatenatedList.add(firstList.get(i));
        }

        for (int i=0; i<secondList.size(); i++) {
            concatenatedList.add(secondList.get(i));
        }

        Collections.sort(concatenatedList);

        return concatenatedList;
    }

    public static ArrayList<Integer> concatenateNumberToArrayList(int number, ArrayList<Integer> secondList) {
        ArrayList<Integer> concatenatedList = new ArrayList<Integer>();

        concatenatedList.add(number);

        for (int i=0; i<secondList.size(); i++) {
            concatenatedList.add(secondList.get(i));
        }

        Collections.sort(concatenatedList);

        return concatenatedList;
    }

    public static ArrayList<Integer> slice (ArrayList<Integer> list, int from, int to) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        for (int i=from; i<to; i++) {
            result.add(list.get(i));
        }

        return result;

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

        int N = 4;
        int C[] = {1,2,3};

//        int N=10;
//        int C[] = {2,5,3,6};

//        int N=20;
//        int C[] = {1, 4, 7, 10};

        int stage = 1;

        Arrays.sort(C);
        System.out.println(coin(N, C, stage));
    }
}
