//Name: Brennan Lee
//Student No: A02177067E

import java.util.*;
import java.io.*;


class Island {
    public static void main(String[] args) {

        Kattio io = new Kattio(System.in,System.out);
        int rows = io.getInt();
        int columns = io.getInt();

        String[][] universe = createuniverse(rows,columns,io);


        int[][] visitedArr = createVisitedArr(universe,rows,columns,io);
    
        int counter = getMinimumIslands(universe, visitedArr,rows, columns);


        io.println(counter);
        io.close();
    }


    public static String[][] createuniverse(int rows, int columns, Kattio io) {
        String[][] universe = new String[rows][columns];
        for (int r = 0; r < rows; r++) {
            String[] next = io.getWord().split("");
            for (int c = 0; c < columns; c++) {
                universe[r][c] = next[c];
            }
        }

        return universe;
    }
    public static int[][] createVisitedArr(String [][] universe, int rows, int columns, Kattio io) {
        int[][] visitedArr = new int[rows][columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (universe[r][c].equals("W")) {
                    visitedArr[r][c] = -1;
                } else {
                    visitedArr[r][c] = 0;
                }
            }
        }
        return visitedArr;
    }

    public static int getMinimumIslands(String[][] universe, int[][] visitedArr, int rows, int columns) {
        int counter = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                boolean flag = false;
                if (visitedArr[r][c] == 0) {
                    if (universe[r][c].equals("L")) {
                        flag = true;
                    }
                    flag = depthFirstSearch(universe, visitedArr, flag, r, c);
                    if (flag) {
                        counter++;
                    }
                }

            }
        }
        return counter;
    }

    public static boolean depthFirstSearch(String[][] universe, int[][] visited, boolean flag, int rows, int columns) {

        boolean firstColumn = columns - 1 >= 0;
        boolean firstRow = rows- 1 >= 0;
        boolean lastColumn =  columns + 1 < universe[rows].length;
        boolean lastRow = rows + 1 < universe.length;
        int[] north = {rows,columns+1};
        int[] south = {rows,columns-1};
        int[] east = {rows-1,columns};
        int[] west = {rows+1,columns};

        visited[rows][columns] = 1;

        if (!flag && universe[rows][columns].equals("L")) {
            flag = true;
        }
       

        if (lastColumn && visited[rows][columns+1] == 0) {
            flag = depthFirstSearch(universe, visited, flag, north[0] , north[1]);
        }
        if (lastRow && visited[rows+1][columns] == 0) {
            flag = depthFirstSearch(universe, visited, flag, west[0], west[1]);
        }
        if (firstColumn && visited[rows][columns-1] == 0) {
            flag = depthFirstSearch(universe, visited, flag, south[0], south[1]);
        }
        if (firstRow && visited[rows-1][columns] == 0) {
            flag = depthFirstSearch(universe, visited, flag, east[0], east[1]);
        }
        return flag;
    }

}
