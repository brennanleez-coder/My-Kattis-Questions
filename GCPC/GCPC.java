//Name: Brennan Lee
//Student No:A0217067E

import java.util.*;
import java.io.*;

class GCPC {
    public static void main(String[] args){
        Kattio io = new Kattio(System.in, System.out);

        HashMap<Integer, Team> hMap = new HashMap<>();
        AVLTree balancedTree = new AVLTree();
       
        int numOfTeams = io.getInt();
        int numOfEvents = io.getInt();

        for (int i = 1; i <= numOfTeams; i++) {
            Team team = new Team(i,0, 0);
            balancedTree.insert(team);
            hMap.put(i, team);
        }

        for (int i = numOfEvents; i > 0; i--) {
            int t = io.getInt();

            Team currentTeam = hMap.get(t);
            Team updatedTeam = new Team(currentTeam.id, currentTeam.score + 1,
            currentTeam.penalty + io.getInt()); // input penalty added to current team penalty

            balancedTree.insert(updatedTeam);
            hMap.put(t, updatedTeam);
            balancedTree.delete(currentTeam);
            int rank = balancedTree.rank(hMap.get(1));

            io.println(rank);
        }
        io.close();
    }
}














































