package connection;

import java.util.ArrayList;
//import java.util.LinkedList;
import java.util.*;

public class ConnectionData {
    //po ktoru zastavku ktorou linkou
    private List<Tuple<StopInterface, LineInterface, Time>> stops;
    private Time finalTime;

    public ConnectionData() {
        stops = new LinkedList<>();
    }

    public void addStop(Tuple<StopInterface, LineInterface, Time> newStop){
        stops.add(newStop);
    }

    public void setFinalTime() {
        this.finalTime = stops.get(stops.size() - 1).getC();
    }

    public void remove(Tuple tuple){
        stops.remove(tuple);
    }

    public Time getFinalTime() {
        return finalTime;
    }

}
