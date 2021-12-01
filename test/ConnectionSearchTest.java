package test;

import connection.*;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class ConnectionSearchTest {
    private ConnectionSearch cs;

    @Test
    public void assertSearch(){
        List<LineInterface> lines;
        List<StopInterface> stops;

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
        Stop s6 = new Stop(new StopName("Rádiová"));
        Stop s7 = new Stop(new StopName("Technická"));

        LineName ln1 = new LineName("39");
        LineName ln2 = new LineName("58");

        Vector<LineSegmentInterface> lineSegments1 = new Vector<>(){{
            add(new LineSegment(s2, new TimeOffset(5), 20, ln1));
            add(new LineSegment(s3, new TimeOffset(5), 20, ln1));
            add(new LineSegment(s4, new TimeOffset(5), 20, ln1));

        }};

        Vector<LineSegmentInterface> lineSegments2 = new Vector<>(){{
            add(new LineSegment(s3, new TimeOffset(10), 20, ln2));
            add(new LineSegment(s6, new TimeOffset(10), 20, ln2));
            add(new LineSegment(s7, new TimeOffset(10), 20, ln2));

        }};

        LineInterface l1 = new Line(ln1, lineSegments1, times1, s0.getName());
        LineInterface l2 = new Line(ln2, lineSegments2, times2, s1.getName());

        lines = new LinkedList<>(){{
            add(l1);
            add(l2);
        }};

        s0.setLines(new Vector<>(){{
            add(ln1);
        }});
        s2.setLines(new Vector<>(){{
            add(ln1);
        }});
        s3.setLines(new Vector<>(){{
            add(ln1);
            add(ln2);
        }});
        s4.setLines(new Vector<>(){{
            add(ln1);
        }});
        s1.setLines(new Vector<>(){{
            add(ln2);
        }});
        s6.setLines(new Vector<>(){{
            add(ln2);
        }});
        s7.setLines(new Vector<>(){{
            add(ln2);
        }});

        stops = new LinkedList<>(){{
            add(s0);
            add(s1);
            add(s2);
            add(s3);
            add(s4);
            add(s6);
            add(s7);
        }};

        Factory factory = new FactoryInMemory(stops, lines);
        cs = new ConnectionSearch(factory);
        ConnectionData result = cs.search(s0.getName(), s7.getName(), new Time(15));
        System.out.println(result.write());
    }
}
