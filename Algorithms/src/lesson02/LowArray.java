package lesson02;

public class LowArray {
    private long[] a;

    public LowArray(int size) {
        this.a = new long[size];
    }

    public void setElem(int index, long value){
        a[index] = value;
    }

    public long getElem(int idx) {
        return a[idx];
    }

}
