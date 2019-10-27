package xp.luocaca.xpdemo.vpnservice.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IVpnService extends IInterface {

    public static abstract class Stub extends Binder implements IVpnService {

        private static class Proxy implements IVpnService {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public int getState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.fivetime.vpnservice.aidl.IVpnService");
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public long getConnectedTime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.fivetime.vpnservice.aidl.IVpnService");
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readLong();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public VpnProfile getProfile() throws RemoteException {
                VpnProfile _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.fivetime.vpnservice.aidl.IVpnService");
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (VpnProfile) VpnProfile.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getProfileName() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.fivetime.vpnservice.aidl.IVpnService");
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void registerCallback(IVpnCallback cb) throws RemoteException {
                IBinder iBinder = null;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.fivetime.vpnservice.aidl.IVpnService");
                    if (cb != null) {
                        iBinder = cb.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    this.mRemote.transact(5, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void unregisterCallback(IVpnCallback cb) throws RemoteException {
                IBinder iBinder = null;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.fivetime.vpnservice.aidl.IVpnService");
                    if (cb != null) {
                        iBinder = cb.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    this.mRemote.transact(6, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void use(VpnProfile profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.fivetime.vpnservice.aidl.IVpnService");
                    if (profile != null) {
                        _data.writeInt(1);
                        profile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(7, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void useSync(VpnProfile profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.fivetime.vpnservice.aidl.IVpnService");
                    if (profile != null) {
                        _data.writeInt(1);
                        profile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.fivetime.vpnservice.aidl.IVpnService");
        }

        public static IVpnService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("com.fivetime.vpnservice.aidl.IVpnService");
            if (iin == null || !(iin instanceof IVpnService)) {
                return new Proxy(obj);
            }
            return (IVpnService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            VpnProfile _arg0;
            VpnProfile _arg02;
            switch (code) {
                case 1:
                    data.enforceInterface("com.fivetime.vpnservice.aidl.IVpnService");
                    int _result = getState();
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 2:
                    data.enforceInterface("com.fivetime.vpnservice.aidl.IVpnService");
                    long _result2 = getConnectedTime();
                    reply.writeNoException();
                    reply.writeLong(_result2);
                    return true;
                case 3:
                    data.enforceInterface("com.fivetime.vpnservice.aidl.IVpnService");
                    VpnProfile _result3 = getProfile();
                    reply.writeNoException();
                    if (_result3 != null) {
                        reply.writeInt(1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case 4:
                    data.enforceInterface("com.fivetime.vpnservice.aidl.IVpnService");
                    String _result4 = getProfileName();
                    reply.writeNoException();
                    reply.writeString(_result4);
                    return true;
                case 5:
                    data.enforceInterface("com.fivetime.vpnservice.aidl.IVpnService");
                    return true;
                case 6:
                    data.enforceInterface("com.fivetime.vpnservice.aidl.IVpnService");
                    return true;
                case 7:
                    data.enforceInterface("com.fivetime.vpnservice.aidl.IVpnService");
                    if (data.readInt() != 0) {
                        _arg02 = (VpnProfile) VpnProfile.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    use(_arg02);
                    return true;
                case 8:
                    data.enforceInterface("com.fivetime.vpnservice.aidl.IVpnService");
                    if (data.readInt() != 0) {
                        _arg0 = (VpnProfile) VpnProfile.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    useSync(_arg0);
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("com.fivetime.vpnservice.aidl.IVpnService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    long getConnectedTime() throws RemoteException;

    VpnProfile getProfile() throws RemoteException;

    String getProfileName() throws RemoteException;

    int getState() throws RemoteException;

    void registerCallback(IVpnCallback iVpnCallback) throws RemoteException;

    void unregisterCallback(IVpnCallback iVpnCallback) throws RemoteException;

    void use(VpnProfile vpnProfile) throws RemoteException;

    void useSync(VpnProfile vpnProfile) throws RemoteException;
}
