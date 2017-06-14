package com.example.mohit.multi;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.xml.datatype.Duration;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    EditText editText;
    ProgressBar progressBar;
    String[] listofimages;
    LinearLayoutCompat loadingsection = null;
    ListView listView;
//a
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

       // Toast.makeText(this,Environment.getExternalStoragePublicDirectory
         //       (Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/" +
          //      Uri.parse(listofimages[0]).getLastPathSegment(),Toast.LENGTH_LONG).show();
        Thread thread = new Thread(new DownloadImageThread());
        thread.start();
    }

    public boolean downloadImageUsingThreads(String url) {

        URL downloadurl = null;
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        File file = null;
        boolean succesfull = false;
        try {
            downloadurl = new URL(url);
        } catch (MalformedURLException e) {

        }

        try {
            connection = (HttpURLConnection) downloadurl.openConnection();
            inputStream = connection.getInputStream();
            file = new File(Environment.getExternalStoragePublicDirectory
                    (Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/" +
                    Uri.parse(url).getLastPathSegment());

            outputStream = new FileOutputStream(file);

            int read = -1;
            byte[] buffer = new byte[1024];

            while ((read = inputStream.read(buffer)) != -1) {

                outputStream.write(buffer, 0, read);
            }

            succesfull = true;
        } catch (IOException e) {

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {

                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {

                }
            }
        }
        return succesfull;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        editText.setText(listofimages[position]);

    }

    private class DownloadImageThread implements Runnable {

        @Override
        public void run() {
            Log.d("thread","thread runnnig");
            downloadImageUsingThreads(listofimages[0]);
        }
    }
}
