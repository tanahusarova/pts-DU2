package connection;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LineSegment {
//    private StopName from;
    private StopInterface nextStop;
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


    public Pair<Time, StopName> nextStop(Time time){
        return new Pair(new Time(time.time + timeToNextStop.time), nextStop.getName());
    }

    public Tuple<Time, StopName, Boolean> nextStopAndUpdateReachable(Time time){
        Boolean tmp = numberOfPasengers.get(time) < capacity;

        if (tmp == true) nextStop.updateReachableAt(new Time(time.time + timeToNextStop.time), Optional.ofNullable(lineName));

        return new Tuple<>(new Time(time.time + timeToNextStop.time), nextStop.getName(), tmp);

    }

    public StopInterface getNextStop() {
        return nextStop;
    }

    public void incrementCapacity(Time time){
        numberOfPasengers.put(time, numberOfPasengers.get(time) + 1);
    }
}
