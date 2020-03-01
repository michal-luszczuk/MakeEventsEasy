package com.luszczuk.makeeventseasy.firebase

import com.google.firebase.analytics.FirebaseAnalytics
import com.luszczuk.makeeventseasy.base.tracker.EventTracker

class FirebaseAnalyticsEventTracker constructor(
    private val firebaseAnalytics: FirebaseAnalytics,
    private val parametersConverter: EventParametersToBundleConverter
) : EventTracker<FirebaseEvent> {

    override fun trackEvent(event: FirebaseEvent) {
        firebaseAnalytics.logEvent(event.name, parametersConverter.convert(event.parameters))
    }
}