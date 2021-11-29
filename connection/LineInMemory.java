package connection;

import java.util.Vector;
import java.util.concurrent.ForkJoinTask;

public class LineInMemory extends Line{


    public LineInMemory(LineName name) {
        super(name);
    }

    public LineInMemory(LineName name, Vector<LineSegmentInMemory> lineSegments, StopName firstStop, Vector<Time> startingTimes) {
        super(name);
        this.lineSegments = (Vector<LineSegmentInterface>) lineSegments.clone();
        this.firstStop = firstStop;
        this.startingTimes = (Vector<Time>) startingTimes.clone();

    }

    public boolean contains(StopName stop){
        if (firstStop == stop) return true;
        for (int i = 0; i < lineSegments.size(); i++){
            if (lineSegments.get(i).getNextStop().getName() == stop) return true;
        }
        return false;
    }

}
