package com.example.erica.test;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.DataOutputStream;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private TextView textViewHttpResult = null;
    private Button btnLoad = null;
    private Spinner spinner = null;
    private EditText editTextUrl = null;
    private String httpResult = null;
    Handler handler = new Handler();
    Thread httpRequestThread = null;

    private Runnable HttpRequest = new Runnable(){
        public void run() {
            // 運行網路連線的程式
            try {
                if (spinner.getSelectedItem().toString() == "GET") {
                    HttpReuestGet();
                } else if (spinner.getSelectedItem().toString() == "PUT") {
                    HttpReuestPut();
                } else if (spinner.getSelectedItem().toString() == "DELETE") {
                    HttpReuestDelete();
                } else if (spinner.getSelectedItem().toString() == "POST") {
                    HttpReuestPost();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    private void HttpReuestPost() {
        URL urlUrl = null;
        BufferedReader reader = null;
        final StringBuilder stringBuilder;

        try {
            // create the HttpURLConnection
            urlUrl = new URL(editTextUrl.getText().toString());
            HttpURLConnection connection = (HttpURLConnection) urlUrl.openConnection();
            connection.setRequestMethod(spinner.getSelectedItem().toString());
            connection.setReadTimeout(15 * 1000);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(textViewHttpResult.getText().toString());
            wr.flush();
            wr.close();
            // try to get response status code
            final int statusCode = connection.getResponseCode();
            // read the output from the server
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();
            String line = null;
            stringBuilder.append("stats code:" + Integer.toString(statusCode) + "\n");
            stringBuilder.append("result:\n");
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
                handler.post(new Runnable() {
                    public void run() {
                        textViewHttpResult.setText(stringBuilder.toString());
                        //
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close the reader; this can throw an exception too, so
            // wrap it in another try/catch block.
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            httpRequestThread.interrupt();


        }
    }

    private void HttpReuestPut() {
        URL urlUrl = null;
        BufferedReader reader = null;
        final StringBuilder stringBuilder;

        try {
            // create the HttpURLConnection
            urlUrl = new URL(editTextUrl.getText().toString());
            HttpURLConnection connection = (HttpURLConnection) urlUrl.openConnection();
            connection.setRequestMethod(spinner.getSelectedItem().toString());
            connection.setReadTimeout(15 * 1000);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(textViewHttpResult.getText().toString());
            wr.flush();
            wr.close();
            // try to get response status code
            final int statusCode = connection.getResponseCode();
            // read the output from the server
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();
            String line = null;
            stringBuilder.append("stats code:" + Integer.toString(statusCode) + "\n");
            stringBuilder.append("result:\n");
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
                handler.post(new Runnable() {
                    public void run() {
                        textViewHttpResult.setText(stringBuilder.toString());
                        //
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close the reader; this can throw an exception too, so
            // wrap it in another try/catch block.
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            httpRequestThread.interrupt();


        }
    }

    private void HttpReuestDelete() {
        URL urlUrl = null;
        BufferedReader reader = null;
        final StringBuilder stringBuilder;

        try {
            // create the HttpURLConnection
            urlUrl = new URL(editTextUrl.getText().toString());
            HttpURLConnection connection = (HttpURLConnection) urlUrl.openConnection();
            connection.setRequestMethod(spinner.getSelectedItem().toString());

            // just want to do an HTTP DELETE here

            // give it 15 seconds to respond
            connection.setReadTimeout(15 * 1000);
            connection.connect();

            // try to get response status code
            final int statusCode = connection.getResponseCode();

            // read the output from the server
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();
            String line = null;

            stringBuilder.append("stats code:" + Integer.toString(statusCode) + "\n");
            stringBuilder.append("result:\n");

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
                handler.post(new Runnable() {
                    public void run() {
                        textViewHttpResult.setText(stringBuilder.toString());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close the reader; this can throw an exception too, so
            // wrap it in another try/catch block.
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            httpRequestThread.interrupt();
        }
    }

    private void HttpReuestGet()
            throws Exception {
        URL urlUrl = null;
        BufferedReader reader = null;
        final StringBuilder stringBuilder;

        try {
            // create the HttpURLConnection
            urlUrl = new URL(editTextUrl.getText().toString());
            HttpURLConnection connection = (HttpURLConnection) urlUrl.openConnection();
            connection.setRequestMethod(spinner.getSelectedItem().toString());

            // just want to do an HTTP GET here

            // give it 15 seconds to respond
            connection.setReadTimeout(15 * 1000);
            connection.connect();

            // read the output from the server
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
                handler.post(new Runnable() {
                    public void run() {
                        textViewHttpResult.setText(stringBuilder.toString());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            // close the reader; this can throw an exception too, so
            // wrap it in another try/catch block.
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            httpRequestThread.interrupt();
        }
    }

    // Create app UI
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        textViewHttpResult = (TextView) findViewById(R.id.txtResult);
        editTextUrl = (EditText) findViewById(R.id.url);
        btnLoad = (Button) findViewById(R.id.btnLoad);

        spinner = (Spinner) findViewById(R.id.spn);
        String[] spn_content = {"GET", "PUT", "POST", "DELETE"};
        ArrayAdapter<String> aAdapterSpnCont = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, spn_content);
        spinner.setAdapter(aAdapterSpnCont);
        editTextUrl.setText("http://192.168.1.147:3000/tasks");


        btnLoad.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    httpRequestThread = new Thread(HttpRequest);
                    if (!httpRequestThread.isAlive()) {
                        httpRequestThread.start();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

}

