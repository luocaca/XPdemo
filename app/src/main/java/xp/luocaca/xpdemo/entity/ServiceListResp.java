package xp.luocaca.xpdemo.entity;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import xp.luocaca.xpdemo.StringUtil;

public class ServiceListResp implements Serializable {
    public Server privateServer = new Server("private", ServerType.plivate);
    public List<Server> publicServer = new CopyOnWriteArrayList();
    public int result = -1;

    public static class DelayComparator implements Comparator<NodeBase> {
        public int compare(NodeBase vpn1, NodeBase vpn2) {
            if (vpn1 == null) {
                return 1;
            }
            if (vpn2 == null) {
                return -1;
            }
            if (vpn1.delay > vpn2.delay) {
                return 1;
            }
            if (vpn2.delay > vpn1.delay) {
                return -1;
            }
            return 0;
        }
    }

    public static class NameComparator implements Comparator<NodeBase> {
        public int compare(NodeBase lhs, NodeBase rhs) {
            if (lhs == rhs) {
                return 0;
            }
            if (lhs == null) {
                return -1;
            }
            if (rhs == null) {
                return 1;
            }
            return lhs.name.compareTo(rhs.name);
        }
    }

    public static class NodeBase implements Serializable {
        public String code;
        public String comment;
        public int delay;
        public String group;
        public int index = 0;
        public String ipAddr;
        public int load;
        public String name;
        public ServerType type;

        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            NodeBase another = (NodeBase) o;
            if (StringUtil.isEmpty((CharSequence) this.ipAddr) && StringUtil.isEmpty((CharSequence) another.ipAddr)) {
                return true;
            }
            if (StringUtil.isEmpty((CharSequence) this.ipAddr)) {
                if (StringUtil.isNotEmpty(another.ipAddr)) {
                    return false;
                }
            }

            return false;
        }
    }

    public static class PrivateNode extends NodeBase {
        public long expire;
    }

    public static class PublicNode extends NodeBase {
        public boolean bridgeable = false;
        public String pingHost = "";
        public int pingPort = -1;
    }

    public static class Server implements Serializable {
        public String code;
        public int index;
        public String name;
        public List<NodeBase> node;
        public ServerType type;

        public Server() {
            this.index = 0;
            this.node = new CopyOnWriteArrayList();
        }

        public Server(String code2, ServerType type2) {
            this.index = 0;
            this.code = code2;
            this.type = type2;
            this.node = new CopyOnWriteArrayList();
        }
    }

    public enum ServerType implements Serializable {
        overseas,
        domestic,
        plivate
    }
}
