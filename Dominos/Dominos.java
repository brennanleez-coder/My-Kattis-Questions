//Name: Brennan Lee
//Student No: A0217067E

import java.io.*;
import java.util.*;

public class Dominos {
    //global variables
    public static ArrayList<ArrayList<Integer>> listlist;
    public static boolean[] doneArray;

    //populate aList
    public static void createAList(int numberOfLines, Kattio io) {
        for (int i = 0; i < numberOfLines; i++) {
            int a = io.getInt();
            int b = io.getInt(); 
            if (a==b) {
                continue;
            } else {
                listlist.get(a-1).add(b); 
            }    
        }
    }
    public static void depthFirstSearch(Stack<Integer> s, int source) {
        doneArray[source] = true;
        int size = listlist.get(source).size();
        for (int i = 0; i < size ; i++) {
            int endPoint = listlist.get(source).get(i) -1;
            if (doneArray[endPoint] == false)
                depthFirstSearch(s, endPoint);
        }
        s.push(source+1);
    }

    public static void depthFirstSearch(int source) {
        doneArray[source] = true;
        int size = listlist.get(source).size();
        for (int i = 0; i < size ; i++) {
            if (!doneArray[listlist.get(source).get(i)-1])
                depthFirstSearch(listlist.get(source).get(i)-1);
        }
    }

    public static int findConnectedComponents(Stack<Integer> stack, int numberOfTiles, int ccCounter) {
        int output = ccCounter;
        for (int i = 0; i < numberOfTiles; i++) {
            if (!doneArray[i]) {
                depthFirstSearch(stack, i);
            }
        }

        for (int i=0;i<numberOfTiles; i++) {
            doneArray[i] = false;
        }

        while (stack.size() != 0) {
            int x = stack.pop();
            if (!doneArray[x-1]) {
                depthFirstSearch(x-1);
                output++;
            }
        }
        return output;
    }
    
    public static void main(String[] args) throws IOException {
        long time = System.currentTimeMillis();
        Kattio io = new Kattio(System.in,System.out);

        int testCases = io.getInt();
        for (int i = 0; i < testCases; i++) {
            int ccCounter = 0;
            int numberOfTiles = io.getInt();
            int numberOfLines = io.getInt();

            doneArray = new boolean[numberOfTiles];
            listlist = new ArrayList<>(numberOfTiles);

            for (int j = 0; j < numberOfTiles; j++) {
                listlist.add(new ArrayList<>());
            }          

            createAList(numberOfLines, io);
            Stack<Integer> s = new Stack<>();
            ccCounter = findConnectedComponents(s, numberOfTiles, ccCounter);
            io.println(ccCounter);
        }
        long end = System.currentTimeMillis();
        //io.println(end-time);
        io.close();
    }
}