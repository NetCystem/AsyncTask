package com.example.macbook.asynctask;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        text = findViewById(R.id.text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute("3");

            }
        });

    }


    class MyAsyncTask extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {
            int time = Integer.parseInt(strings[0]);
            try {
                Thread.sleep(time*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return strings[0];
        }

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MainActivity.this, "Progress", "wait 3 seconds");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            text.setText("finished");
            Toast.makeText(MainActivity.this, "The process is finished", Toast.LENGTH_LONG).show();

        }
    }



}
