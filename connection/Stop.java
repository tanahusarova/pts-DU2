package connection;

import java.util.HashMap;
import java.util.Optional;
import java.util.Vector;

public class Stop implements StopInterface{
    private StopName stopName;
    private Optional<Time> reachableAt;
    private Optional<LineName> reachableVia;
    private Vector<LineName> lines;

    public Stop(StopName stopName) {
        this.stopName = stopName;
    }

    public void updateReachableAt(Time time, Optional<LineName> line){
        if (time.time < reachableAt.get().time) {
            reachableAt = Optional.of(time);
            reachableVia = line;
        }
    }

    public Optional<Time> getReachableAt(){
        return null;
    }

    public Vector<LineName> getLines(){

        return null;
    }

    public StopName getName() {
        return stopName;
    }
}
