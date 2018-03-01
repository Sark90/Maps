package threads;

import java.util.Scanner;

public class KBInput {
    public static int inputInt(String msg) {
        System.out.print(msg);
        int i;
        Scanner scanner = new Scanner(System.in);
        try {
            i = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Not valid integer value. Returning 5");
            i = 5;
        }
        return i;
    }
    public static int inputSize() {
        return inputInt("Input the size of the array: ");
    }

    public static int inputThreadsNum() {
        return inputInt("Input the number of threads: ");
    }
}
