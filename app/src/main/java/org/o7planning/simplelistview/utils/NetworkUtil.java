package org.o7planning.simplelistview.utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class NetworkUtil extends AndroidViewModel {

    public NetworkUtil(@NonNull Application application) {
        super(application);
    }

    private static MutableLiveData<Boolean> mutableLiveDataNetworkStatus = new MutableLiveData<>();

    public static MutableLiveData<Boolean> getMutableLiveDataNetworkStatus() {
        return mutableLiveDataNetworkStatus;
    }

    public static void setConnectivityTracking(Context context) {
        ConnectivityManager.NetworkCallback mCallback;
        ConnectivityManager mCManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkRequest request = new NetworkRequest.Builder().build();
        mCallback=new ConnectivityManager.NetworkCallback() {
            @Override
            public void onLost(@NonNull Network network) {
                super.onLost(network);
//                ToastUtil.makeLongToast(context,"Network UnAvailable");
                mutableLiveDataNetworkStatus.postValue(false);
            }
            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                mutableLiveDataNetworkStatus.postValue(true);
//                ToastUtil.makeLongToast(context,"Network Available");
            }
        };

        // now register the above things
        assert mCManager != null;
        mCManager.registerNetworkCallback(request,mCallback);
    }

    // works , but its not automatic ,
    // needs to be called to get info about net E.g on Button Click
    // Plus this uses depricated Method -> NetworkInfo
    public static Boolean getConnectivityStatus(Context context) {
        Boolean status = null;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            return true;
        } else {
            status = false;
            return status;
        }
    }

}