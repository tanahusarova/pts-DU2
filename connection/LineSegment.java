package connection;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LineSegment implements LineSegmentInterface{
    protected StopInterface nextStop;
    protected TimeOffset timeToNextStop;
    protected int capacity;
    protected LineName lineName;
    protected Map<Time, Integer> numberOfPasengers;

    public LineSegment(Stop nextStop, TimeOffset timeToNextStop, int capacity, LineName lineName) {
        this.nextStop = nextStop;
        this.timeToNextStop = timeToNextStop;
        this.capacity = capacity;
        this.lineName = lineName;
        this.numberOfPasengers = new HashMap<>();
    }


    public Pair<Time, StopName> nextStop(Time time){
        //opat intellij na neparametrizovany Pair :D
        return new Pair(new Time(time.time + timeToNextStop.time), nextStop.getName());
    }

    public Tuple<Time, StopName, Boolean> nextStopAndUpdateReachable(Time time){
        if (!numberOfPasengers.containsKey(time)) {
            numberOfPasengers.put(time, 0);
        }

        if (numberOfPasengers.get(time) < capacity) nextStop.updateReachableAt(new Time(time.time + timeToNextStop.time), lineName);

        return new Tuple<>(new Time(time.time + timeToNextStop.time), nextStop.getName(), numberOfPasengers.get(time) < capacity);

    }

    public StopInterface getNextStop() {
        return nextStop;
    }

}
