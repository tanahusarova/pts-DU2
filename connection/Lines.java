package connection;

import java.util.*;

public class Lines {
    private Set<LineInterface> lines;
    private Set<LineName> lineNames;
    private Factory factory;

    public Lines(Factory factory) {
        this.lines = new HashSet<>();
        this.lineNames = new HashSet<>();
        this.factory = factory;
    }

    public Lines(Set<LineInterface> lines, Set<LineName> lineNames){
        this.lines = lines;
        this.lineNames = lineNames;
    }

    private LineInterface getLine(LineName lineName){
        LineInterface line = null;
        if (!lineNames.contains(lineName)) {
            line = factory.createLine(lineName);
            this.lines.add(line);
            this.lineNames.add(lineName);
        }
        else {
            for (LineInterface l : lines) {
                if (l.getName().equals(lineName)) line = l;
            }
        }
        return line;
    }

    public void updateReachable(Vector<LineName> lines, StopName stop, Time time){

        for (LineName l : lines) {
            LineInterface line = getLine(l);
            line.updateReachable(stop, time);
        }

    }

    public List<StopInterface> update(Vector<LineName> lines, StopName stop, Time time){
        ArrayList<StopInterface> result = new ArrayList<>();

        for (LineName l : lines) {
            LineInterface line = getLine(l);
            result.addAll(line.update(stop, time));
        }

        return result;

    }

    public StopName updateCapacityAndGetPreviousStop(LineName line, StopName stop, Time time){
        LineInterface tmpLine = null;
        for (LineInterface l : lines){
            if (l.getName().equals(line)){
                tmpLine = l;
                break;
            }
        }
        return tmpLine.updateCapacityAndGetPreviousStop(stop, time);

    }

    public void clean(){
        lines.clear();
        lineNames.clear();
    }

    public Set<LineInterface> getLines() {
        return lines;
    }
}
