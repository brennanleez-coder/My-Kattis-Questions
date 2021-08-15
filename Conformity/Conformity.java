//Name:Brennan Lee
//Student No: A0217067E

import java.io.*;
import java.util.*;

class Conformity {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        HashMap<ArrayList<Integer>,Integer> collection = new HashMap<>();
        int maxValue = 0;
        int totalCount = 0;
        //eventually output = max x total count

        int numOfFrosh = io.getInt();
        //popularity of a combination is the number of frosh selecting exactly the same combination of courses.
        do {
            //Arraylist to store courses
            ArrayList<Integer> courseNum = new ArrayList<>();

            for (int j = 0 ; j < 5; j++) {
                courseNum.add(io.getInt());
            }

            //sort arraylist in ascending order
            Collections.sort(courseNum, (first,second) -> first.compareTo(second));

            if (collection.get(courseNum) == null) { //key not present. order doesnt matter
                //put it into the collection
                collection.put(courseNum, 1);
            } else {
                //if present, increment by 1
                collection.put(courseNum, collection.get(courseNum) + 1);
            }
            numOfFrosh--;
        } while(numOfFrosh > 0);

        //System.out.println(collection.keySet());

        for (ArrayList<Integer> key: collection.keySet()) {
            //System.out.println(collection.get(key));
            if (collection.get(key) > maxValue) {
                maxValue = collection.get(key);
            }
        }
        

        for (ArrayList<Integer> key: collection.keySet()) {
            //System.out.println("totalCOunt " + totalCount);
            if (collection.get(key) == maxValue) {
                totalCount ++;
            }
        }
        


        
        io.println(totalCount * maxValue);
        io.close();


    }
}