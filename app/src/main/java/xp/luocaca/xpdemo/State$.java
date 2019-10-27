package xp.luocaca.xpdemo;

/* compiled from: Constants.scala */
public final class State$ {
    public static final State$ MODULE$ = null;
    private final int CONNECTED = 2;
    private final int CONNECTING = 1;
    private final int STOPPED = 4;
    private final int STOPPING = 3;

    static {
        new State$();
    }

    public int CONNECTING() {
        return this.CONNECTING;
    }

    public int CONNECTED() {
        return this.CONNECTED;
    }

    public int STOPPING() {
        return this.STOPPING;
    }

    public int STOPPED() {
        return this.STOPPED;
    }

    private State$() {

    }
}
