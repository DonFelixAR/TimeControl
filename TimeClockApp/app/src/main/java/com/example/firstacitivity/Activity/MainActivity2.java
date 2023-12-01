package com.example.firstacitivity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.firstacitivity.Adapter.RecordAdapter;
import com.example.firstacitivity.Database.UserRecord;
import com.example.firstacitivity.R;

import org.litepal.LitePal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    private RecyclerView messageContainer;
    private RecordAdapter adapter;
    private List<UserRecord> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        messageContainer = (RecyclerView) findViewById(R.id.messageContainer);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        messageContainer.setLayoutManager(manager);

        Button goBackButton = (Button) findViewById(R.id.buttonGoHome);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goBack = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(goBack);
            }
        });

//        List<UserRecord> dataList = LitePal.findAll(UserRecord.class);
//        RecordAdapter adapter = new RecordAdapter(dataList);
//        messageContainer.setAdapter(adapter);
        Button Analyze = (Button) findViewById(R.id.AnalyzedDataButton);
        Analyze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Anlyze = new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(Anlyze);
            }
        });

        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.deleteAll(UserRecord.class);
                dataList.clear();
                adapter.notifyDataSetChanged();
                messageContainer.setAdapter(null);
            }
        });

    }

    public void onResume(){
        super.onResume();
        List<UserRecord> dataList = LitePal.findAll(UserRecord.class);
        Log.d("MainActivity2", "Data size: " + dataList.size()); // 添加的日志
        adapter = new RecordAdapter(dataList);
        messageContainer.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
