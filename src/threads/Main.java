package threads;

public class Main {

    public static void main(String[] args) {
        threads.ArrSum as = new threads.ArrSum();
        threads.ArrOperations.count(as);
        threads.ArrOperations.getOptimalThreadsNum(as);
    }

}
