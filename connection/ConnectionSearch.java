package connection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class ConnectionSearch {
    private Lines lines;
    private Stops stops;
    private Factory factory;
    private List<ConnectionData> connectionDatas;


    public ConnectionSearch(Factory factory) {
        this.factory = factory;
        lines = new Lines(factory);
        stops = new Stops(factory);
        connectionDatas = new LinkedList<>();
    }


    public ConnectionData search(StopName from, StopName to, Time time){
        stops.setStartingStop(from, time);
        searchRecursive(from, to, time, new ConnectionData());

        //doplnit zaplnanie kapacity
        StopName tmpStopName = to;

        while(tmpStopName != from)
        {
            Pair<Time, LineName> time_line = stops.getReachableAt(tmpStopName);
            tmpStopName = lines.updateCapacityAndGetPreviousStop(time_line.getSecond(), tmpStopName, time_line.getFirst());
        }

        stops.clean();
        lines.clean();
        return getBestOption();
    }

    public void searchRecursive(StopName from, StopName to, Time time, ConnectionData cd){
        if (from == to) {
            cd.setFinalTime();
            connectionDatas.add(cd);
            return;
        }

        Vector<LineName> listOfLines = stops.getLines(from);
        lines.updateReachable(listOfLines, from, time);
        List<StopInterface> sortedByTime = stops.earliestReachableAfter(time, listOfLines.size());

        for (int i = 0; i < sortedByTime.size(); i++){
            StopInterface tmpStop = sortedByTime.get(i);
            Tuple tmpTuple = new Tuple(tmpStop, tmpStop.getReachableVia().get(), tmpStop.getReachableAt().get());
            cd.addStop(tmpTuple);
            searchRecursive(tmpStop.getName(), to, tmpStop.getReachableAt().get(), cd);
            cd.remove(tmpTuple);

        }
        return;
    }


    private ConnectionData getBestOption(){
        int indexOfBest = 0;
        int bestTime = connectionDatas.get(0).getFinalTime().time;
        for (int i = 1; i < connectionDatas.size(); i++){
            if (bestTime > connectionDatas.get(i).getFinalTime().time){
                bestTime = connectionDatas.get(i).getFinalTime().time;
                indexOfBest = i;
            }
        }
        return connectionDatas.get(indexOfBest);
    }
}
