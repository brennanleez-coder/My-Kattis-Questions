import java.io.*;
// Name: Brennan Lee
// Student No: A0217067E

class Ass1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = Integer.parseInt(br.readLine());
        String output = "";

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            String formatter = String.format("Case #%d: ", i+1);
            

            for (int k = 0 ; k < input.length(); k++) {
                char extractChar = input.charAt(k);

                String whatToPress = whatToPress(extractChar);
                if(formatter.charAt(formatter.length()-1) == whatToPress.charAt(0)) {
                    formatter += " ";
                }
                formatter += whatToPress;
            }
            output += formatter + "\n";
        }
        pw.print(output);
        pw.flush();
    }

    public static String whatToPress(char input) {
        if (input==('a')) {
            return "2";
        } else if (input==('b')) {
            return "22";
        } else if (input==('c')) {
            return "222";
        } else if (input==('d')) {
            return "3";
        } else if (input==('e')) {
            return "33";
        } else if (input==('f')) {
            return "333";
        } else if (input==('g')) {
            return "4";
        } else if (input==('h')) {
            return "44";
        } else if (input==('i')) {
            return "444";
        } else if (input==('j')) {
            return "5";
        } else if (input==('k')) {
            return "55";
        } else if (input==('l')) {
            return "555";
        } else if (input==('m')) {
            return "6";
        } else if (input==('n')) {
            return "66";
        } else if (input==('o')) {
            return "666";
        } else if (input==('p')) {
            return "7";
        } else if (input==('q')) {
            return "77";
        } else if (input==('r')) {
            return "777";
        } else if (input==('s')) {
            return "7777";
        } else if (input==('t')) {
            return "8";
        } else if (input==('u')) {
            return "88";
        } else if (input==('v')) {
            return "888";
        } else if (input==('w')) {
            return "9";
        } else if (input==('x')) {
            return "99";
        } else if (input==('y')) {
            return "999";
        } else if (input==('z')) {
            return "9999";
        } else if (input==(' ')) {
            return "0";
        } else {
            return "";
        }
    }
}