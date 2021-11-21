package connection;

public class FactoryDatabase implements Factory {

    public FactoryDatabase() {
    }

    @Override
    public StopInterface createStop(StopName stopname) {
        return new StopDatabase(stopname);
    }

    @Override
    public LineInterface createLine(LineName lineName) {
        return new LineDatabase(lineName);
    }


}
