package connection;

import javax.management.Query;
import java.util.*;

public class Stops {
    private Set<StopName> stopNames;
    private Set<StopInterface> stops;
    private Factory factory;
    private Queue<Pair> earliestReachableAfter;
    private boolean generateNew;

/*
    public Stops(String fileName) {
        this.stopNames = new HashSet<>();
        this.stops = new HashSet<>();
        factory = new FactoryDatabase(fileName);
    }

 */

    public Stops(Factory factory) {

        this.factory = factory;
        generateNew = true;
        this.earliestReachableAfter = new LinkedList<>();

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


    public Pair<StopName, Time> earliestReachableAfter(Time time){
        if (generateNew) search(time);
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

    public void setGenerateNew(){
        generateNew = true;
    }

}
