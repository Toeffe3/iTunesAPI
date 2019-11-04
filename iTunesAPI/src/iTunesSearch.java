
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.*;

/**
 * This class contains the api request and handeling.
 * @author dense
 */
public class iTunesSearch {
    
    private JSONArray o;
    private int n = -1;
    /**
     * Given no amount iTunesSearch will return maximum 10 results.
     * @param navn Artist name to look for
     */
    iTunesSearch(String navn) {
        this(navn, 10);
    }
    /**
     * Call the api with search parameters and maximum amount of tracks.
     * @param navn Artist name to look for
     * @param amo Maximum amoung of tracks returned, 0 for maximum
     */
    iTunesSearch(String navn, int amo) {
        try {
            URL url = new URL("https://itunes.apple.com/search?term="           // Create a new URL
                    +navn.replaceAll("\\s", "+")                                // iTunes uses + for spaces
                    +"&limit="+amo                                              // Set limit
            );
            
            URLConnection request = url.openConnection();                       // Create a request to get data
            request.connect();                                                  // Get data
            
            BufferedReader r = new BufferedReader(                              //Read the TXT-Response
                    new InputStreamReader(                                      //Through a InputReader
                            request.getInputStream(), Charset.forName("UTF-8")  //As UTF-8
                    )
            );
            StringBuilder sb = new StringBuilder();                             //Use stringbuilder to
            String line; while ((line = r.readLine()) != null) {                //Reconstruct the data line by line
                sb.append(line);
            }
            JSONObject json = new JSONObject(sb.toString());                    //Convert stringbuilder to a string and then into a JSON object
            
            o = json.getJSONArray("results");                                   //Extract the results array
            n = json.getInt("resultCount");                                     //Extract the amount of results
        } catch (MalformedURLException ex) {
            Logger.getLogger(iTunesSearch.class.getName()).log(Level.SEVERE, null, ex);
            n = -1;
        } catch (IOException ex) {
            Logger.getLogger(iTunesSearch.class.getName()).log(Level.SEVERE, null, ex);
            n = -1;
        }
    }
    
    /**
     * Get a array with all results from API request.
     * @return a JSONArray with all results.
     * @throws java.lang.Exception
     */
    public JSONArray getArray() throws Exception {
        if(n>0)return o;
        else if(n==0) throw new Exception("No results");
        else throw new Exception("Somethong went wrong");
    }
    
}