package connection;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class Lines {
    private Set<LineInterface> lines;
    private Set<LineName> lineNames;
    private Factory factory;

    public Lines(Factory factory) {
        this.lines = new HashSet<>();
        this.lineNames = new HashSet<>();
        this.factory = factory;
    }
/*
    public Lines(String fileName) {
        this.lines = new HashSet<>();
        this.lineNames = new HashSet<>();
        factory = new FactoryDatabase(fileName);
    }

 */

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

        for (LineName l : lines) {
            LineInterface line = null;
            if (!lineNames.contains(l)) {
                line = factory.createLine(l);
                this.lines.add(line);
                this.lineNames.add(l);
            }

            else line = getLine(l);
            line.updateReachable(stop, time);
        }

        return;

    }

    public StopName updateCapacityAndGetPreviousStop(LineName line, StopName stop, Time time){
        LineInterface tmpLine = null;
        for (LineInterface l : lines){
            if (l.getName() == line){
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

}
