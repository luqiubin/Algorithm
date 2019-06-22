package net.lzzy.algorithm;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.lzzy.algorithm.aigorlib.BaseSearch;
import net.lzzy.algorithm.aigorlib.BaseSort;
import net.lzzy.algorithm.aigorlib.DirectSort;
import net.lzzy.algorithm.aigorlib.LQB;
import net.lzzy.algorithm.aigorlib.SortFactory;

import java.util.Calendar;
import java.util.Locale;
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
    Spinner spinner1;
    LinearLayout container;
    private Button btnSort;


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

        final ArrayAdapter<String>adapter1=new ArrayAdapter<>
                (this,android.R.layout.simple_dropdown_item_1line,SortFactory.getSortName());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1=findViewById(R.id.activity_main_btn_sp1);
        spinner1.setAdapter(adapter1);

//    }
//    private void initSpinner(){
//        Spinner spinner=findViewById(R.id.activity_main_btn_sp);
//        spinner.setAdapter(new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_dropdown_item, SortFactory.getSortName()));

    }
    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BaseSearch<Integer>search=
                 LQB.getInstance(spinner.getSelectedItemPosition(),items);
            if (search!=null){
                int pos=search.search(v.getId());
                tvResult.setText("该元素位于数组的第".concat((pos+1)+"位"));
            }
        }
    };
    private void resetSearch(){
        container.removeAllViews();
        generateItems();
        btnSort.callOnClick();
        for (Integer i:items){
            Button btn=new Button(this);
            btn.setText(String.format(i.toString(), Locale.CHINA));
            btn.setId(i);
            btn.setLayoutParams(new LinearLayout.LayoutParams(0,
                    ViewGroup.LayoutParams.WRAP_CONTENT,1));
            btn.setOnClickListener(listener);
            container.addView(btn);
        }
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
            case R.id.bt1:
                resetSearch();
        }
        resetSearch();
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