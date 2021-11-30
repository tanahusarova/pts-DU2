package connection;

import java.util.List;
import java.util.Vector;

public class FactoryInMemory implements Factory {

    private List<StopInterface> stops;
    private List<LineInterface> lines;


    public FactoryInMemory(List<StopInterface> stops, List<LineInterface> lines) {
        this.stops = stops;
        this.lines = lines;
    }

    @Override
    public StopInterface createStop(StopName stopName) {
        StopInterface stop = null;
        for (StopInterface s: stops){
            if (s.getName().equals(stopName)) {
                stop = s;
                break;
            }
        }

        Vector<LineName> linesContStop = new Vector();

        for (LineInterface l : lines){
            if (l.contains(stopName)) linesContStop.add(l.getName());
        }

        stop.setLines(linesContStop);
        return stop;
    }

    @Override
    public LineInterface createLine(LineName lineName) {
        for (LineInterface l: lines){
            if (l.getName().equals(lineName)) {
                return l;
            }
        }
        return null;
    }

    @Override
    public LineSegmentInterface createLineSegment(Stop nextStop, TimeOffset timeToNextStop, int capacity, LineName lineName) {
        return new LineSegmentInMemory(nextStop, timeToNextStop, capacity, lineName);

    }


}
