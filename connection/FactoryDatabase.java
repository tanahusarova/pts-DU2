package connection;

public class FactoryDatabase implements Factory {
    private String fileName;

    public FactoryDatabase(String name) {
        fileName = name;
    }

    @Override
    public StopInterface createStop(StopName stopname) {
        return new StopDatabase(stopname, fileName);
    }

    @Override
    public LineInterface createLine(LineName lineName) {
        return new LineDatabase(lineName, fileName);
    }


}
