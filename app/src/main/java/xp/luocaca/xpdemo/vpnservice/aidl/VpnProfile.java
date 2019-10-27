package xp.luocaca.xpdemo.vpnservice.aidl;

import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

import java.net.URLEncoder;
import java.util.Locale;

import xp.luocaca.xpdemo.entity.ServiceListResp;

public class VpnProfile implements Parcelable {
    public static final Creator<VpnProfile> CREATOR = new Creator<VpnProfile>() {
        public VpnProfile createFromParcel(Parcel parcel) {
            return new VpnProfile(parcel);
        }

        public VpnProfile[] newArray(int size) {
            return new VpnProfile[size];
        }
    };
    public boolean auth = false;
    public boolean bypass = false;
    public String domain = "";
    public String host = "133.130.125.168";
    public String individual = "";
    public boolean ipv6 = false;
    public boolean kcp = false;
    public int kcpPort = 8399;
    public String kcpcli = "--crypt none --mode normal --mtu 1200 --nocomp --dscp 46 --parityshard 0";
    public int localPort = 1080;
    public String method = "aes-256-cfb";
    public String name = "Untitled";
    public ServiceListResp.NodeBase node = null;
    public String password = "a720c26e6617391a";
    public boolean proxyApps = false;
    public String remoteDns = "8.8.8.8";
    public int remotePort = 8010;
    public String route = "all";
    public long rx = 0;
    public long tx = 0;
    public boolean udpdns = false;
    public long userOrder = 0;

    public VpnProfile() {
        if (VERSION.SDK_INT >= 17) {
            this.localPort += Binder.getCallingUserHandle().hashCode();
        }
    }

    public VpnProfile(Parcel parcel) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5 = false;
        if (parcel.readInt() == 1) {
            this.node = new ServiceListResp.NodeBase();
            this.node.index = parcel.readInt();
            this.node.code = parcel.readString();
            this.node.group = parcel.readString();
            switch (parcel.readInt()) {
                case 1:
                    this.node.type = ServiceListResp.ServerType.overseas;
                    break;
                case 2:
                    this.node.type = ServiceListResp.ServerType.domestic;
                    break;
                case 3:
                    this.node.type = ServiceListResp.ServerType.plivate;
                    break;
            }
            this.node.name = parcel.readString();
            this.node.comment = parcel.readString();
            this.node.delay = parcel.readInt();
            this.node.load = parcel.readInt();
            this.node.ipAddr = parcel.readString();
        }
        this.name = parcel.readString();
        this.domain = parcel.readString();
        this.host = parcel.readString();
        this.localPort = parcel.readInt();
        this.remotePort = parcel.readInt();
        this.password = parcel.readString();
        this.method = parcel.readString();
        this.route = parcel.readString();
        this.remoteDns = parcel.readString();
        this.proxyApps = parcel.readInt() != 0;
        if (parcel.readInt() == 0) {
            z = false;
        } else {
            z = true;
        }
        this.bypass = z;
        if (parcel.readInt() == 0) {
            z2 = false;
        } else {
            z2 = true;
        }
        this.udpdns = z2;
        if (parcel.readInt() == 0) {
            z3 = false;
        } else {
            z3 = true;
        }
        this.auth = z3;
        if (parcel.readInt() == 0) {
            z4 = false;
        } else {
            z4 = true;
        }
        this.ipv6 = z4;
        this.individual = parcel.readString();
        this.tx = parcel.readLong();
        this.rx = parcel.readLong();
        this.userOrder = (long) parcel.readInt();
        if (parcel.readInt() != 0) {
            z5 = true;
        }
        this.kcp = z5;
        this.kcpPort = parcel.readInt();
        this.kcpcli = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = 1;
        if (this.node != null) {
            parcel.writeInt(1);
            parcel.writeInt(this.node.index);
            parcel.writeString(this.node.code);
            parcel.writeString(this.node.group);
            int type = 0;
            switch (this.node.type) {
                case overseas:
                    type = 1;
                    break;
                case domestic:
                    type = 2;
                    break;
                case plivate:
                    type = 3;
                    break;
            }
            parcel.writeInt(type);
            parcel.writeString(this.node.name);
            parcel.writeString(this.node.comment);
            parcel.writeInt(this.node.delay);
            parcel.writeInt(this.node.load);
            parcel.writeString(this.node.ipAddr);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeString(this.name);
        parcel.writeString(this.domain);
        parcel.writeString(this.host);
        parcel.writeInt(this.localPort);
        parcel.writeInt(this.remotePort);
        parcel.writeString(this.password);
        parcel.writeString(this.method);
        parcel.writeString(this.route);
        parcel.writeString(this.remoteDns);
        if (this.proxyApps) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (this.bypass) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        parcel.writeInt(i3);
        if (this.udpdns) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        parcel.writeInt(i4);
        if (this.auth) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        parcel.writeInt(i5);
        if (this.ipv6) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        parcel.writeInt(i6);
        parcel.writeString(this.individual);
        parcel.writeLong(this.tx);
        parcel.writeLong(this.rx);
        parcel.writeLong(this.userOrder);
        if (!this.kcp) {
            i7 = 0;
        }
        parcel.writeInt(i7);
        parcel.writeInt(this.kcpPort);
        parcel.writeString(this.kcpcli);
    }

    public String toString() {
        String strName = "";
        try {
            strName = URLEncoder.encode(this.name, "utf-8");
        } catch (Exception e) {
        }
        Locale locale = Locale.ENGLISH;
        String str = "%s%s:%s@%s:%d";
        Object[] objArr = new Object[5];
        objArr[0] = this.method;
        objArr[1] = this.auth ? "-auth" : "";
        objArr[2] = this.password;
        objArr[3] = this.host;
        objArr[4] = Integer.valueOf(this.remotePort);
        return "ss://" + Base64.encodeToString(String.format(locale, str, objArr).getBytes(), 3) + '#' + strName;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof VpnProfile)) {
            return false;
        }
        VpnProfile other = (VpnProfile) obj;
        if (this.host == other.host && this.remotePort == other.remotePort && this.password == other.password && this.method == other.method && this.route == other.route && this.remoteDns == other.remoteDns && this.proxyApps == other.proxyApps && this.bypass == other.bypass && this.bypass == other.bypass && this.udpdns == other.udpdns && this.auth == other.auth && this.ipv6 == other.ipv6 && this.individual == other.individual && this.userOrder == other.userOrder && this.kcp == other.kcp && this.kcpPort == other.kcpPort && this.kcpcli == other.kcpcli) {
            return true;
        }
        return false;
    }

    public boolean isMethodUnsafe() {
        return "table".equalsIgnoreCase(this.method) || "rc4".equalsIgnoreCase(this.method);
    }
}
