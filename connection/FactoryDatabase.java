package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryDatabase implements Factory {
    private Connection connection;

    public FactoryDatabase(Connection connection) {
        this.connection = connection;
    }

    public FactoryDatabase(){
        this.connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:ptsDU2.db");
            System.out.println("Connected!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public StopInterface createStop(StopName stopname) {
        return new StopDatabase(stopname, connection);
    }

    @Override
    public LineInterface createLine(LineName lineName) {
        return new LineDatabase(connection, lineName);
    }

    @Override
    public LineSegmentInterface createLineSegment(Stop nextStop, TimeOffset timeToNextStop, int capacity, LineName lineName) {
        return new LineSegmentDatabase(nextStop, timeToNextStop, capacity, lineName, connection);
    }


}
