package connection;

public class Tuple <E, T, K>{
    private E a;
    private T b;
    private K c;

    public Tuple(E a, T b, K c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public E getA() {
        return a;
    }

    public T getB() {
        return b;
    }

    public K getC() {
        return c;
    }
}
