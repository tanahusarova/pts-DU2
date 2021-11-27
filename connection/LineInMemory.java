package connection;

import java.util.Vector;
import java.util.concurrent.ForkJoinTask;

public class LineInMemory extends Line{

    public LineInMemory(LineName name) {
        super(name);
    }

    public LineInMemory(LineName name, Vector<LineSegment> lineSegments, StopName firstStop, Vector<Time> startingTimes) {
        super(name);
        this.lineSegments = (Vector<LineSegment>) lineSegments.clone();
        this.firstStop = firstStop;
        this.startingTimes = (Vector<Time>) startingTimes.clone();

    }


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




        /*int timeOrder = 0;
        int bestTime = 0;
        int order = 0;
        while(startingTimes.get(timeOrder).time < time.time) timeOrder++;
        if (firstStop.getName() == stop) {
            firstStop.updateReachableAt(startingTimes.get(timeOrder), java.util.Optional.ofNullable(name));
            bestTime = startingTimes.get(timeOrder).time;
        }
        else {

            int timePassed = startingTimes.get(timeOrder).time;

            while (lineSegments.get(order).getNextStop().getName() != stop) {
                timePassed += lineSegments.get(order).getTimeToNextStop().time;
                order++;
            }

          //  bestTime = timePassed;

            int tmp = timePassed;
            int dif = 0;

            while(timeOrder < 0 || tmp > time.time){
                bestTime = tmp;
                dif = startingTimes.get(timeOrder).time - startingTimes.get(timeOrder - 1).time;
                tmp -= dif;
                timeOrder--;
            }

        }

        while(order < lineSegments.size()){
            int dif = lineSegments.get(order).getTimeToNextStop().time;
            lineSegments.get(order).getNextStop().updateReachableAt(new Time(bestTime + dif), java.util.Optional.ofNullable(name));
            order++;
        }

         */





    }

    @Override
    public StopName updateCapacityAndGetPreviousStop(StopName stop, Time time) {
        for (LineSegment ls : lineSegments){
            if (ls.getNextStop().getName() == stop){
                ls.getNextStop().updateReachableAt(time, name);
                ls.incrementCapacity(time);
            }
        }
        return null;
    }

    public boolean contains(StopName stop){
        if (firstStop == stop) return true;
        for (int i = 0; i < lineSegments.size(); i++){
            if (lineSegments.get(i).getNextStop().getName() == stop) return true;
        }
        return false;
    }

}
