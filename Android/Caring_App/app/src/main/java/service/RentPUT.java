package service;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import bll.Fahrzeug;
import bll.Rent;

public class RentPUT extends AsyncTask<RentPUT.COMMAND, Void, String> {
    private static final String URL ="/Caring_WebService/Caring/rent";
    private static String ipHost = null;
    private Rent rent = null;

    public enum COMMAND{ POST, PUT}

    public static void setIPHost( String ip){
        ipHost = ip;
    }
    public void setRent( Rent rent ){
        this.rent = rent;
    }
    @Override
    protected String doInBackground(RentPUT.COMMAND... commands) {
        java.net.URL url = null;
        HttpURLConnection conn = null;
        BufferedWriter writer = null;
        BufferedReader reader = null;
        String content = null;
        Gson gson = new Gson();

        try {
            url = new java.net.URL("http://"+this.ipHost + URL );
            // Öffnen der Connection
            conn = (HttpURLConnection)url.openConnection();
            // Evaluieren des Commands POST/PUT sind die erwarteten
            switch( commands[0]){
                case POST:
                    conn.setRequestMethod("POST");
                    break;
                case PUT:
                    conn.setRequestMethod("PUT");
                    break;
                default: throw new Exception("Error: command " + commands[0]+ " is not allowed!");
            }
            // setzen des Content-Types auf das JSON Format
            conn.setRequestProperty("Content-Type", "application/json");
            writer = new BufferedWriter( new OutputStreamWriter(( conn.getOutputStream())));
            writer.write(gson.toJson(rent));
            writer.flush();
            writer.close();

            // Überprüfen, ob ein Fehler aufgetreten ist, lesen der Fehlermeldung
            if( conn.getResponseCode() != 200){
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while((line = reader.readLine())!= null){
                    sb.append(line);
                }
                content = conn.getResponseCode() + " " + sb.toString();
            }
            else{
                content = "ResponseCode: "+conn.getResponseCode();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try{
                if( reader != null){
                    reader.close();

                }
                writer.close();
                conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;

    }
}

