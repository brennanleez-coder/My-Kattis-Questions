//name: Brennan Lee
//Student no: A0217067E

import java.io.*;

public class Teque {
    public static void main(String[] args) throws IOException {
        PrintWriter pw = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

            int n = Integer.parseInt(br.readLine());

            if (n > 1) {
                int numberOfMethods = n;
                int[] front = new int[n];
                int[] back = new int[n];
                int initialState = (n + 1) / 2;

                //all pointers start from here
                int headOfQueue = initialState;
                int fPointer = initialState;
                int tailOfQueue = initialState;
                int rPointer = initialState;

                //terminates when no more methods
                while (numberOfMethods > 0) {
                    String nextMethod = br.readLine();
                    String[] following = nextMethod.split(" ");

                    String method = following[0]; //method
                    int numberOfTimes = Integer.parseInt(following[1]); //num of times

                    if (method.equals("get")) {
                        String result = "";
                        int initialLength = 0;

                        if (fPointer > headOfQueue) {
                            initialLength = fPointer - headOfQueue;
                        } else if (front[fPointer] == 0 && headOfQueue == fPointer ) {
                            initialLength = 0;
                        } else if ((headOfQueue == fPointer && front[fPointer] != 0)) {
                            initialLength = front.length;
                        } else {
                            initialLength = front.length - (headOfQueue - fPointer);
                        }


                        if (initialLength > numberOfTimes) {
                            result += front[(numberOfTimes + headOfQueue) % n];
                        } else {
                            result += back[(numberOfTimes - initialLength + 1 + rPointer) % n];
                        }
                        pw.println(result);

                    } else if (method.equals("push_back")) {
                        tailOfQueue = (tailOfQueue+1)%n;
                        back[tailOfQueue] = numberOfTimes;
                        if ((fPointer - headOfQueue) < (tailOfQueue - rPointer)) {
                            front[fPointer] = back[rPointer + 1];
                            fPointer = (fPointer+1)%n;
                            rPointer = (rPointer+1)%n;
                        }
                    } else if (method.equals("push_middle")) {
                        back[rPointer] = numberOfTimes;
                        rPointer--;
                        if (rPointer < 0) {
                            rPointer = n - 1;
                        }

                        if ((tailOfQueue - rPointer) > (fPointer - headOfQueue)) {
                            rPointer = (rPointer+1)%n;
                            front[fPointer] = back[rPointer];
                            fPointer = (fPointer+1)%n;
                        }
                    } else if (method.equals("push_front")) {
                        headOfQueue--;
                        if (headOfQueue < 0) {
                            headOfQueue = n - 1;
                        }
                        front[headOfQueue] = numberOfTimes;
                        if ((tailOfQueue - rPointer) + 1 < (fPointer - headOfQueue)) {
                            back[rPointer] = front[fPointer - 1];
                            fPointer--;
                            if (fPointer < 0) {
                                fPointer = n - 1;
                            }
                            rPointer--;
                            if (rPointer < 0) {
                                rPointer = n - 1;
                            }
                        }
                    }
                    numberOfMethods--;
                }
                br.close();
            } else {
                br.readLine();
            }
        }finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}
