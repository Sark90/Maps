package threads;

import java.util.*;
import static threads.KBInput.inputSize;
import static threads.KBInput.inputThreadsNum;

public abstract class ArrOperations extends Thread {
    private static final int MAX_THREAD_NUM = 10;
    private static final int TESTS_NUM = 3;
    private static long duration;
    private static TreeMap<Integer, Long> durations = new TreeMap<>();
    private static int threadNum;

    protected ArrOperations() {
        super();
    }

    public static boolean isNull(int[] arr) {
        if (arr == null) {
            System.out.println("Array is not initialized.");
            return true;
        } else return false;
    }

    public static int[] genArray() {
        int arr[] = new int[inputSize()];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(100);
        }
        return arr;
    }

    public static void count(ArrOperations ao) {
        threadNum = inputThreadsNum();
        count(ao, threadNum);
        System.out.println(ao.getResult());
    }

    private static void count(ArrOperations ao, int num) {
        ArrOperations threadArr[];
        threadNum = num;
        threadArr = new ArrSum[threadNum];
        if (threadNum > ao.getArrSize()) {
            threadNum = ao.getArrSize();
        }
        Timer timer = new Timer();
        timer.start();
        for (int i = 0; i < threadNum; i++) {
            threadArr[i] = new ArrSum();
            threadArr[i].start();
        }
        for (int i = 0; i < threadNum; i++) {
            try {
                threadArr[i].join();
            } catch (InterruptedException e) {
                System.out.println("Thread " + i + " interrupted.");
            }
        }
        timer.stop();
        /*System.out.println(threadNum + " thread(s):\n\t" + ao.getResult());
        System.out.println("\tTime passed: " + timer.getTime() + " ns");*/
        duration = timer.getTime();
    }

    public abstract String getResult();
    public abstract int getArrSize();

    public static int getThreadNum() {
        return threadNum;
    }
       public static void getOptimalThreadsNum(ArrOperations ao) {
        System.out.println("\nCalculating the optimal number of threads:");
        for(int threadNum = 1; threadNum<=MAX_THREAD_NUM; threadNum++) {
            for (int i=0; i<TESTS_NUM; i++) {
                count(ao, threadNum);
            }
            durations.put(threadNum, duration);
        }
        long minTime = durations.get(1);
        int optimalThreadNum = 1;
        for(int i=2; i<=MAX_THREAD_NUM; i++) {
            if (minTime > durations.get(i)) {
                minTime = durations.get(i);
                optimalThreadNum = i;
            }
        }
        System.out.println("Optimal number of threads : " + optimalThreadNum + " (time: " + minTime + " ns)");
    }
}
