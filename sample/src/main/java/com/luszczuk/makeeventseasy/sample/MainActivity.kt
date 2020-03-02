package com.luszczuk.makeeventseasy.sample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.luszczuk.makeeventseasy.base.tracker.StandardCompositeEventTracker
import com.luszczuk.makeeventseasy.sample.events.SampleClickButtonEvent
import com.luszczuk.makeeventseasy.sample.events.SampleClickButtonEventWithConstParameters
import com.luszczuk.makeeventseasy.sample.events.SampleClickButtonEventWithDynamicParameters
import com.mixpanel.android.mpmetrics.MixpanelAPI
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var tracker: StandardCompositeEventTracker

    @Inject
    lateinit var mixpanelAPI: MixpanelAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.buttonOne).setOnClickListener {
            tracker.trackEvent(SampleClickButtonEvent())
        }

        findViewById<View>(R.id.buttonTwo).setOnClickListener {
            tracker.trackEvent(SampleClickButtonEventWithConstParameters())
        }

        findViewById<View>(R.id.buttonThree).setOnClickListener {
            tracker.trackEvent(
                SampleClickButtonEventWithDynamicParameters(buttonPressed = true, price = 350.0)
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mixpanelAPI.flush()
    }
}
