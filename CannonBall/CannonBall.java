//Name: Brennan Lee
//Student No: A0217067E

import java.io.*;
import java.util.*;

public class CannonBall {

    static class IntegerTriple {
        public double first;
        public double second;
        public int identifier;

        IntegerTriple(double first, double second, int identifier) {
            this.first = first;
            this.second = second;
            this.identifier = identifier;
        }

        double getFirst() { return first; }
        double getSecond() { return second; }
        int getThird() { return identifier; }

        @Override
        public String toString() { 
            return String.format("%f %f %d", getFirst(), getSecond(), getThird());         
        }

    }

    static class Edge {
        public IntegerTriple beginning;
        public IntegerTriple end;
        public double time;

        Edge(IntegerTriple beginning, IntegerTriple end, double time) {
            this.beginning = beginning;
            this.end = end;
            this.time = time;
        }

    }
    
    public static void main(String[] args) throws IOException {

        Kattio io = new Kattio(System.in, System.out);

        double x1 = io.getDouble();
        double y1 = io.getDouble();
        double x2 = io.getDouble();
        double y2 = io.getDouble();


        ArrayList<IntegerTriple> list = new ArrayList<>();
        ArrayList<Edge> edgeList = new ArrayList<>();
        int cannonsAvailable = io.getInt();

        IntegerTriple coordinateA = createTriplet(x1, y1, 0);
        list.add(coordinateA);

        int id = 1;
        for (id = 1; cannonsAvailable > 0 ; id++, cannonsAvailable--) {
            double x3 = io.getDouble();
            double y3 = io.getDouble();
            IntegerTriple cannon = createTriplet(x3, y3, id);
            list.add(cannon);
        }

        IntegerTriple coordinateB = createTriplet(x2, y2, id);
        list.add(coordinateB);

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size(); j++) {
                double timing = 0;

                IntegerTriple m = list.get(i);
                IntegerTriple n = list.get(j);
                double diffx = Math.pow(m.getFirst() - n.getFirst(), 2);
                double diffy = Math.pow(m.getSecond() - n.getSecond(), 2);
                double distance = Math.sqrt(diffx + diffy);
                if (i == 0) {
                    Edge edge = new Edge(m, n, distance / 5.0);
                    edgeList.add(edge);
                } else {
                    timing = 2.0;
                    if (distance != 50) {
                        timing = timing + Math.abs(50 - distance) / 5.0;
                    }
                    Edge edge = new Edge(m, n, timing);
                    edgeList.add(edge);
                }

            }
        }

        double[] bestTiming = new double[list.size()];
        double infinity = 1000000000;
        int distanceAtSource = 0;
        int source = 0;
        for (int i = 0; i < list.size(); i++) {
            bestTiming[i] = infinity;
        }
        bestTiming[source] = distanceAtSource;

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < edgeList.size(); j++) {
                Edge edge = edgeList.get(j);
                //relax
                if (bestTiming[edge.beginning.getThird()] + edge.time < bestTiming[edge.end.getThird()]) {
                    bestTiming[edge.end.getThird()] = bestTiming[edge.beginning.getThird()] + edge.time;
                }
            }
        }

        io.println(bestTiming[list.size() - 1]);
        io.close();
    }

    public static IntegerTriple createTriplet(double x, double y, int z) {
        return new IntegerTriple(x,y,z);
    }


    


}
