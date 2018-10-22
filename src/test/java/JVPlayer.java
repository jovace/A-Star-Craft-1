import java.util.*;
import java.io.*;
import java.math.*;

enum DIR {U,D,L,R};

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            String line = in.next();
        }
        int robotCount = in.nextInt();
        for (int i = 0; i < robotCount; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            String direction = in.next();
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println("0 0 U 1 1 R 2 2 D 3 3 L");
    }
}

class Individual{
	
}

class Gene{
	public int x;
	public int y;
	public 
}