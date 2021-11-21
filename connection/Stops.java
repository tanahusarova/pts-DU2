package connection;

import java.util.*;

public class Stops {
    private Set<StopName> stopNames;
    private Set<StopInterface> stops;
    private Factory factory;


    public Stops(String fileName) {
        this.stopNames = new HashSet<>();
        this.stops = new HashSet<>();
        factory = new FactoryDatabase();
    }

    public Stops() {
        this.stopNames = new HashSet<>();
        this.stops = new HashSet<>();
        factory = new FactoryInMemory();
    }

    public void setStops(Set<StopInterface> stops){
        this.stops.clear();
        this.stops.add((StopInterface) stops);
        this.stopNames.clear();
        for (StopInterface s : stops){
            stopNames.add(s.getName());
        }
    }

    private StopInterface getStop(StopName stopName){
        StopInterface stop = null;
        for (StopInterface s : stops){
            if (s.getName() == stopName) {
                stop = s;
                break;
            }
        }
        return stop;
    }

    public boolean setStartingStop(StopName stopName, Time time){
        StopInterface stop = null;

        if (!stopNames.contains(stopName)) {
            stop = factory.createStop(stopName);
            stops.add(stop);
            stopNames.add(stopName);
        }

        else stop = getStop(stopName);

        stop.updateReachableAt(new Time(1), null);
        return true;
    }

    public Vector<LineName> getLines(StopName stopName){
        StopInterface stop = getStop(stopName);
        return stop.getLines();
    }

    public Map<Time, LineName> getReachableAt(StopName stop){
        return null;
    }


}
