package connection;

public class Time {
    Integer time;

    public Time(Integer time) {
        this.time = time;
    }

    public Integer getTime(){
        return time;
    }

    @Override
    public String toString() {
        return time.toString();
    }
}
