package com.example.aaron.lab16aaronohare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by aaron on 08/02/2017.
 */

//class used to manage the http request & response
public class HttpManager {

    public static String getData(String uri){
        BufferedReader reader = null;

        try {
            //define url
            URL url = new URL(uri);
            //open connection to defined url
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            StringBuilder sb = new StringBuilder();

            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            //while there are still lines for the reader to read
            while((line = reader.readLine()) != null){
                //add the line to the stingbuilder, and a new line
                sb.append(line + "\n");            }

            return sb.toString();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }

        finally{
            //close reader, if it is open
            if(reader != null){
                try{
                    reader.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

    }


