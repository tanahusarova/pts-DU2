package conection;

import java.security.KeyPair;
import java.util.*;

public class Stops {
    private Set<StopName> stopNames;
    private Set<Stop> stops;


    public Stops(Set<StopName> stops) {
        this.stopNames = stops;
        this.stops = new HashSet<>();
    }

    private Stop getStop(StopName stopName){
        Stop stop = null;
        for (Stop s : stops){
            if (s.getName() == stopName) {
                stop = s;
                break;
            }
        }
        return stop;
    }

    public boolean setStartingStop(StopName stopName, Time time){
        Stop stop = null;

        if (!stopNames.contains(stopName)) {
            stop = new Stop(stopName);
            stops.add(stop);
            stopNames.add(stopName);
        }

        else stop = getStop(stopName);

        stop.updateReachableAt(new Time(1), null);
        return true;
    }

    public Vector<LineName> getLines(StopName stopName){
        Stop stop = getStop(stopName);
        return stop.getLines();
    }

    public Map<Time, LineName> getReachableAt(StopName stop){
        return null;
    }
}
