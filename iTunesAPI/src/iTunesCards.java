import java.util.*;
import java.util.logging.*;
import org.json.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import javax.swing.JEditorPane;
import javax.swing.table.*;
import javax.imageio.ImageIO;

/**
 * Creates cards (table rows) from iTunesSearch.
 * @author emilk
 */
public class iTunesCards {

    public static void insertIntoTable(JTable t, JSONArray a) {

        Object[][] table = to2DArray(a);
        DefaultTableModel m = new DefaultTableModel(table, new Object[] {"Cover", "Sang", "Artist", "Album", "Udgivelseår", "Forsmag"}) {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
      	t.setModel(m);
      	t.setRowHeight(100);
      	t.setEnabled(false);
        ButtonColumn buttonColumn = new ButtonColumn(t, 5);
      	//t.setBackground(Color.LIGHT_GRAY);
    }

    private static Object[][] to2DArray(JSONArray a) {

        ArrayList<ArrayList<Object>> s = new ArrayList<>();

        for(Object o : a) {
            JSONObject track = (JSONObject)o;

            ArrayList<Object> row = new ArrayList<Object>();

            System.out.println(track.get("trackName"));
            try {
                row.add((ImageIcon)new ImageIcon(ImageIO.read(new URL((String)track.get("artworkUrl100")))));
      	    } catch (MalformedURLException ex) {
      		      Logger.getLogger(iTunesCards.class.getName()).log(Level.SEVERE, null, ex);
      	    } catch (IOException ex) {
      		      Logger.getLogger(iTunesCards.class.getName()).log(Level.SEVERE, null, ex);
      	    }
            row.add(track.get("trackName"));
            row.add(track.get("artistName"));
            row.add(track.get("collectionName"));
            row.add(((String)track.get("releaseDate")).replaceAll("(\\d{4})-(\\d{2})-(\\d{2})T\\d{2}:\\d{2}:\\d{2}+Z","$3/$2 $1"));

            Button b = new Button("Lyt");
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent a) {
                  JEditorPane jep = new JEditorPane();
                  jep.setEditable(false);
                  try {
                      jep.setPage((String)track.get("previewUrl"));
                  } catch (IOException ex) {
                      jep.setContentType("audio/x-m4a");
                      jep.setText("<html>Kunne ikke indlæse</html>");
                  }
                  JScrollPane scrollPane = new JScrollPane(jep);
                  JFrame f = new JFrame("Forsmag");
                  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                  f.getContentPane().add(scrollPane);
                  f.setPreferredSize(new Dimension(800,600));
                  f.setVisible(true);
                }
            });
            row.add(b);

            s.add(row);
        }

        Object[][] data = new Object[s.size()][6];
        for(int i = 0; i < data.length; i++) {                                     //Convert the arraylist to static 2D array
            for(int j = 0; j < data[0].length; j++) {
		            data[i][j] = s.get(i).get(j);
            }
        }
        return data;
    }
}
