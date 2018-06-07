package whereis.mypackage.ikeaordertracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;



public class OrderTracker extends AppCompatActivity {

    private AppLovinAd loadedAd;
    private AppLovinInterstitialAdDialog interstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_tracker);

        AppLovinSdk.initializeSdk(getApplicationContext());
        interstitialAd = AppLovinInterstitialAd.create( AppLovinSdk.getInstance( this ), this );

        Log.d("TEST" , "SDK initialized");


        // Optional: Assign listeners
//        interstitialAd.setAdDisplayListener();
//        interstitialAd.setAdClickListener( );
//        interstitialAd.setAdVideoPlaybackListener( ... );


        // Load an Interstitial Ad
        AppLovinSdk.getInstance(getApplicationContext()).getAdService().loadNextAd( AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener()
        {
            @Override
            public void adReceived(AppLovinAd ad)
            {
                loadedAd = ad;
                interstitialAd.showAndRender( loadedAd );
                Log.d("TEST" , "Ad loaded and rendering");
            }

            @Override
            public void failedToReceiveAd(int errorCode)
            {
                Log.d("TEST" , "Ad failed to load with: " + errorCode);
                // Look at AppLovinErrorCodes.java for list of error codes.
            }


        } );

    }
}
