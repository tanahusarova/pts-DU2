package connection;

import java.util.Vector;
import java.util.concurrent.ForkJoinTask;

public class LineInMemory extends Line{
 //   protected Vector<LineSegmentInMemory> lineSegments;


    public LineInMemory(LineName name) {
        super(name);
    }

    public LineInMemory(LineName name, Vector<LineSegmentInMemory> lineSegments, StopName firstStop, Vector<Time> startingTimes) {
        super(name);
        this.lineSegments = (Vector<LineSegmentInterface>) lineSegments.clone();
        this.firstStop = firstStop;
        this.startingTimes = (Vector<Time>) startingTimes.clone();

    }

/*
    @Override
    public void updateReachable(StopName stop, Time time) {
        int timeOrder = 0;
        while(startingTimes.get(timeOrder).time < time.time) timeOrder++;

        StopName tmpName = firstStop;
        Time tmpTime = startingTimes.get(timeOrder);
        int i = 0;

        while (tmpName != stop){
            Pair tmp = lineSegments.get(i).nextStop(tmpTime);
            tmpName = (StopName) tmp.getSecond();
            tmpTime = (Time) tmp.getFirst();
            i++;
        }

        Integer timeDif = startingTimes.get(timeOrder).time - startingTimes.get(timeOrder - 1).time;
        if (tmpTime.time - timeDif >= time.time ){
            tmpTime = new Time(tmpTime.time - timeDif);
        }

        while(i < lineSegments.size()){
            Tuple<Time, StopName, Boolean> tmp = lineSegments.get(i).nextStopAndUpdateReachable(tmpTime);
            if(tmp.getC() == false) break;
        }


    }

*/

    public boolean contains(StopName stop){
        if (firstStop == stop) return true;
        for (int i = 0; i < lineSegments.size(); i++){
            if (lineSegments.get(i).getNextStop().getName() == stop) return true;
        }
        return false;
    }

}
