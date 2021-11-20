package conection;

public class ConnectionSearch {
    private Lines lines;
    private Stops stops;

    public ConnectionSearch(Lines lines, Stops stops) {
        this.lines = lines;
        this.stops = stops;
    }


    public ConnectionData search(StopName from, StopName to, Time time){
        stops.setStartingStop(from, time);
        stops.getLines(from);
        return null;
    }

}
