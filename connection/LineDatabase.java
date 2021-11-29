package connection;

import javax.net.ssl.ExtendedSSLSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
//import org.hibernate.Session;


public class LineDatabase extends Line{
 //   private Session connection;
    private Connection connection;


    public LineDatabase(Connection connection, LineName lineName) {
        super(lineName);
        this.connection = connection;
        /* startingTimes

        SELECT lineStartTime
        FROM lines
        WHERE lineName = (this.name)
        ORDER BY 1;

        firstStop

        SELECT lineStartStop
        FROM lines
        WHERE lineName = (this.name)

        lineSegments

        SELECT stopTimeDiff, stopName
        FROM lines
        JOIN linesStops ON lines.lineId = linesStops.lineId
        WHERE lineName = (this.name)
        ORDER BY stopOrder
         */

        //startingTimes

        String sqlStartingTimes = "SELECT lineStartTime" + "FROM lines" +
                     "WHERE lineName = " + lineName.toString() +
                     "ORDER BY 1";

        Vector<Time> startingTimes = new Vector<>();

        try (Connection conn = this.connection;
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sqlStartingTimes)){

            while (rs.next()) {
                startingTimes.add(new Time(rs.getInt("lineStartTime")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //lineSegments

        String sqlLineSegments = "SELECT stopTimeDiff, stopName, capacity, lineName" + "FROM lines" +
                "JOIN linesStops ON lines.lineId = linesStops.lineId" +
                "WHERE lineName = " + lineName.toString() +
                "ORDER BY stopOrder";

        Vector<LineSegmentInterface> lineSegments = new Vector<>();

        try (Connection conn = this.connection;
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sqlLineSegments)){

            while (rs.next()) {
                lineSegments.add( new LineSegmentDatabase(new Stop(new StopName(rs.getString("stopName"))),
                        new TimeOffset(rs.getInt("stopTimeDiff")), rs.getInt("capacity"),
                        new LineName(rs.getString("lineName")), connection));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //firstStop

        String sqlFirstStop = "SELECT DISTINCT lineStarStop" + "FROM lines" +
                "WHERE lineName = " + lineName.toString();

        StopName firstStop = null;

        try (Connection conn = this.connection;
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sqlFirstStop)){

            firstStop = new StopName(rs.getString("lineStartStop"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        this.lineSegments = lineSegments;
        this.startingTimes = startingTimes;
        this.firstStop = firstStop;

    }

    ///////dorobit increment capacity

    /*

    @Override
    public StopName updateCapacityAndGetPreviousStop(StopName stop, Time time) {
        StopName previousStop = null;
        for (LineSegmentInterface ls : lineSegments){
            if (ls.getNextStop().getName() == stop){
                ls.getNextStop().updateReachableAt(time, name);
                ls.incrementCapacity(time);
                return previousStop;
            }
            previousStop = ls.getNextStop().getName();
        }
        return null;
    }


    String sql = "UPDATE warehouses SET name = ? , "
                + "capacity = ? "
                + "WHERE id = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, name);
            pstmt.setDouble(2, capacity);
            pstmt.setInt(3, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

     */


    /*
    public StopName updateCapacityAndGetPreviousStop(StopName stop, Time time) {
        StopName previousStop = null;
        for (LineSegment ls : lineSegments){
            if (ls.getNextStop().getName() == stop){
                ls.getNextStop().updateReachableAt(time, name);
                ls.incrementCapacity(time);
                return previousStop;
            }
            previousStop = ls.getNextStop().getName();
        }
        return null;
    }

     */

}
