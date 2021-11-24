package connection;

public class ConnectionSearch {
    private Lines lines;
    private Stops stops;
    private Factory factory;


    public ConnectionSearch(Factory factory) {
        this.factory = factory;
        lines = new Lines(factory);
        stops = new Stops(factory);
    }


    public ConnectionData search(StopName from, StopName to, Time time){
        stops.setStartingStop(from, time);
        StopName tmpStop = from;
        Time tmpTime = time;

        while(tmpStop != to) {
            lines.updateReachable(stops.getLines(tmpStop), tmpStop, time);
            Pair tmp = stops.earliestReachableAfter(tmpTime);
            tmpTime = (Time) tmp.getSecond();
            tmpStop = (StopName) tmp.getFirst();
        }

        return null;
    }


}
