package connection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

public class Line implements LineInterface{
    protected LineName name;
    protected Vector<Time> startingTimes;
    protected Vector<LineSegmentInterface> lineSegments;
    protected StopName firstStop;

    public Line(LineName ln1, Vector<LineSegmentInterface> lineSegments1) {
        name = ln1;
    }

    public Line(LineName lineName) {
        this.name = lineName;
        startingTimes = new Vector<>();
        lineSegments = new Vector<>();
    }

    public Line(LineName lineName, Vector<LineSegmentInterface> segments, Vector<Time> startingTimes, StopName firstStop) {
        this.name = lineName;
        this.startingTimes = startingTimes;
        lineSegments = segments;
        this.firstStop = firstStop;
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

        while (!tmpName.equals(stop)){
            Pair tmp = lineSegments.get(i).nextStop(tmpTime);
            tmpName = (StopName) tmp.getSecond();
            tmpTime = (Time) tmp.getFirst();
            i++;
        }


        if (timeOrder > 0) {
            Integer timeDif = startingTimes.get(timeOrder).time - startingTimes.get(timeOrder - 1).time;
            if (tmpTime.time - timeDif >= time.time) {
                tmpTime = new Time(tmpTime.time - timeDif);
            }
        }

        if (i > 0) lineSegments.get(i - 1).getNextStop().updateReachableAt(new Time(tmpTime.time), name);

        while(i < lineSegments.size()){
            Tuple<Time, StopName, Boolean> tmp = lineSegments.get(i).nextStopAndUpdateReachable(tmpTime);
            tmpTime.time = tmp.getA().getTime();
            if(tmp.getC() == false) break;
            i++;
        }


    }

    public List<StopInterface> update(StopName stop, Time time) {
        int timeOrder = 0;
        while(startingTimes.get(timeOrder).time < time.time) timeOrder++;
        ArrayList<StopInterface> result = new ArrayList<>();

        StopName tmpName = firstStop;
        Time tmpTime = startingTimes.get(timeOrder);
        int i = 0;

        while (!tmpName.equals(stop)){
            Pair tmp = lineSegments.get(i).nextStop(tmpTime);
            tmpName = (StopName) tmp.getSecond();
            tmpTime = (Time) tmp.getFirst();
            i++;
        }

        if (timeOrder > 0) {
            Integer timeDif = startingTimes.get(timeOrder).time - startingTimes.get(timeOrder - 1).time;
            if (tmpTime.time - timeDif >= time.time) {
                tmpTime = new Time(tmpTime.time - timeDif);
            }
        }



        if (i > 0) lineSegments.get(i - 1).getNextStop().updateReachableAt(new Time(tmpTime.time), name);

        while(i < lineSegments.size()){
            Tuple<Time, StopName, Boolean> tmp = lineSegments.get(i).nextStopAndUpdateReachable(tmpTime);
            result.add(lineSegments.get(i).getNextStop());
            tmpTime.time = tmp.getA().getTime();
            if(tmp.getC() == false) break;
            i++;
        }

        return new ArrayList<>(){{
            addAll(result);
        }} ;
    }

    @Override
    public StopName updateCapacityAndGetPreviousStop(StopName stop, Time time) {
        StopName previousStop = firstStop;
        for (LineSegmentInterface ls : lineSegments){
            if (ls.getNextStop().getName().equals(stop)){
                ls.getNextStop().updateReachableAt(time, name);
                ls.incrementCapacity(time);
                return previousStop;
            }
            previousStop = ls.getNextStop().getName();
        }
        return null;
    }

    @Override
    public boolean contains(StopName stop) {
        if (firstStop == stop) return true;
        for (int i = 0; i < lineSegments.size(); i++){
            if (lineSegments.get(i).getNextStop().getName() == stop) return true;
        }
        return false;
    }

}


