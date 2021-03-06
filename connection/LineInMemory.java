package connection;

import java.util.Vector;
import java.util.concurrent.ForkJoinTask;

public class LineInMemory extends Line{


    public LineInMemory(LineName name) {
        super(name);
    }

    public LineInMemory(LineName name, Vector<LineSegmentInterface> lineSegments, StopName firstStop, Vector<Time> startingTimes) {
        super(name);
        //tu nebolo lepsie len pouzit kontruktor s parametrom? potom by tam nemusel byt ten typecast
        //aj ked neviem ako to funguje pri vectore
        this.lineSegments = lineSegments;
        this.firstStop = firstStop;
        this.startingTimes = startingTimes;

    }

    public boolean contains(StopName stop){
        if (firstStop == stop) return true;
        for (int i = 0; i < lineSegments.size(); i++){
            if (lineSegments.get(i).getNextStop().getName() == stop) return true;
        }
        return false;
    }

}
