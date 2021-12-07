package connection;

//niekde som videl ze ked nejaku classu pouzivas ako key do hashmap tak by mal overridenut metodu hashcode a equals
//intellij ich vie same vygenerovat
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
