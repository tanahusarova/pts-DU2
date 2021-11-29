package connection;

import javax.management.Query;
import java.util.*;

public class Stops {
    private Set<StopName> stopNames;
    private Set<StopInterface> stops;
    private Factory factory;

/*
    public Stops(String fileName) {
        this.stopNames = new HashSet<>();
        this.stops = new HashSet<>();
        factory = new FactoryDatabase(fileName);
    }

 */

    public Stops(Set<StopName> stopNames, Set<StopInterface> stops) {
        this.stopNames = stopNames;
        this.stops = stops;
    }

    public Stops(Factory factory) {
        stopNames = new HashSet<>();
        stops = new HashSet<>();
        this.factory = factory;

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
        if (!stopNames.contains(stopName)) {
            stop = factory.createStop(stopName);
            stops.add(stop);
            stopNames.add(stopName);
        }
        else {
            for (StopInterface s : stops) {
                if (s.getName() == stopName) {
                    stop = s;
                    break;
                }
            }
        }
        return stop;
    }

    public boolean setStartingStop(StopName stopName, Time time){

        StopInterface stop = getStop(stopName);
        stop.updateReachableAt(time, null);
        return true;
    }

    public Vector<LineName> getLines(StopName stopName){
        StopInterface stop = getStop(stopName);
        return stop.getLines();
    }

    public Pair<Time, LineName> getReachableAt(StopName stop){
        StopInterface tmpStop = getStop(stop);
        return new Pair(tmpStop.getReachableAt(), tmpStop.getReachableVia());
    }

    public StopInterface earliestReachableAfter(Time time){
        Time earliest = null;
        StopInterface result = null;

        for (StopInterface s : stops){
            if (s.getReachableAt().isPresent() && s.getReachableAt().get().time >= time.time
                    && (earliest == null || s.getReachableAt().get().time <= earliest.time)){

                earliest = s.getReachableAt().get();
                result = s;
            }
        }

        return result;
    }



    public void clean(){
        for (StopInterface s: stops){
            s.updateReachableAt(null, null);
        }
    }

}
