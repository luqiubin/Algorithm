package net.lzzy.algorithm.aigorlib;

/**
 * Created by lzzy_gxy on 2019/6/13.
 * Description:
 */
public class insertSort <T extends Comparable<? super T>>extends BaseSort<T>{
public insertSort(T[]items){

    super(items);
}

    @Override
    public void Sort() {
        for (int i=1;i<items.length;i++){
            if (bigger(items[i],items[i-1])){
                T temp=items[i];
                int k=i-1;
                for (int j=k;j>=0&& bigger(items[j],temp);j--){
                    items[j+1]=items[j];
                    k--;
                }
                items[k+1]=temp;
            }

        }
    }
}
