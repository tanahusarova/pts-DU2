package connection;

public class LineSegmentInMemory extends LineSegment{

    public LineSegmentInMemory(Stop nextStop, TimeOffset timeToNextStop, int capacity, LineName lineName) {
        super(nextStop, timeToNextStop, capacity, lineName);
    }

    public void incrementCapacity(Time time){
        numberOfPasengers.put(time, numberOfPasengers.get(time) + 1);
    }

}
