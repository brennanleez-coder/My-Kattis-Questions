//Name: Brennan Lee
//Student No: A0217067E

import java.io.*;
import java.util.*;


class KindsOfPeople {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in,System.out);
        int row = io.getInt();
        int column = io.getInt();

        int [][] map = createMap(row,column, io);

        int[][] pathTraversed = new int[row][column];

        int visited = 2;
        for (int a = 0 ; a < row; a++) {
            for (int b = 0; b < column; b++) {
                if(pathTraversed[a][b] == 0) {
                    breadthFirstSearch(map, pathTraversed, a, b, map[a][b], visited);
                    visited++;
                }
            }
        }

        int n = io.getInt();
        for (int i = 0 ; i < n ; i++) {
            int r1 = io.getInt();
            int c1 =io.getInt();
            int r2 = io.getInt();
            int c2 = io.getInt();
            output(pathTraversed, map, r1, r2, c1, c2, io);
        }
        
        io.close();
    }

    public static int[][] createMap(int r, int c, Kattio io) {
        int [][] map = new int[r][c];
        for(int a = 0 ; a < r; a++) {
            String[] rows = io.getWord().split("");
            for (int b = 0 ; b < c; b++) {
                map[a][b] = Integer.parseInt(rows[b]);
            }
        }

        return map;
    }
    public static void output(int[][] pathTraversed, int[][] map, int r1, int r2, int c1, int c2, Kattio io) {
        boolean neither = pathTraversed[r2-1][c2-1] != pathTraversed[r1-1][c1-1];
        boolean binary = map[r1-1][c1-1] == 0;
        if (neither) {
            io.println("neither");
        } else {
            if (binary) {
                io.println("binary");
            } else if (!(binary && neither)) {
                io.println("decimal");
            }
        }

    }

    public static void breadthFirstSearch(int[][] map, int[][] path, int i, int j, int current,int visited) {
        boolean outside = map.length <= i ||map[0].length <= j || i < 0 || j < 0;
        if (outside) {
            return; 
        } else if (path[i][j] == visited) {
            return; 
        } else if (map[i][j] != current) { 
            return; 
        } 
        path[i][j] = visited; 

        int[] north = {i,j+1};
        int[] south = {i,j-1};
        int[] east = {i-1,j};
        int[] west = {i+1,j};
        

        breadthFirstSearch(map, path, north[0], north[1], current, visited);
        breadthFirstSearch(map, path, south[0], south[1], current, visited); 
        breadthFirstSearch(map, path, east[0], east[1], current, visited);
        breadthFirstSearch(map, path, west[0], west[1], current, visited);
    }
}