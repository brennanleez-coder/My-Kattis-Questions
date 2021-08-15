
//Name: Brennan Lee
//Student No: A0217067E
import java.io.*;

public class Weak {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int N = io.getInt();

        while (N > -1) {
            int[][] aMatrix = new int[N][N];
            boolean[] strongVertices = new boolean[N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    aMatrix[i][j] = io.getInt();
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < N; k++) {
                        if (aMatrix[j][k] == 1) {

                            if (aMatrix[i][k] == 1) {

                                if (aMatrix[i][j] == 1) {
                                    strongVertices[i] = true;
                                    strongVertices[j] = true;
                                    strongVertices[k] = true;
                                }
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                if (!strongVertices[i]) {
                    io.print(i + " ");
                }
            }
            io.println();
            N = io.getInt();//terminate
        }
        io.close();

    }
}
