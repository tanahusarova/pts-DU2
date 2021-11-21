package connection;

import java.util.HashMap;
import java.util.Optional;
import java.util.Vector;

public interface StopInterface {
    void updateReachableAt(Time time, Optional<LineName> line);
    HashMap<Time, LineName> getReachableAt();
    Vector<LineName> getLines();
    StopName getName();
}
