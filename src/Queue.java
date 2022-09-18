import java.util.Scanner;
import java.util.Random;
import static java.util.Arrays.deepToString;

public class Queue {
    // create a random instance
    static Random rand = new Random();
    // create a scanner instance, for reading the user's answer
    static Scanner scan = new Scanner(System.in);
    static String[] ops = {"plus", "minus", "times", "divided by"};

    // return a random integer between two numbers
    public static int getRandomInt(int min, int max) {
        float num_float = max - ((max - min) * rand.nextFloat());
        return Math.round(num_float);
    }

    // map operation index to an operation and compute result
    public static float computeOp(float ans, int num, int op_index) {
        float result = 0;
        if (op_index == 0) {
            System.out.println("add " + num);
            result = ans + num;
        }
        else if (op_index == 1) {
            System.out.println("subtract " + num);
            result = ans - num;
        }
        else if (op_index == 2) {
            System.out.println("multiply by " + num);
            result = ans * num;
        }
        else if (op_index == 3) {
            System.out.println("divide by " + num);
            result = ans / num;
        }
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        // get parameters of the game
        int step_min = 3;
        int step_max = 10;
        int number_min = -10;
        int number_max = 10;
        long wait_time = 2000; // in milliseconds
        // get random number of steps for the game
        int step = getRandomInt(step_min, step_max);
        System.out.println("Game steps = " + step);
        // make number queue
        int[][] queue = new int[step][2];
        int number;
        int operation;
        float answer = 0;
        System.out.println("\nReady...");
        Thread.sleep(1000);
        System.out.println("Set...");
        Thread.sleep(1000);
        System.out.println("Go!");
        Thread.sleep(1000);
        for (int i=0; i<step; i++) {
            number = getRandomInt(number_min, number_max);
            operation = getRandomInt(0, ops.length-1);
            queue[i][0] = number;
            queue[i][1] = operation;
            // start it off
            if (i==0) {
                answer = number;
                System.out.println("\n" + number);
            }
            else {
                // check that if the operation is divide, the result is a whole number
                if (operation==3) {
                    if (((answer/number) % 1) != 0) {
                        operation = getRandomInt(0, ops.length-2);
                    }
                }
                answer = computeOp(answer, number, operation);
            }
            Thread.sleep(wait_time);
        }
        // get user answer
        System.out.println("Answer: ");
        int user_answer = scan.nextInt();
        // test whether the answer is correct
        if (user_answer == answer) {
            System.out.println("Good work!\nThe answer is = " + answer);
        }
        else {
            System.out.println("Booooooo!\nThe answer actually is = " + answer);
        }
    }
}

