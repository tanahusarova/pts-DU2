package connection;

public interface LineSegmentInterface {
    public StopInterface getNextStop();
    public void incrementCapacity(Time time);
    public Pair<Time, StopName> nextStop(Time time);
    public Tuple<Time, StopName, Boolean> nextStopAndUpdateReachable(Time time);
    }
