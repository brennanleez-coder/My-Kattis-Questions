//Name: Brennan Lee
//Student No: A0217067E

import java.io.*;
import java.util.*;

public class CoconutSplat {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int syllabus = io.getInt();
        int players = io.getInt();

        ArrayList<String> states = new ArrayList<>();
        ArrayList<Integer> numOfHands = new ArrayList<>();
        int indexOfLoser = 0;
        for (int i = 0; i < players; i++) {
            states.add("foldedHands");
            numOfHands.add(i + 1);
        }

        // when size of state is 1, means one player left
        while (numOfHands.size() != 1) {
            indexOfLoser = circular(indexOfLoser + syllabus - 1, states.size());
            String stateOfCurrent = states.get(indexOfLoser);
            int indexOfCurrent = numOfHands.get(indexOfLoser);

            switch (stateOfCurrent) {
                case "foldedHands":
                    states.set(indexOfLoser, "fist"); // downgrade to fist
                    states.add(indexOfLoser + 1, "fist"); // folded becomes 2 fists
                    numOfHands.add(indexOfLoser + 1, indexOfCurrent);
                    break;
                case "fist":
                    states.set(indexOfLoser, "palmDown"); // downgrade to palm faced down
                    indexOfLoser = circular(indexOfLoser + 1, states.size()); // move to next fist/palm
                    break;
                default: //Palm down to hand behind back
                    states.remove(indexOfLoser);
                    numOfHands.remove(indexOfLoser);
            }
        }
        io.println(numOfHands.get(0)); // one player left
        io.close();

    }

    public static int circular(int x, int size) {
        return x % size;
    }
}
