package connection;

import java.sql.Connection;

public class LineDatabase implements LineInterface{
    private LineName name;
    private Connection connection;

    public LineDatabase(Connection connection, LineName lineName) {
        this.connection = connection;
        this.name = lineName;
    }

    private

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
