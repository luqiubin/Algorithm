package net.lzzy.algorithm;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.lzzy.algorithm.aigorlib.BaseSort;
import net.lzzy.algorithm.aigorlib.DirectSort;
import net.lzzy.algorithm.aigorlib.SortFactory;

import java.util.Calendar;
import java.util.Objects;
import java.util.Random;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner spinner;
    private Integer[] items;
    private EditText edtItems;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtItems = findViewById(R.id.activity_main_edt_items);

        findViewById(R.id.activity_main_btn_generate).setOnClickListener(this);
        findViewById(R.id.activity_main_btn_sort).setOnClickListener(this);
        tvResult = findViewById(R.id.activity_main_tv_result);
        final ArrayAdapter<String>adapter=new ArrayAdapter<>
                (this,android.R.layout.simple_dropdown_item_1line,SortFactory.getSortName());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner=findViewById(R.id.activity_main_btn_sp);
        spinner.setAdapter(adapter);
//    }
//    private void initSpinner(){
//        Spinner spinner=findViewById(R.id.activity_main_btn_sp);
//        spinner.setAdapter(new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_dropdown_item, SortFactory.getSortName()));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_btn_generate:
                generateItems();
                displayItems(edtItems);
                break;
            case R.id.activity_main_btn_sort:
                BaseSort<Integer>sort=SortFactory.getInstance(spinner.getSelectedItemPosition(),items);
                BaseSort<Integer>sortNotNull= Objects.requireNonNull(sort);
                sortNotNull.sortTime();
                String result=sortNotNull.getResult();
                tvResult.setText(result);
                Toast.makeText(this, "总时长"+sort.getDuration(),
                        Toast.LENGTH_SHORT).show();
//                DirectSort<Integer> sort=new DirectSort<>(items);
//                sort.sortTime();
//                String result=sort.getResult();
//                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("排序成功");
//                builder.setMessage("对比次数:" + sort.getComparcCount() + "\n").show();
//                displayItems(tvResult);
                break;
                default:
                    break;

        }
    }

    private void displayItems(TextView tv) {
        String display = "";
        for (Integer i : items) {
            display = display.concat(i + ",");
        }
        display = display.substring(0, display.length() - 1);
        tv.setText(display);
    }

    private void directSort() {
        //todo:直接选择排序的具体实现
        for (int i=0;i<items.length-1;i++){
            int minPos=i;
            for (int j=i+1;j<items.length;j++){
                if (items[minPos].compareTo(items[j])>0){
                    minPos=j;
                }
            }
            swap(minPos,i);
        }
    }
    //todo：直接插入排序
    private void insertSort(){
        for (int i=1;i<items.length;i++){
        if (items[i]<items[i-1]){
        int temp=items[i];
        int k=i-1;
        for (int j=k;j>=0&& temp<items[j];j--){
        items[j+1]=items[j];
        k--;
        }
        items[k+1]=temp;
        }
        }
    }

    private void swap(int m,int n) {
        int tmp=items[m];
        items[m]=items[n];
        items[n]=tmp;
    }




//        int temp;
//        for (i=0;i<items.length-1;i++){
//            for (j=0;j<items.length-1;j++){
//                if (items[j]>items[j+1]){
//                    temp=items[j];
//                    items[j]=items[j+1];
//                    items[j+1]=temp;
//                }
//            }
//

    private void generateItems() {
        items = new Integer[10];
        Random generator = new Random();
        for (int i = 0; i < items.length; i++) {
            items[i] = generator.nextInt(99);
        }
    }
}



//for (int i=1;i<items.length;i++){
//        if (items[i]<items[i-1]){
//        int temp=items[i];
//        int k=i-1;
//        for (int j=k;j>=0&& temp<items[j];j--){
//        items[j+1]=items[j];
//        k--;
//        }
//        items[k+1]=temp;
//        }
//        }