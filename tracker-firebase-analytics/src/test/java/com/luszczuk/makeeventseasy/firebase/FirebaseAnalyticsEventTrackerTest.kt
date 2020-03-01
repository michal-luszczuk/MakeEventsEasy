package com.luszczuk.makeeventseasy.firebase

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.luszczuk.makeeventseasy.base.EventParameter
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

import org.junit.Before

class FirebaseAnalyticsEventTrackerTest {

    companion object {
        private const val EXAMPLE_EVENT_NAME = "example_event_name"
    }

    @MockK(relaxUnitFun = true)
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    @MockK
    private lateinit var parametersConverter: EventParametersToBundleConverter

    @InjectMockKs
    private lateinit var tracker: FirebaseAnalyticsEventTracker

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `GIVEN specific firebase event and converter returns bundle parameters WHEN trackEvent THEN firebase analytics event logged  with name and bundle params`() {
        //given
        val mockParameters = mockk<List<EventParameter<String, *>>>()
        val event = mockk<FirebaseEvent> {
            every { name } returns EXAMPLE_EVENT_NAME
            every { parameters } returns mockParameters
        }

        val paramsBundle = mockk<Bundle>()
        every { parametersConverter.convert(mockParameters) } returns paramsBundle

        //when
        tracker.trackEvent(event)

        //then
        verify {
            firebaseAnalytics.logEvent(EXAMPLE_EVENT_NAME, paramsBundle)
        }
    }
}