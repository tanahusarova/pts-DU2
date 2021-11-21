package connection;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class Lines {
    private Set<LineInterface> lines;
    private Set<LineName> lineNames;
    private Factory factory;

    public Lines() {
        this.lines = new HashSet<>();
        this.lineNames = new HashSet<>();
        factory = new FactoryInMemory();
    }

    public Lines(String fileName) {
        this.lines = new HashSet<>();
        this.lineNames = new HashSet<>();
        factory = new FactoryDatabase();
    }

    public void setLines(Set<LineInterface> lines){
        this.lines.clear();
        this.lines.add((LineInterface) lines);
        this.lineNames.clear();
        for (LineInterface l : lines){
            lineNames.add(l.getName());
        }
    }

    private LineInterface getLine(LineName lineName){
        LineInterface line = null;
        for (LineInterface l : lines){
            if (l.getName() == lineName) line = l;
        }
        return line;
    }

    public void updateReachable(Vector<LineName> lines, StopName stop, Time time){


    }

    public StopName updateCapacityAndGetPreviousStop(LineName line, StopName stop, Time time){
        return null;
    }

    public void clean(){
        lines.clear();
        lineNames.clear();
    }

}
