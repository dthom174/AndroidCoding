package com.example.android.androidcoding;


import android.util.Log;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class ProfileUtils {

    public static final String LOG_TAG = ProfileUtils.class.toString();

    private ProfileUtils(){
    }

    public static List<UserProfile> fetchData(String requestUrl){

        URL url = createUrl(requestUrl);
        String jsonResponse =  null;

        try{
            jsonResponse = makeHttpRequest(url);
        }catch (IOException e){
            Log.e(LOG_TAG, "problem making the HTTP request");
        }

        List<UserProfile> userProfiles = extractEarthquakes(jsonResponse);

        return userProfiles;
    }

    /**
     *
     * Returns new URL object from the given string URL.
     *
     */
    private static URL createUrl(String stringUrl){

        URL url = null;

        try{

            url = new URL(stringUrl);

        }catch(MalformedURLException e){

            Log.e(LOG_TAG, "problem building the URL");
        }

        return url;
    }

    /**
     * Make an HTTP request to the given and return a string as the response
     * @param url
     * @return
     * @throws IOException
     */
    private static String makeHttpRequest(URL url) throws IOException{

        String jsonResponse = "";

        if(url == null){

            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try{

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(1000);
            urlConnection.setConnectTimeout(1500);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if(urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }else{
                Log.e(LOG_TAG, "error response code" + urlConnection.getResponseCode());
            }

        }catch(IOException e){

            Log.e(LOG_TAG, "problem retrieving JSON results");
        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(inputStream != null){

                inputStream.close();
            }
        }

        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static String readFromStream(InputStream inputStream) throws IOException{

        StringBuilder output = new StringBuilder();

        if(inputStream != null){

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();

            while(line != null){

                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();
    }

    /**
     * Return a list of {@link UserProfile} objects that has been built up from
     * parsing a JSON response.
     */
    private static List<UserProfile> extractEarthquakes(String JSON_RESPONSE){

        // Create an empty ArrayList that we can start adding earthquakes to
        List<UserProfile> userProfiles = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // build up a list of Earthquake objects with the corresponding data.
            JSONObject root = new JSONObject(JSON_RESPONSE);
            JSONArray data =  root.optJSONArray("data");

            for(int i = 0; i < data.length(); ++i){

                JSONObject objec = data.optJSONObject(i);
                String firstName = objec.getString("first_name");
                String lastName = objec.getString("last_name");
                String avatar = objec.getString("avatar");

                userProfiles.add(new UserProfile(firstName,lastName,avatar));

            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e(LOG_TAG, "Problem parsing the JSON results", e);
        }

        // Return the list of earthquakes
        return userProfiles;
    }
}