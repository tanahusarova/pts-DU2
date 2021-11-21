package connection;

import java.util.HashMap;
import java.util.Optional;
import java.util.Vector;

public class StopInMemory implements StopInterface{
    private StopName name;

    public StopInMemory(StopName stopName) {
        name = stopName;
    }

    @Override
    public void updateReachableAt(Time time, Optional<LineName> line) {

    }

    @Override
    public HashMap<Time, LineName> getReachableAt() {
        return null;
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
