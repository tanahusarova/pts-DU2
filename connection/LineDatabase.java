package connection;

import java.util.Vector;

public class LineDatabase implements LineInterface{
    private LineName name;
    public LineDatabase(LineName name) {
        this.name = name;
    }

    @Override
    public LineName getName() {
        return name;
    }

    @Override
    public void updateReachable(Vector<LineName> lines, StopName stop, Time time) {

    }

    @Override
    public StopName updateCapacityAndGetPreviousStop(StopName stop, Time time) {
        return null;
    }
}
