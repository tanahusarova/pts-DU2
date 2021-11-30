package test;

import connection.*;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class StopsTest {
    private Stops stops;

    @Test
    public void assertSetStartingStop(){
        StopName sn1 = new StopName("Televízia");
        StopName sn2 = new StopName("Zoo");
        StopName sn3 = new StopName("Lanfranconi");
        StopInterface s1 = new Stop(sn1);
        StopInterface s2 = new Stop(sn2);
        StopInterface s3 = new Stop(sn3);
        Set<StopName> stopNames = new HashSet<>(){{
            add(sn1);
            add(sn2);
            add(sn3);
        }};
        Set<StopInterface> stop = new HashSet<>(){{
            add(s1);
            add(s2);
            add(s3);
            }};
        stops = new Stops(stopNames, stop);
        stops.setStartingStop(sn3, new Time(67));
        stops.setStartingStop(sn1, new Time(90));
        stops.setStartingStop(sn2, new Time(24));
        assertTrue(s3.getReachableAt().get().getTime() == 67);
        assertTrue(s1.getReachableAt().get().getTime() == 90);
        assertTrue(s2.getReachableAt().get().getTime() == 24);

    }

    @Test
    public void assertEarliestReachableAfter(){
        StopName sn1 = new StopName("Televízia");
        StopName sn2 = new StopName("Zoo");
        StopName sn3 = new StopName("Lanfranconi");
        StopInterface s1 = new Stop(sn1);
        StopInterface s2 = new Stop(sn2);
        StopInterface s3 = new Stop(sn3);
        Set<StopName> stopNames = new HashSet<>(){{
            add(sn1);
            add(sn2);
            add(sn3);
        }};
        Set<StopInterface> stop = new HashSet<>(){{
            add(s1);
            add(s2);
            add(s3);
        }};
        stops = new Stops(stopNames, stop);
        stops.setStartingStop(sn3, new Time(67));
        stops.setStartingStop(sn1, new Time(90));
        stops.setStartingStop(sn2, new Time(24));
        assertTrue(stops.earliestReachableAfter(new Time(0)).equals(s2));
        assertTrue(stops.earliestReachableAfter(new Time(66)).equals(s3));

    }

    @Test
    public void assertClean(){
        StopName sn1 = new StopName("Televízia");
        StopName sn2 = new StopName("Zoo");
        StopName sn3 = new StopName("Lanfranconi");
        StopInterface s1 = new Stop(sn1);
        StopInterface s2 = new Stop(sn2);
        StopInterface s3 = new Stop(sn3);
        Set<StopName> stopNames = new HashSet<>(){{
            add(sn1);
            add(sn2);
            add(sn3);
        }};
        Set<StopInterface> stop = new HashSet<>(){{
            add(s1);
            add(s2);
            add(s3);
        }};
        stops = new Stops(stopNames, stop);
        assertTrue(stops.getStops().size() == 3);
        stops.clean();
        assertTrue(stops.getStops().size() == 0);

    }

}
