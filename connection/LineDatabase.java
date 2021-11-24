package connection;

import java.util.Vector;

public class LineDatabase implements LineInterface{
    private LineName name;
    private String fileName;

    public LineDatabase(LineName name, String fileName) {
        this.name = name;
        this.fileName = fileName;
    }

    @Override
    public LineName getName() {
        return name;
    }

    @Override
    public void updateReachable(StopName stop, Time time) {

    }

    @Override
    public StopName updateCapacityAndGetPreviousStop(StopName stop, Time time) {
        return null;
    }
}
