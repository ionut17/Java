package lab5.controller;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lab5.model.Song;

/**
 *
 * @author Anca
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "favorites")
public class Favorites {
    
    @XmlElement(name = "song", type = Song.class)
     private List<Song> songs = new ArrayList<>();
     
     public List<Song> getSongs(){
         return songs;
     }
     
     public void setSongs(List<Song> favoriteSongs){
         this.songs=favoriteSongs;
     }
}
