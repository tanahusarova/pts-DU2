package connection;

import java.util.HashMap;
import java.util.Map;

public class LineSegment {
//    private StopName from;
    private Stop nextStop;
    private TimeOffset timeToNextStop;
    private int capacity;
    private LineName lineName;
    private Map<Time, Integer> numberOfPasengers;

    public LineSegment(Stop nextStop, TimeOffset timeToNextStop, int capacity, LineName lineName) {
        this.nextStop = nextStop;
        this.timeToNextStop = timeToNextStop;
        this.capacity = capacity;
        this.lineName = lineName;
        this.numberOfPasengers = new HashMap<>();
    }


}
