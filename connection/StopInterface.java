package connection;

import java.util.Optional;
import java.util.Vector;

public interface StopInterface {
    void updateReachableAt(Time time, LineName line);
    Optional<Time> getReachableAt();
    Optional<LineName> getReachableVia();
    Vector<LineName> getLines();
    StopName getName();
    void setLines(Vector<LineName> linesContStop);
}
