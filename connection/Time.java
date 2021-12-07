package connection;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time1 = (Time) o;
        return Objects.equals(time, time1.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time);
    }
}
