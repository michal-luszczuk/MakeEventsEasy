package com.luszczuk.makeeventseasy.firebase

import com.luszczuk.makeeventseasy.base.tracker.EventTracker
import com.mixpanel.android.mpmetrics.MixpanelAPI

class MixpanelAnalyticsEventTracker constructor(
    private val mixpanelAnalytics: MixpanelAPI,
    private val parametersConverter: EventParametersToJsonObjectConverter
) : EventTracker<FlurryEvent> {

    override fun trackEvent(event: FlurryEvent) {
        mixpanelAnalytics.track(event.name, parametersConverter.convert(event.parameters))
    }
}