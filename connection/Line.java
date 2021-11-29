package connection;

import java.util.Vector;

public class Line implements LineInterface{
    protected LineName name;
    protected Vector<Time> startingTimes;
    protected Vector<LineSegmentInterface> lineSegments;
    protected StopName firstStop;

    public Line() {
    }

    public Line(LineName lineName) {
        this.name = lineName;
        startingTimes = new Vector<>();
        lineSegments = new Vector<>();
    }

    public Line(LineName lineName, Vector<LineSegmentInterface> segments, Vector<Time> startingTimes) {
        this.name = lineName;
        this.startingTimes = startingTimes;
        lineSegments = segments;
    }

    public LineName getName(){
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
            Tuple<Time, StopName, Boolean> tmp = lineSegments.get(i).nextStopAndUpdateReachable(tmpTime);
            if(tmp.getC() == false) break;
        }


    }



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

}
