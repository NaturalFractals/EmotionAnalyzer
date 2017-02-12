package com.example.jessecochran.emotionanalyzer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.jessecochran.emotionanalyzer.emotion.EmotionServiceRestClient;
import com.example.jessecochran.emotionanalyzer.emotion.contract.Scores;
import com.example.jessecochran.emotionanalyzer.emotion.rest.WebServiceRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EmotionServiceRestClient webServiceRequest = new EmotionServiceRestClient("9af8ddbf990f47b79733d388e18299ab");
        try {
            webServiceRequest.recognizeImage("https://westus.api.cognitive.microsoft.com/emotion/v1.0/recognize?%s");
                    //.post("https://westus.api.cognitive.microsoft.com/emotion/v1.0/recognize?%s", null,

                   // "application/json", true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processJson(JSONObject o) {
        try {
            Log.d("dd", o.getString("anger"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
