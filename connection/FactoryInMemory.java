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

    //nemala by factory vytvarat nove instancie? T.j return new Stop(...)
    //akoze trieda je v pohode len to asi nie je factory
    //ja by som tieto operacie zahrnul do samotnych stops a lines, napriklad stops.getStopByName() ale samozrejme 100 ludi 100 chuti
    
    
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

    //tato metoda sa niekde vola?
    //toto ma byt myslene tak ze zvonka sa zavola tato metoda s vhdodnymi parametrami?
    //lebo sa mi zda zvlastne ze uz dostanes v konstruktore vytvorene linky ktore uz obsahuju svoje linseSegmenty
    //cize potom by tato metoda bola zbytocna ale neviem ci to v zadani nebolo myslene tak ze tie stopky a linky mas vytvorit
    //teda aspon ja som to tak pochopil
    
    //E: zda sa mi ze to ani nepouzivam v hlavnom kode, ale niekde pri testoch, nech mam vsetko vo factories pokope 
    @Override
    public LineSegmentInterface createLineSegment(Stop nextStop, TimeOffset timeToNextStop, int capacity, LineName lineName) {
        return new LineSegmentInMemory(nextStop, timeToNextStop, capacity, lineName);

    }


}
