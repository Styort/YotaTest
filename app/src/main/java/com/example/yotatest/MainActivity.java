package com.example.yotatest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    GetHtmlTask getHtmlTask;
    Button getHTMLbutt;
    EditText urlET;
    TextView htmlCodeTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initElem();
        getHTMLcodeClick();
    }
    protected void onStop() {
        super.onStop();
        getHtmlTask.cancel(true);
    }
    private void initElem() {
        urlET = (EditText)findViewById(R.id.urlET);
        getHTMLbutt = (Button)findViewById(R.id.getHTMLbutt);
        htmlCodeTV = (TextView)findViewById(R.id.htmlCodeTV);
    }

    public void getHTMLcodeClick(){
        getHTMLbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!urlET.getText().toString().isEmpty()){
                    if(!urlET.getText().toString().contains("http://"))
                    {
                        urlET.setText("http://"+urlET.getText());
                    }
                    getHtmlTask = new GetHtmlTask(urlET.getText().toString(),htmlCodeTV);
                    getHtmlTask.execute();
                }else
                {
                    htmlCodeTV.setText("Введите ссылку!");
                }

            }
        });
    }

}
