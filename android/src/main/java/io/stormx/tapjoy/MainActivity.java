package io.stormx.tapjoy;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tapjoy.TJActionRequest;
import com.tapjoy.TJConnectListener;
import com.tapjoy.TJError;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.Tapjoy;
import com.tapjoy.TapjoyConnectFlag;

import java.util.Hashtable;

public class MainActivity extends AppCompatActivity implements TJPlacementListener {

    boolean started = false;

    public static final String appKey = "appId";
    public static final String isDev = "isDev";
    public static final String userId = "userId";
    private final String TAG = "TapJoyOfferwall";
    private TJPlacement offerwallPlacement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Hashtable<String, Object> connectFlags = new Hashtable<String, Object>();
        connectFlags.put(TapjoyConnectFlag.ENABLE_LOGGING, getIntent().getStringExtra(isDev));

        Tapjoy.setDebugEnabled(Boolean.parseBoolean(getIntent().getStringExtra(isDev)));
        Tapjoy.connect(getApplicationContext(), getIntent().getStringExtra(appKey), connectFlags, new TJConnectListener() {
            @Override
            public void onConnectSuccess() {
                Log.d(TAG, "onConnectSuccess");
                
                Tapjoy.setUserID(getIntent().getStringExtra(userId));
                offerwallPlacement = Tapjoy.getPlacement("Offerwall", MainActivity.this);
                offerwallPlacement.requestContent();
            }

            @Override
            public void onConnectFailure() {
                Log.d(TAG, "onConnectFailure");
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        started = true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (started) {
            started = false;
            this.finish();
        }
    }

    @Override
    public void onRequestSuccess(TJPlacement tjPlacement) {
        Log.d(TAG, "onRequestSuccess");
    }

    @Override
    public void onRequestFailure(TJPlacement tjPlacement, TJError tjError) {
        Log.d(TAG, "onRequestFailure" +tjError.message);
    }

    @Override
    public void onContentReady(TJPlacement tjPlacement) {
        Log.d(TAG, "onContentReady");
        if (offerwallPlacement != null) {
            offerwallPlacement.showContent();
        }
    }

    @Override
    public void onContentShow(TJPlacement tjPlacement) {
        Log.d(TAG, "onContentShow");
    }

    @Override
    public void onContentDismiss(TJPlacement tjPlacement) {
        Log.d(TAG, "onContentDismiss");
    }

    @Override
    public void onPurchaseRequest(TJPlacement tjPlacement, TJActionRequest tjActionRequest, String s) {
        Log.d(TAG, "onPurchaseRequest");
    }

    @Override
    public void onRewardRequest(TJPlacement tjPlacement, TJActionRequest tjActionRequest, String s, int i) {

    }

    @Override
    public void onClick(TJPlacement tjPlacement) {
        
    }
}
