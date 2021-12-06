package connection;


import java.util.List;
import java.util.Vector;

//tato trieda sa mi nezda velmi potrebna ale mozno nieco prehliadam :D
//lebo sa mi zda ze aj tak len vola metodu triedy Stops
public class Update {
    private Stops stops;
    private Lines lines;

    public Update(Stops stops, Lines lines) {
        this.stops = stops;
        this.lines = lines;
    }
    //updateReachable(stops.getLines(tmpStopNameF), tmpStopNameF, tmpTime);

    public void addStops(List<StopInterface> stops){
        this.stops.addStops(stops);
    }

}
