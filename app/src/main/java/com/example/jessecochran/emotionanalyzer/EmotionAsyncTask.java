package com.example.jessecochran.emotionanalyzer;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jessecochran on 2/11/17.
 */

public class EmotionAsyncTask extends AsyncTask {
    BufferedReader reader;
    HttpURLConnection urlConnection;

    private final String LOG_TAG = EmotionAsyncTask.class.getSimpleName();


    @Override
    protected Object doInBackground(Object[] params) {
        //TODO Put api key and query parameters for appropriate api call
        String apiKey = "";
        try {
            final String FETCH_MOVIE_BASE_URL = "BASE_URL_HERE";
            final String SORT_BY = "sort_by";
            final String API_KEY = "api_key";

            //create the request to microsoft api, and then open the connection
            Uri builtUri = Uri.parse(FETCH_MOVIE_BASE_URL).buildUpon()
                    .appendQueryParameter(SORT_BY, //Query here)
                    .appendQueryParameter(API_KEY, apiKey)
                    .build();

            URL url = new URL(builtUri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Read input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (inputStream == null) {
                Log.d(LOG_TAG, "input stream is null");
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            String s = buffer.toString();
            if(s != null) {
                //if String is not null, parse the JSON
                //TODO create new JsonObject and parse the Json object. Check out the private method
                //TODO at the bottom of this link https://github.com/NaturalFractals/PopularMoviesApp/blob/master/app/src/main/java/com/developer/jc/popularmoviesapp/services/FetchMovies.java
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error", e);
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "error closing stream", e);
                }
            }

        }
        return null;
    }
}
