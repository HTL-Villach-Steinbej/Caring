package service;


import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CarsList extends AsyncTask<String,Void,String> {

    private static final String URL = "/";
    private static String ipHost = null;

    public static void setIpHost(String ip) {
        ipHost = ip;
    }
    @Override
    protected String doInBackground(String... command) {
        boolean isError = false;
        java.net.URL url = null;
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String content = null;

        try {
            url = new URL(ipHost + URL);
            conn = (HttpURLConnection) url.openConnection();
           /* if (!conn.getResponseMessage().contains("OK")) {
             //   isError = true;
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }*/
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            // get data from server
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            content = sb.toString();
            if (isError) {
                throw new Exception(conn.getResponseCode() + "; " + content);
            }
        } catch (Exception ex) {
            content = ex.getMessage();
        } finally {
            try {
                reader.close();
                conn.disconnect();
            } catch (Exception e) {
            }
        }
        return content;

    }

}
