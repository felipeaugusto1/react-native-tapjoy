package io.stormx.tapjoy;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.content.Intent;

public class RNTapJoyModule extends ReactContextBaseJavaModule {

    public RNTapJoyModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "RNTapJoy";
    }

    @ReactMethod
    public void showOfferwall(final String appKey, final String isDev, final String userId) {
        ReactApplicationContext context = getReactApplicationContext();
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MainActivity.appKey, appKey);
        intent.putExtra(MainActivity.isDev, isDev);
        intent.putExtra(MainActivity.userId, userId);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}