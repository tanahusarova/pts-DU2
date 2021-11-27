package connection;

import javax.net.ssl.ExtendedSSLSession;
import java.sql.Connection;
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
        ORDER BY stopOrder;


         */
    }

    @Override
    public void updateReachable(StopName stop, Time time) {

    }

    @Override
    public StopName updateCapacityAndGetPreviousStop(StopName stop, Time time) {
        return null;
    }

   /* private void readLine(){
        SQLQuery sqlQuery = new connection.create
    }

    */
}
