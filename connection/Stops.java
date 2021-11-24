package connection;

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

    public Stops(Factory factory) {
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

        stop.updateReachableAt(time, null);
        return true;
    }

    public Vector<LineName> getLines(StopName stopName){
        StopInterface stop = getStop(stopName);
        return stop.getLines();
    }

    public Map<Time, LineName> getReachableAt(StopName stop){
        return null;
    }

    public Pair<StopName, Time> earliestReachableAfter(Time time){
        Time earliest = new Time(time.time);
        StopName stopName = new StopName(null);
        for (StopInterface s : stops){
            if (s.getReachableAt().isPresent() && s.getReachableAt().get().time >= time.time
                    && s.getReachableAt().get().time <= earliest.time){

                stopName = s.getName();
                earliest = s.getReachableAt().get();
            }
        }
        return new Pair<>(stopName, earliest);
    }

}
