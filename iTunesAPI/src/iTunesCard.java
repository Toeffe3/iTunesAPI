
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emilk
 */
public class iTunesCard {
    public String cover;
    public String preview;
    public String song_name;
    public String artist_name;
    public String genre;
    
    public int index;
    
    public iTunesCard (String coverin, String previewin, String song_namein, String artist_namein,String genrein){
        cover=coverin;
        preview=previewin;
        song_name=song_namein;
        artist_name=artist_namein;
        genre=genrein;
        index=indexin;
    
        JPanel trackcard = new JPanel(new GridBagLayout(), false);
        trackcard.setBackground(Color.lightGray);
        
        trackcard.setBounds(10, 10+(index*100), 100, 749);
    }
    
    public class UserDetail extends JPanel{
        
    }
}
    
    
    

