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


    public ConnectionData search1(StopName from, StopName to, Time time){
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

    public ConnectionData search(StopName from, StopName to, Time time, ConnectionData cd, Time tmpTime){
        if (from == to) {
            return cd;
        }

       // StopName tmpStop = from;
       // Time tmpTime = time;

        lines.updateReachable(stops.getLines(from), from, time);
        Pair tmp = stops.earliestReachableAfter(tmpTime);
        search((StopName) tmp.getFirst(), to, time, cd, (Time) tmp.getSecond());


        while(tmpStop != to) {
            lines.updateReachable(stops.getLines(tmpStop), tmpStop, time);
            Pair tmp = stops.earliestReachableAfter(tmpTime);
            tmpTime = (Time) tmp.getSecond();
            tmpStop = (StopName) tmp.getFirst();
        }


        return null;
    }


}
