package com.luszczuk.makeeventseasy.flurry

import com.flurry.android.FlurryAgent
import com.luszczuk.makeeventseasy.base.tracker.EventTracker

class FlurryAnalyticsEventTracker constructor(
    private val parametersConverter: EventParametersToStringMapConverter
) : EventTracker<FlurryEvent> {

    override fun trackEvent(event: FlurryEvent) {
        FlurryAgent.logEvent(event.name, parametersConverter.convert(event.parameters))
    }
}