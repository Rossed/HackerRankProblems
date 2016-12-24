/**
 * Created by Edwards on 12/12/2016.
 */

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;

import java.util.regex.*;

public class CoinChangeProblem {

    static ArrayList<Integer> solution = new ArrayList<Integer>();
    static ArrayList<ArrayList<Integer>> solutionList = new ArrayList<ArrayList<Integer>>();

    static HashMap<Integer, Integer> checkMap = new HashMap<Integer, Integer>();
    static HashMap<Integer, ArrayList<ArrayList<Integer>>> solutionMap = new HashMap<Integer, ArrayList<ArrayList<Integer>>>();
    static ArrayList<Integer> currentSet = new ArrayList<Integer>();

    static int lvl=0;
    static int M;

    static int coin(int N, int[] C, int stage) {
        System.out.println("++++++++++++++");
        lvl++;
        int amount = N;
        int previousStage = stage;
        stage = ((stage-1)*C.length)+1;
        int skipStage = ((previousStage%C.length)-1)+((previousStage-1)*C.length);


        if (checkMap.get(amount)!=null) {
            System.out.printf("checked");


            //Already checked and there is a solution
            if (solutionMap.get(amount)!=null) {
                System.out.printf("there is a solution");
                for (int i=0; i<solutionMap.get(amount).size(); i++) {
                    System.out.println("----+++ for amount: "+amount);
                    System.out.println(solutionMap.get(amount).get(i));
                    ArrayList<Integer> solution = concatanateArrayLists(currentSet, solutionMap.get(amount).get(i));
                    //if unique solution
                    boolean contained = false;
                    for (int j=0; j<solutionList.size();j++) {
                        if(compareArrayLists(solution, solutionList.get(j))) {
                            contained=true;
                            System.out.printf("+_+_+_+_+_+_+_");
                            System.out.printf("solution: "+solution);
                            System.out.printf("solutionList: "+solutionList.get(j));
                        }
                    }
                    //solution found, add solution to solution list and solutionMap for that amount
                    if (!contained) {

                        solutionList.add(solution);

                        //todo: fix this part

                    }

                }
            }
        } else {
            //Base case: where a single coin matches the amount left
            outerLoop:
            for (int i = 0; i < C.length; i++) {

                currentSet.add(C[i]);
                System.out.println("_______________________");
                System.out.println("current set: " + currentSet);
                System.out.println("sum: " + sum(currentSet));

                int currentStage = stage + i;

                System.out.println("----");
                System.out.println("up to: " + lvl + "." + currentStage);
                System.out.println("level: " + lvl);
                System.out.println("amount: " + amount);
                System.out.println("number: " + C[i]);

                System.out.println("///////");
                System.out.println("stage%C.length: " + currentStage % C.length);
                System.out.println("stageSimple: " + skipStage);
                System.out.println("///////");

                if (currentStage < skipStage && currentStage % C.length != 0) {

                    System.out.println("skip");

                } else
                    //Solution found
                    if (amount == C[i]) {
                        int amountTemp = 0;

                        System.out.println("last num: " + (sum(currentSet) - (currentSet.get(currentSet.size() - 1))));

                        for (int j = 0; j < currentSet.size(); j++) {
                            //If the amount is not contained in the current set, add it
                            amountTemp = amountTemp + currentSet.get(currentSet.size() - 1 - j);
                            System.out.println("checking number: " + amountTemp);
                            if (solutionMap.get(amountTemp) == null) {
                                ArrayList<Integer> firstNewSolution = slice(currentSet, currentSet.size() - 1 - j, currentSet.size());
                                ArrayList<ArrayList<Integer>> firstNewSolutionArrayList = new ArrayList<ArrayList<Integer>>();
                                firstNewSolutionArrayList.add(firstNewSolution);
                                solutionMap.put(amountTemp, firstNewSolutionArrayList);
                                System.out.println("-------" + solutionMap + "-----");
                            } else {
                                boolean contained = false;
                                for (int k = 0; k < solutionMap.get(amountTemp).size(); k++) {
                                    if (compareArrayLists(slice(currentSet, currentSet.size() - 1 - j, currentSet.size())
                                            , solutionMap.get(amountTemp).get(k))) {
                                        contained = true;

                                    }
                                }
                                if (!contained) {
                                    solutionMap.get(amountTemp).add(slice(currentSet, currentSet.size() - 1 - j, currentSet.size()));
                                    System.out.println("iliketoeattomatos: " + slice(currentSet, currentSet.size() - 1 - j, currentSet.size()));
                                    System.out.println("-------" + solutionMap + "-----");
                                }

                            }
                        }

                        solution.add(C[i]);
                        ArrayList<Integer> temp = new ArrayList<Integer>(solution);
                        Collections.sort(temp);
                        boolean contained = false;
                        if (solutionList.size() == 0) {
                            solutionList.add(temp);
                        } else {
                            for (int j = 0; j < solutionList.size(); j++) {
                                if (compareArrayLists(solutionList.get(j), temp)) {
                                    contained = true;
                                }
                            }
                            if (!contained) {
                                solutionList.add(temp);

                            }
                        }
                        solution.remove(solution.size() - 1);
                        //Keep going, Recursive function
                    } else if (C[i] < N) {
                        solution.add(C[i]);
                        coin(amount - C[i], C, currentStage);
                        //If lower number is greater than current amount, skip all following numbers
                    } else if (C[i] > N) {
                        currentSet.remove(currentSet.size() - 1);
                        break outerLoop;
                    }
                currentSet.remove(currentSet.size() - 1);
                System.out.println("removed");
            }
        }

        checkMap.put(amount, 1);
        System.out.printf("checkMap: "+checkMap);

        lvl--;
        System.out.println(Arrays.toString(solutionList.toArray()));

        if(solution.size()>0){
            return solution.remove(solution.size() - 1);
        } else {
            System.out.println(Arrays.toString(solutionList.toArray()));
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

    public static int sum(ArrayList<Integer> sumList) {
        int sum=0;
        for (int i=0;i<sumList.size(); i++) {
            sum=sum+sumList.get(i);
        }
        return sum;
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
        int N = 15;
        int C[] = {49, 22, 45, 6, 11, 20, 30, 10, 46, 8, 32, 48, 2, 41, 43, 5, 39, 16, 28, 44, 14, 4, 27, 36};
//        int N = 4;
//        int C[] = {1,2,3};
//        int N=20;
//        int C[] = {1, 4, 7, 10};
        M = N;
        int stage = 1;

//        [[2, 2, 2, 2, 2, 5]
//        [2, 2, 2, 4, 5]
//        [2, 2, 5, 6], [2, 2, 11], [2, 4, 4, 5], [2, 5, 8], [4, 5, 6], [4, 11], [5, 5, 5], [5, 10]]

        Arrays.sort(C);
        System.out.println(coin(N, C, stage));
    }
}

