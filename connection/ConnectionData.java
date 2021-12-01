package connection;

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


    public String write() {
        StringBuilder sb = new StringBuilder();
        while(!stops.isEmpty()){
            sb.append(stops.pop().toString("Názov zastávky: ", "Názov linky: ", "čas: "));
            sb.append("\n");
        }
        return sb.toString();
    }


}
