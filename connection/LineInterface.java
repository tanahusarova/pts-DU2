package connection;

import java.util.List;
import java.util.Vector;

public interface LineInterface {

    LineName getName();
    void updateReachable(StopName stop, Time time);
    StopName updateCapacityAndGetPreviousStop(StopName stop, Time time);
    boolean contains(StopName stopName);
    List<StopInterface> update(StopName stop, Time time);
    }
