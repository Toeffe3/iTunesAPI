
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.json.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dense
 */
public class iTunesSearch {
    
    iTunesSearch(String navn) throws UnsupportedEncodingException, MalformedURLException, IOException {
        URL url = new URL("https://itunes.apple.com/search?term="               // Create a new URL
                +navn.replaceAll("\\s", "+")                                    // iTunes uses + for spaces
                +"&limit=10"                                                    // Set limit
        );
        
        System.out.println(url);
        
        URLConnection request = url.openConnection();                           // Create a request to get data
        request.connect();                                                      // Get data
        
        BufferedReader r = new BufferedReader(
            new InputStreamReader(
                request.getInputStream(), Charset.forName("UTF-8")
            )
        );
        StringBuilder sb = new StringBuilder();
        String line; while ((line = r.readLine()) != null) {
            sb.append(line);
        }
        String jsonstr = sb.toString(); 
        JSONObject json = new JSONObject(jsonstr);
        
        System.out.println(json);
    }
    
}