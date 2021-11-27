package connection;

import java.util.List;
import java.util.Vector;

public class FactoryInMemory implements Factory {

    private List<StopInterface> stops;
    private List<LineInterface> lines;

   /* public FactoryInMemory() {
    }

    @Override
    public StopInterface createStop(StopName stopName) {
        return new StopInMemory(stopName);
    }

    @Override
    public LineInterface createLine(LineName lineName) {
        return new LineInMemory(lineName);
    }

    */

    public FactoryInMemory(List<StopInterface> stops, List<LineInterface> lines) {
        this.stops = stops;
        this.lines = lines;
    }

    @Override
    public StopInterface createStop(StopName stopName) {
        StopInterface stop = null;
        for (StopInterface s: stops){
            if (s.getName() == stopName) {
                stop = s;
                break;
            }
        }

        Vector<LineName> linesContStop = new Vector();

        for (LineInterface l : lines){
            if (((LineInMemory) l).contains(stopName)) linesContStop.add(l.getName());
        }

        stop.setLines(linesContStop);
        return null;
    }

    @Override
    public LineInterface createLine(LineName lineName) {
        LineInterface line = null;
        for (LineInterface l: lines){
            if (l.getName() == lineName) {
                line = l;
                return l;
            }
        }
        return null;
    }

}