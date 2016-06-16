package io.github.kolacbb.androiddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import io.github.kolacbb.androiddemo.AndroidHeros.MeasureViewActivity;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    String[] listItem = new String[]{"Measure View", "Custom View"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    // 初始化View
    public void initView() {
        listView = (ListView) findViewById(R.id.list);
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItem);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, MeasureViewActivity.class));
                        break;
                }
            }
        });
    }
}
