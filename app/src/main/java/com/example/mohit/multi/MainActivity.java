package com.example.mohit.multi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    EditText editText;
    ProgressBar progressBar;
    String[] listofimages;
    LinearLayoutCompat loadingsection;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        progressBar = (ProgressBar) findViewById(R.id.downloadprogressbar);
        listView = (ListView) findViewById(R.id.listview);
        loadingsection = (LinearLayoutCompat) findViewById(R.id.linearLayout);
        listofimages = getResources().getStringArray(R.array.urls);
        listView.setOnItemClickListener(this);
    }

    public void downloadimage(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        editText.setText(listofimages[position]);

    }
}
