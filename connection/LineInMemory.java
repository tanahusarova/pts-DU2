package connection;

import java.util.Vector;
import java.util.concurrent.ForkJoinTask;

public class LineInMemory implements LineInterface{
    private LineName name;
    private Vector<Time> startingTimes;
    private Vector<LineSegment> lineSegments;
   // private StopInterface firstStop;
    private StopName firstStop;


    public LineInMemory(LineName name) {
        this.name = name;
        startingTimes = new Vector<>();
        lineSegments = new Vector<>();
    }

    public LineInMemory(LineName name, Vector<LineSegment> lineSegments, StopInterface firstStop) {
        this.name = name;
        this.lineSegments = lineSegments;
        this.firstStop = firstStop.getName();
    }

    @Override
    public LineName getName() {
        return name;
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
            lineSegments.get(i).nextStopAndUpdateReachable(tmpTime);
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
                ls.getNextStop().updateReachableAt(time, java.util.Optional.ofNullable(name));
                ls.incrementCapacity(time);
            }
        }
        return null;
    }
}
