package conection;

import java.util.Vector;

public class Line {
    private LineName lineName;
    private Vector<Time> startingTimes;
    private StopName firstStop;

    public Line(LineName lineName, Vector<Time> startingTimes, StopName firstStop) {
        this.lineName = lineName;
        this.startingTimes = startingTimes;
        this.firstStop = firstStop;
    }
}
