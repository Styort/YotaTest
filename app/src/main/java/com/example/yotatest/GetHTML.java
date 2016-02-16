package com.example.yotatest;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Виктор on 16.02.2016.
 */
class GetHtmlTask extends AsyncTask<String, String, String> {
    String url;
    TextView htmlCodeTV;

    public GetHtmlTask(String url,TextView htmlCodeTV) {
        this.url = url;
        this.htmlCodeTV = htmlCodeTV;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        htmlCodeTV.setText("Считываем данные...");
    }

    @Override
    protected String doInBackground(String... params) { //считываем HTML-rод
        StringBuilder html = new StringBuilder();
        try {
            URL urlCl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlCl.openConnection();
            connection.setRequestMethod("GET");

            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), "windows-1251");
            BufferedReader reader = new BufferedReader(inputStreamReader);

            for (String line; (line = reader.readLine()) != null; ) {
                html.append(line+"\n");
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return html.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(!s.isEmpty()){
            htmlCodeTV.setText(s); //записываем HTML код в TextView
        }
        else {
            htmlCodeTV.setText("Данные не считаны, проверьте правильность введеной ссылки!");
        }
    }
}