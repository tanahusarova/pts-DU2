package connection;

import java.util.HashMap;
import java.util.Optional;
import java.util.Vector;

public class StopInMemory implements StopInterface{
    private StopName name;
    private Optional<Time> reachableAt;
    private Optional<LineName> reachableVia;
    private Vector<LineName> lines;

    public StopInMemory(StopName stopName) {
        name = stopName;
    }

    @Override
    public void updateReachableAt(Time time, Optional<LineName> line) {

    }

    @Override
    public Optional<Time> getReachableAt() {
        return reachableAt;
    }

    @Override
    public Vector<LineName> getLines() {
        return null;
    }

    @Override
    public StopName getName() {
        return name;
    }

}
