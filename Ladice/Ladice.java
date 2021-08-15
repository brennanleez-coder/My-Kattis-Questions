//Name: Brennan Lee
//Student No: A0217067E

import java.util.*;
import java.io.*;

class Ladice {
    public static class UnionFind {
		public int[] p;
		public int[] rank;

		UnionFind(int numOfNodes) {
			this.p = new int[numOfNodes];
			this.rank = new int[numOfNodes];
			for (int i = 0; i < numOfNodes; i++) {
				this.p[i] = i;
				this.rank[i] = 1;
			}
		}

        boolean isSameSet(int i, int j) {
			return findSet(i) == findSet(j);
		}

		void unionSet(int i, int j) {
			if (!isSameSet(i, j)) {
				int x = findSet(i);
				int y = findSet(j);

				if (rank[x] >= rank[y]) {
					this.p[y] = x;
					rank[x] += rank[y];
				} else {
					p[x] = y;
					rank[y] += rank[x];
					
				}
			}
		}

		int findSet(int i) {
			if (p[i] == i) {
				return i;
			} else {
				p[i] = findSet(p[i]);
				return p[i];
			}
		}

		void use(int index) {
			int d = findSet(index);
			rank[d]-=1;
		}


	}
    
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int n = io.getInt();
        int l = io.getInt();
        UnionFind set = new UnionFind(l);

        for (int i = 0 ; i < n ; i++) {
            int first = io.getInt() -1;
            int second = io.getInt() -1;
            
            int intA = set.findSet(first);
            int intB = set.findSet(second);

            if (set.rank[intA] > 0) {
                set.use(first);
                set.unionSet(first,second);
                io.println("LADICA");
            } else if (set.rank[intB] > 0) {
                set.use(second);
                set.unionSet(first,second);
                io.println("LADICA");
            } else {
                io.println("SMECE");
            }
        }
        io.close();
    }

    
}