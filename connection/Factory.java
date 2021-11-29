package connection;

public interface Factory {
    StopInterface createStop(StopName stopname);
    LineInterface createLine(LineName lineName);
    LineSegmentInterface createLineSegment(Stop nextStop, TimeOffset timeToNextStop, int capacity, LineName lineName);
}
