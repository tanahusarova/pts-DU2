package connection;

public class FactoryInMemory implements Factory {

    public FactoryInMemory() {
    }

    @Override
    public StopInterface createStop(StopName stopName) {
        return new StopInMemory(stopName);
    }

    @Override
    public LineInterface createLine(LineName lineName) {
        return new LineInMemory(lineName);
    }

}
