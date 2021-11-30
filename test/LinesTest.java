package test;

import connection.Line;
import connection.LineInterface;
import connection.Lines;
import connection.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Vector;

import static org.junit.Assert.assertTrue;

public class LinesTest {
    private Lines lines;

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

    @Test
    public void assertUpdateCapacityAndGetPreviousStop(){
        /*
        Vector<Time> times1 = new Vector<>(){
            {
                add(new Time(20));
                add(new Time(60));
                add(new Time(100));
            }};

        Vector<Time> times2 = new Vector<>(){
            {
                add(new Time(20));
                add(new Time(40));
                add(new Time(80));
            }};

        Stop s0 = new Stop(new StopName("Bočná"));
        Stop s1 = new Stop(new StopName("Televízia"));
        Stop s2 = new Stop(new StopName("Zoo"));
        Stop s3 = new Stop(new StopName("Lanfranconi"));
        Stop s4 = new Stop(new StopName("Kráľovské údolie"));
        Stop s5 = new Stop(new StopName("Chatam Sófer"));
        Stop s6 = new Stop(new StopName("Rádiová"));
        Stop s7 = new Stop(new StopName("Technická"));


        Vector<LineSegmentInterface> lineSegments1 = new Vector<>(){{
            add(new FakeLineSegment(s2));
            add(new FakeLineSegment(s3));
            add(new FakeLineSegment(s4));

        }};

        Vector<LineSegmentInterface> lineSegments2 = new Vector<>(){{
            add(new FakeLineSegment(s5));
            add(new FakeLineSegment(s6));
            add(new FakeLineSegment(s7));

        }};

        LineName ln1 = new LineName("39");
        LineName ln2 = new LineName("58");
        LineInterface l1 = new Line(ln1, lineSegments1);
        LineInterface l2 = new Line(ln2, lineSegments2);

        lines = new Lines(new HashSet(){{
            add(l1);
            add(l2);
        }},
                new HashSet<>(){{
                    add(ln1);
                    add(ln2);
                }});
        */

        LineName ln1 = new LineName("39");
        LineName ln2 = new LineName("58");
        LineInterface l1 = new Line(ln1, null);
        LineInterface l2 = new Line(ln2, null);

        lines = new Lines(new HashSet(){{
            add(l1);
            add(l2);
        }},
                new HashSet<>(){{
                    add(ln1);
                    add(ln2);
                }});
        assertTrue(lines.getLines().size() == 2);
        lines.clean();;
        assertTrue(lines.getLines().size() == 0);
    }
}
