package xp.luocaca.xpdemo.vpnservice.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IVpnCallback extends IInterface {

    public static abstract class Stub extends Binder implements IVpnCallback {

        private static class Proxy implements IVpnCallback {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void stateChanged(int state, String profileName, String msg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.fivetime.vpnservice.aidl.IVpnCallback");
                    _data.writeInt(state);
                    _data.writeString(profileName);
                    _data.writeString(msg);
                    this.mRemote.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void trafficUpdated(long txRate, long rxRate, long txTotal, long rxTotal) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.fivetime.vpnservice.aidl.IVpnCallback");
                    _data.writeLong(txRate);
                    _data.writeLong(rxRate);
                    _data.writeLong(txTotal);
                    _data.writeLong(rxTotal);
                    this.mRemote.transact(2, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.fivetime.vpnservice.aidl.IVpnCallback");
        }

        public static IVpnCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("com.fivetime.vpnservice.aidl.IVpnCallback");
            if (iin == null || !(iin instanceof IVpnCallback)) {
                return new Proxy(obj);
            }
            return (IVpnCallback) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface("com.fivetime.vpnservice.aidl.IVpnCallback");
                    stateChanged(data.readInt(), data.readString(), data.readString());
                    return true;
                case 2:
                    data.enforceInterface("com.fivetime.vpnservice.aidl.IVpnCallback");
                    trafficUpdated(data.readLong(), data.readLong(), data.readLong(), data.readLong());
                    return true;
                case 1598968902:
                    reply.writeString("com.fivetime.vpnservice.aidl.IVpnCallback");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void stateChanged(int i, String str, String str2) throws RemoteException;

    void trafficUpdated(long j, long j2, long j3, long j4) throws RemoteException;
}
