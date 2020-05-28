package com.example.fanxulin.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static RequestQueue requestQueue;
    String TAG = "FinalProject";
    private String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_main);
        Date current = new Date();
        String time = current.toString();
        String[] splited = time.trim().split(" ");
        String year = splited[5];
        String month = splited[1];
        TextView yyear = (TextView) findViewById(R.id.id_year);
        yyear.setText(year);
        TextView mmonth = (TextView) findViewById(R.id.id_month);
        mmonth.setText(month);
        String day = splited[2];
        TextView dday = (TextView) findViewById(R.id.id_day);
        dday.setText(dayTranslation(day));
        startAPICall("/" + monthTranslation(month) + "/" + day);
    }

    private String dayTranslation(String day) {
        if (day.equals("01")) {
            return "1st";
        } else if (day.equals("02")) {
            return "2nd";
        } else if (day.equals("03")) {
            return "3rd";
        } else if (day.charAt(0) == '0') {
            return String.valueOf(day.charAt(1)) + "th";
        } else {
            return day + "th";
        }
    }
    private String monthTranslation(String totrans) {
        if (totrans.equals("Jan")) {
            return "1";
        } else if (totrans.equals("Feb")) {
            return "2";
        } else if (totrans.equals("Mar")) {
            return "3";
        } else if (totrans.equals("Apr")) {
            return "4";
        } else if (totrans.equals("May")) {
            return "5";
        } else if (totrans.equals("Jun")) {
            return "6";
        } else if (totrans.equals("Jul")) {
            return "7";
        } else if (totrans.equals("Aug")) {
            return "8";
        } else if (totrans.equals("Sep")) {
            return "9";
        } else if (totrans.equals("Oct")) {
            return "10";
        } else if (totrans.equals("Nov")) {
            return "11";
        } else {
            return "12";
        }
    }
    public void startAPICall(String tosearch) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://numbersapi.com" + tosearch + "?json",
                    null,
                    new Response.Listener<JSONObject>() {
                        public void onResponse(final JSONObject response) {
                            Log.d(TAG, response.toString());
                            TextView datefact = findViewById(R.id.id_datefact);
                            try {
                                datefact.setText(response.getString("text"));
                            } catch (JSONException e) {
                                System.out.println(e);
                            }
                        }
                    }, new Response.ErrorListener() {
                public void onErrorResponse(final VolleyError error) {
                    Log.w(TAG, error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void StartAPICall(String tosearch) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://numbersapi.com" + tosearch + "?json",
                    null,
                    new Response.Listener<JSONObject>() {
                        public void onResponse(final JSONObject response) {
                            Log.d(TAG, response.toString());
                            TextView numberfact = findViewById(R.id.id_numberfact);
                            try {
                                numberfact.setText(response.getString("text"));
                            } catch (JSONException e) {
                                System.out.println(e);
                            }
                        }
                    }, new Response.ErrorListener() {
                public void onErrorResponse(final VolleyError error) {
                    Log.w(TAG, error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickbutton(View view) {
        TextView number = findViewById(R.id.id_number);
        try {
            int x = Integer.parseInt(number.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, "You are catched, hahaha", Toast.LENGTH_SHORT).show();
            return;
        }
        String tosearch = number.getText().toString();
        if (Integer.parseInt(number.getText().toString()) < 0) {
            Toast.makeText(this, "Give me a positive number please", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Generating for you now", Toast.LENGTH_SHORT).show();
        }
        StartAPICall("/" + tosearch);
    }
}
