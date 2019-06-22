package net.lzzy.algorithm.aigorlib;

/**
 * Created by lzzy_gxy on 2019/6/22.
 * Description:
 */
public abstract class  BaseSearch <T extends Comparable<? super T>> {

    T[] items;
    long duration;
    int comparcCount;
    int swapCount;


    BaseSearch(T[] items) {
        this.items = items;
        comparcCount = 0;
        swapCount = 0;

    }

    boolean equal(T a, T b) {
        comparcCount++;
        return a.compareTo(b) == 0;
    }

    int compare(T a, T b) {
        comparcCount++;
        return a.compareTo(b);
    }

     public abstract int search(T key);

    public long getDuration() {
        return duration;
    }

     void setDuration(long duration) {
        this.duration = duration;
    }

    public int getComparcCount() {
        return comparcCount;
    }

    public void setComparcCount(int comparcCount) {
        this.comparcCount = comparcCount;
    }

    public int getSwapCount() {
        return swapCount;
    }

    public void setSwapCount(int swapCount) {
        this.swapCount = swapCount;
    }
}
