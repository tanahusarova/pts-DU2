package connection;

public class Pair <E, T>{
    private E a;
    private T b;

    public Pair(E a, T b) {
        this.a = a;
        this.b = b;
    }

    public E getFirst(){
        return a;
    }

    public T getSecond(){
        return b;
    }
}
