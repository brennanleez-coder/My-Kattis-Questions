//Name: Brennan Lee
//Student No: A0217067E

import java.util.*;
import java.io.*;

class AssigningWorkstations {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in,System.out);

        Comparator<Researcher> comparator = (r1,r2) ->
        {
            if (r1.getTiming() == r2.getTiming()) {
                return r1.getPriority() - r2.getPriority();
            } else {
                return r1.getTiming() - (r2.getTiming());
            }
        };

        PriorityQueue<Researcher> queue = new PriorityQueue<>(comparator);

        PriorityQueue<Researcher> otherQueue = new PriorityQueue<>(comparator);

        int numOfResearchers = io.getInt();
        int inactiveTime = io.getInt();

        int unlocks = 0;
        for (int i = numOfResearchers; i > 0; i--) {
            int arrivalTiming = io.getInt();
            int timeElapsed = io.getInt();

            int departureTiming = arrivalTiming + timeElapsed;
            queue.add(new Researcher(arrivalTiming, departureTiming, "arrive"));
            queue.add(new Researcher(arrivalTiming, departureTiming, "departure"));
        }

        while (!queue.isEmpty()) {
            Researcher current = queue.poll();
            if (current.getAction().equals("arrive")) {
                while(!otherQueue.isEmpty()) {
                    Researcher departed = otherQueue.poll();
                    if (current.getTiming() <= departed.getTiming() + inactiveTime) {
                        unlocks++;
                        break;
                    }
                }
            } else {
                otherQueue.add(current);
            }
        }
        io.println(unlocks);
        io.close();
    }

    public static class Researcher {
        int start;
        int endTime;
        String action;

        Researcher(int start, int endTime, String action) {
            this.start = start;
            this.endTime = endTime;
            this.action = action;
        }

        int getTiming() {
            return action.equals("arrive") ? start : endTime;
        }

        String getAction() {
            return action;
        }

        int getPriority() {
            if (action.equals("departure")) {
                return -1;
            } else {
                return 1;
            }
        }
    }
} 
