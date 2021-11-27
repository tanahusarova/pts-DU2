package connection;

import java.sql.Connection;

public class FactoryDatabase implements Factory {
    private Connection connection;

    public FactoryDatabase(Connection connection) {
        this.connection = connection;
    }

    @Override
    public StopInterface createStop(StopName stopname) {
        return new StopDatabase(stopname, connection);
    }

    @Override
    public LineInterface createLine(LineName lineName) {
        return new LineDatabase(connection, lineName);
    }




}
