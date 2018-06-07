package whereis.mypackage.ikeaordertracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinAdViewDisplayErrorCode;
import com.applovin.adview.AppLovinAdViewEventListener;
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

    private AppLovinAdView adView;
    private Button loadButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_tracker);

        AppLovinSdk.initializeSdk(getApplicationContext());
        interstitialAd = AppLovinInterstitialAd.create( AppLovinSdk.getInstance( this ), this );

        // Retrieve elements from layout editor

        adView = (AppLovinAdView) findViewById( R.id.ad_view );
        loadButton = (Button) findViewById( R.id.load_button );

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

        // Load the banner
        adView.loadNextAd();

    }



    // This function will call all listeners that will be required to allow our application to run
    public void initializeListeners() {

        //region Banner Callback Functions

        adView.setAdLoadListener( new AppLovinAdLoadListener()
        {
            @Override
            public void adReceived(final AppLovinAd ad)
            {

                Log.d(log, "Banner loaded" );
            }

            @Override
            public void failedToReceiveAd(final int errorCode)
            {
                // Look at AppLovinErrorCodes.java for list of error codes
                Log.d(log, "Banner failed to load with error code " + errorCode );
            }
        } );


        adView.setAdDisplayListener( new AppLovinAdDisplayListener()
        {
            @Override
            public void adDisplayed(final AppLovinAd ad)
            {
                Log.d( log, "Banner Displayed" );
            }

            @Override
            public void adHidden(final AppLovinAd ad)
            {
                Log.d(log, "Banner Hidden" );
            }
        } );


        adView.setAdClickListener( new AppLovinAdClickListener()
        {
            @Override
            public void adClicked(final AppLovinAd ad)
            {
                Log.d( log,"Banner Clicked" );
            }
        } );


        adView.setAdViewEventListener( new AppLovinAdViewEventListener()
        {
            @Override
            public void adOpenedFullscreen(final AppLovinAd ad, final AppLovinAdView adView)
            {
                Log.d(log, "Banner opened fullscreen" );
            }


            @Override
            public void adClosedFullscreen(final AppLovinAd ad, final AppLovinAdView adView)
            {
                Log.d(log, "Banner closed fullscreen" );
            }


            @Override
            public void adLeftApplication(final AppLovinAd ad, final AppLovinAdView adView)
            {
                Log.d(log, "Banner left application" );
            }


            @Override
            public void adFailedToDisplay(final AppLovinAd ad, final AppLovinAdView adView, final AppLovinAdViewDisplayErrorCode code)
            {
                Log.d(log, "Banner failed to display with error code " + code );
            }
        } );

        //endregion



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
