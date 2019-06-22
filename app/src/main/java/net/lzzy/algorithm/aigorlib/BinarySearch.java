package net.lzzy.algorithm.aigorlib;

/**
 * Created by lzzy_gxy on 2019/6/22.
 * Description:
 */
public class BinarySearch <T extends Comparable<? super T>> extends BaseSearch<T> {

    BinarySearch(T[] items) {
        super(items);
    }

    @Override
    public int search(T key) {
        return 0;
    }
}
