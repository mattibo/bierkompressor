package com.broeckel.bierkompressor;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by Matti Borchers on 10.07.13.
 */
public class HardwareService extends Service {

    private final IBinder mBinder = new HardwareBinder();

    public class HardwareBinder extends Binder {
        HardwareService getService() {
            return HardwareService.this;
        }
    }
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


}
