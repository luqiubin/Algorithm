package net.lzzy.algorithm.aigorlib;

import java.sql.Time;
import java.util.GregorianCalendar;

/**
 * Created by lzzy_gxy on 2019/6/15.
 * Description:
 */
public abstract class BaseSort<T extends Comparable<? super T>>  {

     T[] items;
     long duration;
     int comparcCount;
     int swapCount;
     int moveStep;
     int Time;
    public Object DirectSort;

    BaseSort(T[] items){
        this.items=items;
        comparcCount=0;
        swapCount=0;
        moveStep=0;

    }
    boolean bigger(T a,T b){
        comparcCount++;
        return a.compareTo(b)>0;
    }
    public void sortTime(){
         Long startTime=System.currentTimeMillis();
         Sort();
         long endTime=System.currentTimeMillis();
        Time= (int) (endTime-startTime);
        DirectSort=System.currentTimeMillis();
    }
    public abstract void Sort();

    void swap(int i,int j){
        T tmp=items[1];
        items[i]=items[j];
        items[j]=tmp;
        swapCount++;
    }
    public String getResult(){
        String display="";
        for (T i:items){
            display=display.concat(i+",");


        }
        return display.substring(0,display.length()-1);
    }
    public long getDuration(){
        return duration;
    }
    public int getComparcCount(){
        return comparcCount;
    }
}
