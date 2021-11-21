package connection;

public interface Factory {
    StopInterface createStop(StopName stopname);
    LineInterface createLine(LineName lineName);
}
