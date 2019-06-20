package net.lzzy.algorithm.aigorlib;

/**
 * Created by lzzy_gxy on 2019/6/13.
 * Description:
 */
public class DirectSort<T extends Comparable<?super T>>extends BaseSort {

    public DirectSort(T[] items) {
        super(items);
    }

    @Override
    public void Sort() {
        for (int i = 0; i < items.length - 1; i++) {
            int minPos = i;
            for (int j = i + 1; j < items.length; j++) {
                if (items[minPos].compareTo(items[j]) > 0) {
                    minPos = j;
                }
            }
            swap(minPos, i);
        }
    }
}

