package com.example.firstacitivity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.firstacitivity.Database.UserRecord;
import com.example.firstacitivity.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
//    private static List<String> recordList = new ArrayList<>();
//    private static Map<String,Double> lableTimeMap = new HashMap<>();
    private Calendar calendar;

    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //定义各个Container对象
        EditText textBoxAction = (EditText) findViewById(R.id.editTextActionBox);
        EditText textBoxDuration = (EditText) findViewById(R.id.editTextDurationBox);
        Spinner ActionCategory = (Spinner) findViewById(R.id.spinnerAction);

//        calendar = Calendar.getInstance();  //获得实例
          //设置为中国区的时间

        //定义Record button对象及点击功能,即点击Record按钮后，将用户输入数据保存到db UserRecord中
        Button recordButton = (Button) findViewById(R.id.buttonRecord);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserRecord userRecord = new UserRecord();
                //获取Container内的数据
                String Action = textBoxAction.getText().toString();
                     //需先获取String，再转换成double
                String DurationToString = textBoxDuration.getText().toString();
                double Duration = Double.parseDouble(DurationToString);
                String selectedAction = ActionCategory.getSelectedItem().toString();
                //将这些获取到的数据写入db UserRecord
                userRecord.setCategory(selectedAction);
                userRecord.setDuration(Duration);
                userRecord.setActionDescription(Action);
                //获取时间信息将数据写入db UserRecord
                calendar = Calendar.getInstance();
                calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
                String year = String.valueOf(calendar.get(Calendar.YEAR));
                String month = String.valueOf(calendar.get(Calendar.MONTH) + 1); // 注意月份是从0开始的，所以要加1
                String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
//                Log.d("MainActivity", "Year: " + year + ", Month: " + month + ", Day: " + day);
                userRecord.setYear(year);
                userRecord.setMonth(month);
                userRecord.setDay(day);

                userRecord.save();
                Toast.makeText(MainActivity.this, "record successfully", Toast.LENGTH_SHORT).show();
                textBoxAction.setText("");
                textBoxDuration.setText("");
            }
        });

        //定义History Button对象及点击功能，即单纯跳转到MainAcitivity2
        Button detailButton = (Button) findViewById(R.id.buttonGoNext);
        detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToMainActivity2 = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(goToMainActivity2);
            }
        });
    }



}