package com.example.githubuser;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

public class HttpRequest extends AsyncTask <String,String,String> {
    public static final String TAG = "HttpRequest";
    public static final String REQUEST_METHOD = "POST";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;

    private Context context;
    private ResultCallback callback;

    public HttpRequest(Context context, ResultCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    public interface ResultCallback {
        void onSuccess(JSONArray res);
        void onError();
    }

    @Override
    protected void onPreExecute() {
        if (!Utilities.isNetworkAvailable(this.context)) {
            Log.d(TAG, "Network is not available");
            if (this.context != null) {
                this.cancel(true);
            }
        }
    }

    @Override
    protected String doInBackground(String... params) {
        String reqUrl = params[0];
        HttpURLConnection urlConnection = null;

        // Read text input stream.
        InputStreamReader isReader = null;
        BufferedReader reader = null;

        // Save server response text.
        StringBuffer JsonResponse = new StringBuffer();

        try {
            URL url = new URL(reqUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            Log.d(TAG, "HttpRequest A1");
            // Set http request method to post.
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            Log.d(TAG, "HttpRequest A2");
            urlConnection.setDoOutput(false);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);


            // Set connection timeout and read timeout value.
            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            urlConnection.setReadTimeout(READ_TIMEOUT);
            Log.d(TAG, "HttpRequest A3");
            InputStream inputStream = urlConnection.getInputStream();
            Log.d(TAG, "HttpRequest A4");
            if (inputStream == null) {
                Log.d(TAG, "inputStream == null");
                return null;
            }

            //input stream
            isReader = new InputStreamReader(inputStream);

            // Create buffered reader.
            reader = new BufferedReader(isReader);

            // Read line of text from server response.
            String line = reader.readLine();

            // Loop while return line is not null.
            while (line != null) {
                // Append the text to string buffer.
                JsonResponse.append(line);

                // Continue to read text line.
                line = reader.readLine();
            }

            if (JsonResponse == null || JsonResponse.length() == 0) {
                Log.d(TAG, "JsonResponse == null");
                return null;
            }

            return JsonResponse.toString();
        }catch (ConnectException e){
            JsonResponse.append("{\"code\": -1}");
            return JsonResponse.toString();
        }catch (SocketTimeoutException e){
            JsonResponse.append("{\"code\": -1}");
            return JsonResponse.toString();
        } catch (IOException e) {
            Log.e(TAG, "Error closing stream", e);
        }

        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(TAG, "Error closing stream", e);
                }
            }
        }
        return null;
    }


    @Override
    protected void onPostExecute(String response) {
        Log.d(TAG, "HttpRequest onPostExecute(): " + response);
        try {
            JSONArray jsonArray = new JSONArray(response);
            callback.onSuccess(jsonArray);
            for (int i = 0; i < jsonArray.length(); i++){
                try {
                    Log.d(TAG, "HttpRequest onPostExecute() jsonArray(" + i +"): " + jsonArray.get(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
