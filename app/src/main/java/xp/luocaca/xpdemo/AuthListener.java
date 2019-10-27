package xp.luocaca.xpdemo;

import xp.luocaca.xpdemo.entity.UserLoginResp;

public interface AuthListener {
    void OnAuthResult(boolean z, UserLoginResp userLoginResp);
}
