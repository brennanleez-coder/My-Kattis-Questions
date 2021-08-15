//Name: Brennan Lee
//Student No: A0217067E

import java.util.*;
import java.io.*;



public class Relay {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int numOfRunners = io.getInt();
        String[][] record = new String[numOfRunners][3];

        //store record in 3 columns, rows are runners names
        for (int i = 0 ; i < numOfRunners; i++) {
            
            record[i][0] = io.getWord();
            record[i][1] = io.getWord();
            record[i][2] = io.getWord();
        }

        //sorting the non 1st leg timings
        for (int i = 0 ; i < numOfRunners - 1; i++) {
            for (int j = i + 1; j <numOfRunners; j++) {
                double x = Double.parseDouble(record[i][2]);
                double y = Double.parseDouble(record[j][2]);
                if (x > y) {
                    String[] temp = record[i];
                    record[i] = record[j];
                    record[j] = temp;
                }
            }
        }

        ArrayList<String> runners = new ArrayList<>();


        for (int i = 0 ; i < 3 ; i++) {
            runners.add(record[i][0]);
        }
        double finalTime = 80; //since each runner <20, max of 4 is <80
        int firstRunner = 0;
        int otherRunner = 0;

        //calculate top 3 timings
        double topThree = 0;
        for (int i = 0; i < 3 ; i++) {
            topThree += Double.parseDouble(record[i][2]);
        }

        for (int i = 3; i < numOfRunners; i++) {
            double nextRunnerOtherLeg = Double.parseDouble(record[i][2]);
            double nextRunnerFirstLeg = Double.parseDouble(record[i][1]);

            // include in the first leg timing
            double total = topThree + nextRunnerFirstLeg;

            if (total < finalTime) {
                finalTime = total;
                firstRunner = i;
            }

            //find other legs
            for (int j = 0; j < 3; j++) {
                double time = topThree + nextRunnerOtherLeg + Double.parseDouble(record[j][1])
                - Double.parseDouble(record[j][2]);

                total = Math.min(total, time);
                if (total < finalTime) {
                    finalTime = total;
                    firstRunner = j;
                    otherRunner = i;
                }
            }
        }


        runners.add(record[otherRunner][0]);
        runners.remove(record[firstRunner][0]);
        runners.add(0, record[firstRunner][0]);

        io.println(finalTime);

        for (int i = 0 ; i < 4; i++) {
            io.println(runners.get(i));
        }

        io.close();
    }

    public static void swap(String[] a, String[] b) {
        String[] temp = a;
        a = b;
        b = temp;
    }
}
