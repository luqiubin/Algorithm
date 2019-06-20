package net.lzzy.algorithm.aigorlib;

/**
 * Created by lzzy_gxy on 2019/6/20.
 * Description:
 */
public class ShellSort <T extends Comparable<? super T>>extends BaseSort<T>{
    ShellSort(T[]items){
        super(items);
    }

    @Override
    public void Sort() {
        int len=items.length;
        int g=len/2;
        while (g>0){
            for (int i=g;i<items.length;i+=g){
                int j=i-g;
                if (bigger(items[i],items[j])){
                  continue;
                }
                T tmp=items[i];
                while (j>=0&&bigger(items[j],tmp)){
                    items[j+g]=items[j];
                    moveStep++;
                    j-=g;
                }
                items[j+g]=tmp;
            }
            g=g/2;
        }
    }

}
