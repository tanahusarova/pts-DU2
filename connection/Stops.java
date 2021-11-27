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

    /*
    private void search(Time time){
        this.earliestReachableAfter.clear();
        Set<StopName> used = new HashSet<>();
        Time earliest = new Time(time.time);
        StopName stopName = new StopName(null);

        for (int i = 0)
        {
            for (StopInterface s : stops) {
                if (s.getReachableAt().isPresent() && s.getReachableAt().get().time >= time.time
                        && s.getReachableAt().get().time <= earliest.time && !used.contains(s.getName())) {

                    stopName = s.getName();
                    earliest = s.getReachableAt().get();
                    this.earliestReachableAfter.add(new Pair<>(stopName, earliest));
                    used.add(stopName);
                }
            }
        }
    }

     */


    public List<StopInterface> earliestReachableAfter(Time time, int count) {
        Time earliest = null;
        StopInterface tmpStop = null;
        List result = new ArrayList();

        for (int i = 0; i < count; i++) {
            for (StopInterface s : stops) {
                if ((earliest == null || (s.getReachableAt().isPresent()
                        && s.getReachableAt().get().time >= time.time
                        && s.getReachableAt().get().time <= earliest.time))
                        && !result.contains(s)) {

                    tmpStop = s;
                    earliest = s.getReachableAt().get();
                }
            }
            result.add(tmpStop);
            tmpStop = null;
            earliest = null;
        }

        return result;

    }

    public void clean(){
        for (StopInterface s: stops){
            s.updateReachableAt(null, null);
        }
    }

}
