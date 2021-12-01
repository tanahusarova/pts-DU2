package connection;

import java.util.Vector;

public class ConnectionSearch {
    private Lines lines;
    private Stops stops;
    private Factory factory;
    private Update update;

    public ConnectionSearch(Factory factory) {
        this.factory = factory;
        lines = new Lines(factory);
        stops = new Stops(factory);
        update = new Update(this.stops, this.lines);
    }

    public ConnectionSearch(Lines lines, Stops stops){
        this.lines = lines;
        this.stops = stops;
    }

    public ConnectionData search(StopName from, StopName to, Time time){
        ConnectionData result = new ConnectionData();
        stops.setStartingStop(from, time);
        StopName tmpStopNameF = from;
        Time tmpTime = time;
        StopInterface tmp;

        while(!tmpStopNameF.equals(to)) {
            //doplnit aktualizaciu stopiek v stopkach
            Vector tmpLines = stops.getLines(tmpStopNameF);
            update.addStops(lines.update(tmpLines, tmpStopNameF, tmpTime));
            lines.updateReachable(tmpLines, tmpStopNameF, tmpTime);
            tmp = stops.earliestReachableAfter(tmpTime);
            tmpTime = tmp.getReachableAt().get();
            tmpStopNameF = tmp.getName();
        }

        StopName tmpStopNameT = to;
        while(tmpStopNameT != from)
        {
            Pair<Time, LineName> time_line = stops.getReachableAt(tmpStopNameT);
            result.addStop(new Tuple(tmpStopNameT, time_line.getSecond(), time_line.getFirst()));
            tmpStopNameT = lines.updateCapacityAndGetPreviousStop(time_line.getSecond(), tmpStopNameT, time_line.getFirst());
        }

        stops.clean();
        lines.clean();
        return result;

    }

}
