//Name: Brennan Lee
//Student No: A0217067E

import java.util.*;
import java.io.*;

class KattisQuest {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in,System.out);

        int numOfCommands = io.getInt();

        TreeSet<Quest> set = new TreeSet<Quest>();
        int questIdentifier = 0;

        for (int i = 1 ; i <= numOfCommands; i++) {
            String command = io.getWord();

            if (command.equals("add")) {

                int details [] = new int[2];
                details[0] = io.getInt();
                details[1] = io.getInt();
                Quest quest = new Quest(questIdentifier, details[0], details[1]);
                questIdentifier ++;
                set.add(quest);

            } else {
                int sessionEnergy = io.getInt();
                Quest queryQuest = set.floor(new Quest(0, sessionEnergy+1, 0));
                long goldCounter = 0;

                while(queryQuest != null) {
                    goldCounter += queryQuest.questGold;
                    sessionEnergy -= queryQuest.questCost;
                    set.remove(queryQuest);

                    queryQuest = set.floor(new Quest(0, sessionEnergy +1, 0));
                }
                io.println(goldCounter);
            }
        }

        io.close();

    }

    public static class Quest implements Comparable<Quest> {
        public int questId;
        public int questCost;
        public int questGold;

        Quest(int questId, int questCost, int questGold) {
            this.questId = questId;
            this.questCost = questCost;
            this.questGold = questGold;
        }

        @Override
        public int compareTo(Quest q2) {
            if (this.questCost - q2.questCost < 0) {
                return -1;
            } else if (this.questCost - q2.questCost > 0) {
                return 1;
            } else if (this.questGold - q2.questGold < 0) {
                return -1;
            } else if (this.questGold - q2.questGold > 0) {
                return 1; 
            } else if (this.questId - q2.questId < 0 ) {
                return 1;
            } else if (this.questId - q2.questId > 0) {
                return -1;
            } else {
                return 0;
            }
        }

    }
}
