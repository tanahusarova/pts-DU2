package connection;

import java.util.ArrayList;

public class ConnectionData {
    //po ktoru zastavku ktorou linkou
    private ArrayList<Pair<StopInterface, LineInterface>> stops;
    private Time finalTime;

    public ConnectionData() {
    }

    public void addStop(Pair<StopInterface, LineInterface> newStop){
        stops.add(newStop);
    }

    public void setFinalTime(Time finalTime) {
        this.finalTime = finalTime;
    }
}
