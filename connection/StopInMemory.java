package connection;

import java.util.Optional;
import java.util.Vector;

public class StopInMemory extends Stop{
    private StopName name;
    private Optional<Time> reachableAt;
    private Optional<LineName> reachableVia;
    private Vector<LineName> lines;

    public StopInMemory(StopName stopName) {
        super(stopName);
    }

    @Override
    public void search(){
    }



}
