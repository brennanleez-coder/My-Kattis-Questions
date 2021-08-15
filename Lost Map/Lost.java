//Name: Brennan Lee
//Student No; A0217067E

import java.util.*;
import java.io.*;

class Lost {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int numberOfVillages = io.getInt();

        int[][] aMatrix = createAMatrix(numberOfVillages, io);
        ArrayList<IntegerTriple> eList = createEdgeList(aMatrix, numberOfVillages, io);
        //System.out.println(aMatrix);

        

        Collections.sort(eList, new Comparator<IntegerTriple>() {
            @Override
            public int compare(IntegerTriple first, IntegerTriple second) {
                if (!first.getThird().equals(second.getThird())) {
                    return first.getThird() - second.getThird();
                } else if (!first.getFirst().equals(second.getFirst())) {
                    return first.getFirst() - second.getFirst();
                } else {
                    return first.getSecond() - second.getSecond();
                }
            }
        });

        //System.out.println(eList);

        UnionFind ufds = new UnionFind(numberOfVillages);
        for (int i = 0 ; i < eList.size(); i++) {
            IntegerTriple edge = eList.get(i);
            int firstVertex =  edge.getFirst();
            int secondVertex = edge.getSecond();
            int weightOfEdge = edge.getThird();

            if (!ufds.isSameSet(firstVertex, secondVertex)) {

                io.println(String.format("%d %d", firstVertex + 1, secondVertex + 1));
                ufds.unionSet(firstVertex, secondVertex);
            }
        }
        io.close();
    }


    public static int[][] createAMatrix(int villages, Kattio io) {
        int[][] matrix = new int[villages][villages];
        for (int i = 0 ; i < villages ; i++) {
            for (int j = 0; j < villages ; j++) {
                matrix[i][j] = io.getInt();
            }
        }
        return matrix;
    }

    public static ArrayList<IntegerTriple> createEdgeList(int[][] matrix, int villages, Kattio io) {
        ArrayList<IntegerTriple> edgeList = new ArrayList<>();

        for (int i = 0 ; i < villages; i ++) {
            for (int j = 0 ; j < villages ; j++) {
                if (i!=j) {
                    int weightedEdge = matrix[i][j];
                    edgeList.add(new IntegerTriple(i, j, weightedEdge));
                }
            }
        }

        return edgeList;
    }

    static class IntegerTriple {
        Integer first;
        Integer second;
        Integer third;

        public IntegerTriple(Integer first, Integer second, Integer third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        Integer getFirst() {
            return first;
        }
        Integer getSecond() {
            return second;
        }
        Integer getThird() {
            return third;
        }
        
        @Override
        public String toString() { 
            return String.format("%s %s %s", getFirst(), getSecond(), getThird());         
        }
    }

    static class UnionFind {                                              
        public int[] p;
        public int[] rank;
        //augmented Attributes
        int[] sizeOfSet; 
        int numberOfSets;
      
        public UnionFind(int N) {
          p = new int[N];
          rank = new int[N];
          sizeOfSet = new int[N];
          numberOfSets = N;

          for (int i = 0; i < N; i++) {
            p[i] = i;
            rank[i] = 0;
          }
          Arrays.fill(sizeOfSet, 1);
        }
      
        public int findSet(int i) { 
          if (p[i] == i) return i;
          else {
            p[i] = findSet(p[i]);
            return p[i]; 
          } 
        }
      
        public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }
      
        public void unionSet(int i, int j) { 
          if (!isSameSet(i, j)) {
            numberOfSets -= 1;
            int x = findSet(i), y = findSet(j);
            // rank is used to keep the tree short
            if (rank[x] > rank[y]) {
                p[y] = x;
                sizeOfSet[x] = sizeOfSet[x] + sizeOfSet[y];
            } else { 
                p[x] = y;
                sizeOfSet[y] = sizeOfSet[x] + sizeOfSet[y];
              if (rank[x] == rank[y]) {
                rank[y] = rank[y]+1; 
                }
            } 
          }
        }
    }
}