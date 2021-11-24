package connection;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Optional;
import java.util.Vector;
import java.util.logging.FileHandler;

public class StopDatabase implements StopInterface{
    private StopName name;
    private String fileName;
    private FileReader fileReader;
    private Optional<Time> reachableAt;
    private Optional<LineName> reachableVia;
    private Vector<LineName> lines;


    public StopDatabase(StopName stopName, String fileName) {
        this.name = stopName;
        this.fileName = fileName;
    }

    @Override
    public void updateReachableAt(Time time, Optional<LineName> line) {

    }

    @Override
    public Optional<Time> getReachableAt() {
        return null;
    }

    @Override
    public Vector<LineName> getLines() {
        return null;
    }

    @Override
    public StopName getName() {
        return name;
    }
}
