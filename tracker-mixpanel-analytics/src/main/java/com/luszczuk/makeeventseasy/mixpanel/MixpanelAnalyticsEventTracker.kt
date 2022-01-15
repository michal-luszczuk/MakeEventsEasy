package com.luszczuk.makeeventseasy.mixpanel

import com.luszczuk.makeeventseasy.base.tracker.EventTracker
import com.mixpanel.android.mpmetrics.MixpanelAPI

class MixpanelAnalyticsEventTracker constructor(
    private val mixpanelAnalytics: MixpanelAPI,
    private val parametersConverter: EventParametersToJsonObjectConverter
) : EventTracker<MixpanelEvent> {

    override fun trackEvent(event: MixpanelEvent) {
        mixpanelAnalytics.track(event.name, parametersConverter.convert(event.parameters))
    }
}