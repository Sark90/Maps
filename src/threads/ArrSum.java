package threads;

public class ArrSum extends ArrOperations {
    private static int[] arr;
    private static int sum=0, start=0, end=0;
    static {
        arr = ArrOperations.genArray();
        showArray();
    }
    public ArrSum() {
        super();
        //start();
    }
    @Override
    public void run() {
        if (isNull(arr)) {return;}
        if (start==0) {
            end = arr.length / getThreadNum() - 1;

        }
        count(start, end);
    }
    private void count(int s, int e) {
        if (isNull(arr) || (end==0) || (end>=arr.length)) return;
        if (end==getThreadNum()*(arr.length/getThreadNum())-1) { end = arr.length-1; }
        for(int i=s; i<=e; i++) { sum += arr[i]; }
        start += arr.length/getThreadNum();
        end += arr.length/getThreadNum();
    }

    @Override
    public String getResult() {
        return "Sum = " + sum;
    }

    @Override
    public int getArrSize() {
        return arr.length;
    }


    public static void showArray() {
        if (isNull(arr)) return;
        System.out.println("\tArray:");
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
