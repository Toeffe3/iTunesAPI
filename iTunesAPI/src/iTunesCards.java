import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Creates cards (table rows) from iTunesSearch.
 * @author emilk
 */
public class iTunesCards {
    
    public static void insertIntoTable(JTable t, JSONArray a) {
        
        String[][] table = to2DArray(a);
        t.setModel(new DefaultTableModel(table, new String[]{"Cover", "Sang", "Artist", "Album", "Udgivelseår", "Forsmag"}));
        
    }
    
    private static String[][] to2DArray(JSONArray a) {

        ArrayList<ArrayList<String>> s = new ArrayList<>();
        
        for(Object o : a) {
            JSONObject track = (JSONObject)o;
            
            ArrayList<String> row = new ArrayList<>();
            
            System.out.println(track.get("trackName"));
            
            row.add((String)track.get("artworkUrl100"));
            row.add((String)track.get("trackName"));
            row.add((String)track.get("artistName"));
            row.add((String)track.get("collectionName"));
            row.add((String)track.get("releaseDate"));
            row.add((String)track.get("previewUrl"));
            
            s.add(row);
        }
        
        String[][] data = new String[s.size()-1][6];
        for(int i = 0; i < data.length; i++) {                                     //Convert the arraylist to static 2D array
            for(int j = 0; j < data[0].length; j++) {
                data[i][j] = s.get(i).get(j);
            }
        }
        return data;
    }
}
    
    
    

