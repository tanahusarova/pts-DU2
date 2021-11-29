package test;
import connection.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StopTest {
    private Stop stop;
    private StopName stopName;

    @Test
    public void assertUpdateReachableAt(){
        stop = new Stop(new StopName("Slávičie údolie"));
        LineName lineName1 = new LineName("39");
        LineName lineName2 = new LineName("31");
        stop.updateReachableAt(new Time(5), lineName1);
        Time tmp = stop.getReachableAt().get();
        int tmp1 = tmp.getTime();
        assertTrue(tmp1 == 5);
        stop.updateReachableAt(new Time(6), lineName2);
        assertEquals(stop.getReachableVia().get(), lineName1);
        stop.updateReachableAt(new Time(2), lineName2);
        assertEquals(stop.getReachableVia().get(), lineName2);

    }

}
