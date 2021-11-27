package connection;

import java.util.Vector;

public class Line implements LineInterface{
    protected LineName name;
    protected Vector<Time> startingTimes;
    protected Vector<LineSegment> lineSegments;
    protected StopName firstStop;

    public Line() {
    }

    public Line(LineName lineName) {
        this.name = lineName;
        startingTimes = new Vector<>();
        lineSegments = new Vector<>();
    }

    public Line(LineName lineName, Vector<LineSegment> segments, Vector<Time> startingTimes) {
        this.name = lineName;
        this.startingTimes = startingTimes;
        lineSegments = segments;
    }

    public LineName getName(){
        return name;
    }

    @Override
    public void updateReachable(StopName stop, Time time) {
    }

    @Override
    public StopName updateCapacityAndGetPreviousStop(StopName stop, Time time) {
        return null;
    }
}
