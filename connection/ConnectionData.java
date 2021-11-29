package connection;

import java.util.ArrayList;
//import java.util.LinkedList;
import java.util.*;

public class ConnectionData {
    //po ktoru zastavku ktorou linkou
    private Stack<Tuple<StopName, LineName, Time>> stops;

    public ConnectionData() {
        stops = new Stack<>();
    }

    public void addStop(Tuple<StopName, LineName, Time> newStop){
        stops.push(newStop);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        while(stops.isEmpty()){
            sb.append(stops.pop());
            sb.append(" ");
        }
        return sb.toString();
    }
}
