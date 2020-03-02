package com.luszczuk.makeeventseasy.sample.injection

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import com.luszczuk.makeeventseasy.base.tracker.StandardCompositeEventTracker
import com.luszczuk.makeeventseasy.base.tracker.StandardCompositeEventTrackerBuilder
import com.luszczuk.makeeventseasy.firebase.*
import com.mixpanel.android.mpmetrics.MixpanelAPI
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TrackerModule {

    @Provides
    @Singleton
    fun providesCompositeTracker(
        firebaseAnalyticsEventTracker: FirebaseAnalyticsEventTracker,
        mixpanelAnalyticsEventTracker: MixpanelAnalyticsEventTracker,
        flurryAnalyticsEventTracker: FlurryAnalyticsEventTracker
    ): StandardCompositeEventTracker {
        return StandardCompositeEventTrackerBuilder()
            .addTracker(firebaseAnalyticsEventTracker)
            .addTracker(mixpanelAnalyticsEventTracker)
            .addTracker(flurryAnalyticsEventTracker)
            .build()


    }

    @Provides
    @Singleton
    fun providesFirebaseAnalyticsEventTracker(firebaseAnalytics: FirebaseAnalytics) =
        FirebaseAnalyticsEventTracker(firebaseAnalytics, EventParametersToBundleConverter())

    @Provides
    @Singleton
    fun providesMixpanelAnalyticsEventTracker(mixpanelApi: MixpanelAPI) =
        MixpanelAnalyticsEventTracker(mixpanelApi, EventParametersToJsonObjectConverter())

    @Provides
    @Singleton
    fun providesFlurryAnalyticsEventTracker() =
        FlurryAnalyticsEventTracker(EventParametersToStringMapConverter())

    @Provides
    @Singleton
    fun providesFirebaseAnalytics(applicationContext: Application) =
        FirebaseAnalytics.getInstance(applicationContext)

    @Provides
    @Singleton
    fun providesMixpanelApi(applicationContext: Application) =
        MixpanelAPI.getInstance(applicationContext, "test")
}