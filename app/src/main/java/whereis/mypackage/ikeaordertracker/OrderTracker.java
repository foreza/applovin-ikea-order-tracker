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
    final String log = "IKEA DEBUG";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_tracker);

        AppLovinSdk.initializeSdk(getApplicationContext());
        interstitialAd = AppLovinInterstitialAd.create( AppLovinSdk.getInstance( this ), this );

        Log.d(log , "SDK initialized");

        // Load an Interstitial Ad
        AppLovinSdk.getInstance(getApplicationContext()).getAdService().loadNextAd( AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener()
        {
            @Override
            public void adReceived(AppLovinAd ad)
            {
                loadedAd = ad;
                interstitialAd.showAndRender( loadedAd );
                Log.d(log , "Ad loaded and rendering");
            }

            @Override
            public void failedToReceiveAd(int errorCode)
            {
                Log.d(log , "Ad failed to load with: " + errorCode);
                // Look at AppLovinErrorCodes.java for list of error codes.
            }


        } );

        // Initialize all other listeners
        initializeListeners();
    }



    // This function will call all listeners that will be required to allow our application to run
    public void initializeListeners() {


        //region Interstitial Callback Functions


        // setAdDisplayListener
        interstitialAd.setAdDisplayListener( new AppLovinAdDisplayListener()
        {
            @Override
            public void adDisplayed(AppLovinAd appLovinAd)
            {
                Log.d(log, "Interstitial Displayed" );
                // Do something here
            }

            @Override
            public void adHidden(AppLovinAd appLovinAd)
            {
                Log.d( log, "Interstitial Hidden" );
                // Do something here maybe
            }
        } );


        // setAdClickListener
        interstitialAd.setAdClickListener( new AppLovinAdClickListener()
        {
            @Override
            public void adClicked(AppLovinAd appLovinAd)
            {
                Log.d(log, "Interstitial Clicked" );
                // Do something here
            }
        } );


        // setAdVideoPlaybackListener
        interstitialAd.setAdVideoPlaybackListener( new AppLovinAdVideoPlaybackListener()
        {
            @Override
            public void videoPlaybackBegan(AppLovinAd appLovinAd)
            {
                Log.d( log,"Video Started" );
            }

            @Override
            public void videoPlaybackEnded(AppLovinAd appLovinAd, double percentViewed, boolean wasFullyViewed)
            {
                Log.d(log,"Video Ended" );
            }
        } );

        //endregion

    }

}
