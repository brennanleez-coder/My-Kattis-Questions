//Name: Brennan Lee
//Student No: A0217067E


import java.util.*;
import java.io.*;


public class Train {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));


        int trainCapacity = io.getInt();
        int stops = io.getInt();

        int peopleWhoLeft, peopleWhoEntered, peopleWhoWaited, peopleOnBoard = 0; //variables to track left, entered, waited, boarded

        boolean isPossible = true;


        for (int i = 0; i < stops; i ++) {
            peopleWhoLeft = io.getInt();
            peopleWhoEntered = io.getInt();
            peopleWhoWaited = io.getInt();

            if (peopleWhoLeft > peopleOnBoard) {
                isPossible = false;
            }
            //calculate the people on board at the end of the trip
            peopleOnBoard += peopleWhoEntered - peopleWhoLeft;

            

            if (peopleOnBoard < 0) {
                // if there is a non-positive number of people on board
                isPossible = false;
            } else if (peopleOnBoard > trainCapacity) {
                //if people on the train is more than the capacity stated
                isPossible = false;
            } else if (peopleOnBoard != trainCapacity && peopleWhoWaited > 0) { 
                //if the train is not max and there are people waiting
                isPossible = false;
            } else if (i == stops - 1 && peopleWhoWaited > 0) { 
                //if last stop and there are still people waiting
                isPossible = false;
            }
           
        }

        //train needs to be empty at the last stop
        if (peopleOnBoard != 0) {
            isPossible = false;
        }
        pw.print(isPossible ? "possible" : "impossible");
        pw.flush();
    }
    
}
