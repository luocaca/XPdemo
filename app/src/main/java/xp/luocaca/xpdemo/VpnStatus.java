package xp.luocaca.xpdemo;

public class VpnStatus {
    public static final int CONNECTED = State.CONNECTED();
    public static final int CONNECTING = State.CONNECTING();
    public static final int STOPPED = State.STOPPED();
    public static final int STOPPING = State.STOPPING();

    public static boolean isActive() {
        return GlobalVariable.vpnStatus == CONNECTED || GlobalVariable.vpnStatus == CONNECTING;
    }
}
