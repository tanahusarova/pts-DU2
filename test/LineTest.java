package test;

import connection.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import static org.junit.Assert.assertTrue;

class FakeLineSegment implements LineSegmentInterface{

    protected StopInterface nextStop;
    protected TimeOffset timeToNextStop;
    protected int capacity;
    protected LineName lineName;
    protected Map<Time, Integer> numberOfPasengers;

    public FakeLineSegment(Stop nextStop) {
        this.nextStop = nextStop;
        timeToNextStop = new TimeOffset(10);
        capacity = 20;
        lineName = new LineName("39");
        numberOfPasengers = new HashMap<>();
    }

    @Override
    public StopInterface getNextStop() {
        return nextStop;
    }

    @Override
    public void incrementCapacity(Time time) {
        if (!numberOfPasengers.containsKey(time)) {
            numberOfPasengers.put(time, 1);
        }
        else numberOfPasengers.put(time, numberOfPasengers.get(time) + 1);
    }

    @Override
    public Pair<Time, StopName> nextStop(Time time) {
        return new Pair(new Time(time.getTime() + timeToNextStop.getTime()), nextStop.getName());
    }

    @Override
    public Tuple<Time, StopName, Boolean> nextStopAndUpdateReachable(Time time) {
        if (!numberOfPasengers.containsKey(time)) {
            numberOfPasengers.put(time, 0);
        }

        Boolean tmp = numberOfPasengers.get(time).intValue() < capacity;

        if (tmp == true) nextStop.updateReachableAt(new Time(time.getTime() + timeToNextStop.getTime()), lineName);

        return new Tuple<>(new Time(time.getTime() + timeToNextStop.getTime()), nextStop.getName(), tmp);

    }
}

public class LineTest {
    private Line line;

    @Test
    public void assertUpdateReachable(){
        Vector<Time> times = new Vector<>(){{
            add(new Time(20));
            add(new Time(40));
            add(new Time(60));
            add(new Time(80));
            add(new Time(100));
        }};

        Stop s1 = new Stop(new StopName("Televízia"));
        Stop s2 = new Stop(new StopName("Zoo"));
        Stop s3 = new Stop(new StopName("Lanfranconi"));
        Stop s4 = new Stop(new StopName("Kráľovské údolie"));
        Stop s5 = new Stop(new StopName("Chatam Sófer"));

        Vector<LineSegmentInterface> lineSegments = new Vector<>(){{
            add(new FakeLineSegment(s2));
            add(new FakeLineSegment(s3));
            add(new FakeLineSegment(s4));
            add(new FakeLineSegment(s5));

        }};
        line = new Line(new LineName("39"), lineSegments, times, s1.getName());

        line.updateReachable(new StopName("Lanfranconi"), new Time(75));
        assertTrue(s3.getReachableAt().get().getTime() == 80);
        assertTrue(s4.getReachableAt().get().getTime() == 90);

        line.updateReachable(new StopName("Zoo"), new Time(50));
        assertTrue(s3.getReachableAt().get().getTime() == 60);
        assertTrue(s5.getReachableAt().get().getTime() == 80);

    }

    @Test
    public void assertUpdateCapacityAndGetPreviousStop(){
        Vector<Time> times = new Vector<>();

        Stop s1 = new Stop(new StopName("Televízia"));
        FakeLineSegment s2 = new FakeLineSegment(new Stop(new StopName("Zoo")));
        FakeLineSegment s3 = new FakeLineSegment(new Stop(new StopName("Lanfranconi")));
        FakeLineSegment s4 = new FakeLineSegment(new Stop(new StopName("Kráľovské údolie")));
        FakeLineSegment s5 = new FakeLineSegment(new Stop(new StopName("Chatam Sófer")));

        Vector<LineSegmentInterface> lineSegments = new Vector<>(){{
            add(s2);
            add(s3);
            add(s4);
            add(s5);

        }};

        line = new Line(new LineName("39"), lineSegments, times, s1.getName());
        StopName tmp = line.updateCapacityAndGetPreviousStop(new StopName("Lanfranconi"), new Time(80));
        assertTrue(tmp.equals(new StopName("Zoo")));
        assertTrue(s3.numberOfPasengers.get(new Time(80)) == 1);
        tmp = line.updateCapacityAndGetPreviousStop(new StopName("Lanfranconi"), new Time(80));
        assertTrue(tmp.equals(new StopName("Zoo")));
        tmp = line.updateCapacityAndGetPreviousStop(new StopName("Lanfranconi"), new Time(80));
        assertTrue(tmp.equals(new StopName("Zoo")));
        assertTrue(s3.numberOfPasengers.get(new Time(80)).intValue() == 3);



    }

}
