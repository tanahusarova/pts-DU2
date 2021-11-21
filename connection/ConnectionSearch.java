package connection;

public class ConnectionSearch {
    private Lines lines;
    private Stops stops;

    public ConnectionSearch(){
    }

    public ConnectionSearch(Lines lines, Stops stops) {
        this.lines = lines;
        this.stops = stops;
    }


    public ConnectionData search(StopName from, StopName to, Time time){
        stops.setStartingStop(from, time);
        lines.updateReachable(stops.getLines(from), from, time);

        return null;
    }

    public void setContext(String fileName){
        this.stops = new Stops(fileName);
        this.lines = new Lines(fileName);

    }

    public void setContext(Stops stops, Lines lines){
        this.stops = stops;
        this.lines = lines;
    }

}
