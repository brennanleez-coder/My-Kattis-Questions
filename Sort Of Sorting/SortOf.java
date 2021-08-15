//Name: Brennan Lee
//Student No: A0217067E


import java.util.*;
import java.io.*;

class SortOf {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio(System.in, System.out);

        

        int noOfCases = -1;

        while (noOfCases != 0) {
            noOfCases = io.getInt();
            if (noOfCases != 0) {
                io.println();
            }

            String[] nameArr = new String[noOfCases];
            
            for(int i = 0; i < noOfCases; i++) {
                nameArr[i] = io.getWord();
            }
            Collections.sort(Arrays.asList(nameArr),
            new Comparator<String>() {
                public int compare(String name1, String name2) {
                    String string1 = name1.substring(0,2);
                    String string2 = name2.substring(0,2);

                    return string1.compareTo(string2);
                }
            });
            for (int i = 0 ; i < noOfCases; i++) {
                io.println(Arrays.asList(nameArr).get(i));
            }
        }
        io.close();
    }
}
