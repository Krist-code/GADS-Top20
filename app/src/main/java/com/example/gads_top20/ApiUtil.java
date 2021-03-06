package com.example.gads_top20;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ApiUtil {
    public static final String QUERY_PARAMETER_KEY = "q";
    public static final String BASE_API_URL =
            "https://gadsapi.herokuapp.com/api/";
    private ApiUtil(){}



    public static URL buildUrl(String query){
        URL url = null;
        Uri uri = Uri.parse(BASE_API_URL).buildUpon().appendEncodedPath(query)
                .build();
        try{
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getJson(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");
            if (scanner.hasNext()) {
                return scanner.next();
            } else {
                return null;
            }
        }catch(Exception e){
            Log.d("Error", e.toString());
            return null;
        }finally {
            connection.disconnect();
        }
    }
}
