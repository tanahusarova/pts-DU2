package connection;

import java.util.Vector;

public interface LineInterface {

    LineName getName();
    void updateReachable(Vector<LineName> lines, StopName stop, Time time);
    StopName updateCapacityAndGetPreviousStop(StopName stop, Time time);
}
