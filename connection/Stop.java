package connection;

import java.util.HashMap;
import java.util.Optional;
import java.util.Vector;

public class Stop implements StopInterface{
    protected StopName stopName;
    protected Optional<Time> reachableAt;
    protected Optional<LineName> reachableVia;
    protected Vector<LineName> lines;

    public Stop(StopName stopName) {
        this.stopName = stopName;
        reachableAt = Optional.ofNullable(null);
        reachableVia = Optional.ofNullable(null);

    }

    public void updateReachableAt(Time time, LineName line){
        if (!reachableAt.isPresent() || time.time < reachableAt.get().time) {
            reachableAt = Optional.of(time);
            if (line != null)
                reachableVia = Optional.of(line);
        }
    }

    public Optional<Time> getReachableAt(){
        return reachableAt;
    }

    @Override
    public Optional<LineName> getReachableVia() {
        return reachableVia;
    }

    public Vector<LineName> getLines(){
        search();
        return lines;
    }

    public StopName getName() {
        return stopName;
    }

    protected void search(){
    }

    public void setLines(Vector<LineName> lines){
        this.lines = lines;
    }
}
