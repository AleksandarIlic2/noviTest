package bus;

import java.util.ArrayList;
import java.util.List;
 
public class StationList {
 
	private List<Station> stations;
	private int currentStationIndex;
 
	 public StationList() {
	        stations = new ArrayList<>();
	        stations.add(new Station("Stanica 1"));
	        stations.add(new Station("Stanica 2"));
	        stations.add(new Station("Stanica 3"));
	        stations.add(new Station("Stanica 4"));
	        currentStationIndex = -1; // Postavljamo indeks na -1 jer ćemo ga prvo inkrementirati pri pozivu getNext()
	    }
 
	    public synchronized Station getNext() {
	        if (!stations.isEmpty()) {
	            currentStationIndex = (currentStationIndex + 1) % stations.size(); // Inkrementiramo indeks stanice
	            return stations.get(currentStationIndex);
	        }
	        return null;
	    }
}