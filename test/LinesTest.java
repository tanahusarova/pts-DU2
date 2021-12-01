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
