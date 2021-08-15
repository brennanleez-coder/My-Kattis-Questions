//Name: Brennan Lee
//Student No: A0217067E
import java.util.*;
import java.io.*;

class JoinStrings {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int n = io.getInt();

        ListNode[] nodes = new ListNode[n];
        //populating words in the array
        for(int i = 0 ; i < nodes.length; i++) {
            nodes[i] = new ListNode(io.getWord());
        }

        int beginning = 0;

        //run as long as number of strings more than 0
        for (int i = n-1 ; i > 0; i--) {
            int first = io.getInt()-1;
            int second = io.getInt()-1;

            //reset head pointer
            if (nodes[first].next == null) {
                nodes[first].next = nodes[second];
            }
            nodes[first].head.next = nodes[second];
            nodes[first].head = nodes[second].head;

            beginning = first;
        }
        recursivelyPrint(nodes[beginning], io);
    }

    public static void recursivelyPrint(ListNode node, Kattio io) {
        if (node.next != null) {
            io.print(node.getItem());
            recursivelyPrint(node.getNext(), io);
        } else {
            io.println(node.getItem());
            io.close();
        }
    }
}