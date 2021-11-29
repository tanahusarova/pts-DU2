package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LineSegmentDatabase extends LineSegment{
    private Connection connection;

    public LineSegmentDatabase(Stop nextStop, TimeOffset timeToNextStop, int capacity, LineName lineName, Connection connection) {
        super(nextStop, timeToNextStop, capacity, lineName);
        this.connection = connection;
    }

    public void incrementCapacity(Time time){
        //zapisovanie do databazy
        String sql = "UPDATE lineStops SET numberOfPasengers = numberOfPasengers + 1, "
                + "WHERE stopName = " + nextStop.getName().toString() +
                "AND stopTime = " + time.time.toString();

        try (Connection conn = connection;
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

             pstmt.executeUpdate();

        } catch ( SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}


