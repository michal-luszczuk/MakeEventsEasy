package com.luszczuk.makeeventseasy.flurry

import com.luszczuk.makeeventseasy.base.EventParameter
import com.luszczuk.makeeventseasy.mixpanel.EventParametersToJsonObjectConverter
import com.luszczuk.makeeventseasy.mixpanel.MixpanelAnalyticsEventTracker
import com.luszczuk.makeeventseasy.mixpanel.MixpanelEvent
import com.mixpanel.android.mpmetrics.MixpanelAPI
import io.mockk.*
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.json.JSONObject
import org.junit.Before
import org.junit.Test

class MixpanelAnalyticsEventTrackerTest {

    companion object {
        private const val EXAMPLE_EVENT_NAME = "eventName"
    }

    @MockK(relaxUnitFun = true)
    private lateinit var mixpanelAnalytics: MixpanelAPI

    @MockK
    private lateinit var converter: EventParametersToJsonObjectConverter

    @InjectMockKs
    private lateinit var mixpanelTracker: MixpanelAnalyticsEventTracker

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `GIVEN specific event and converter returns parameters object WHEN trackEvent THEN mixpanel track  called with parameters`() {
        //given
        val exampleParameters = mockk<List<EventParameter<String, *>>>()
        val event = mockk<MixpanelEvent> {
            every { name } returns EXAMPLE_EVENT_NAME
            every { parameters } returns exampleParameters
        }

        val convertedParameters = mockk<JSONObject>()
        every { converter.convert(exampleParameters) } returns convertedParameters

        //when
        mixpanelTracker.trackEvent(event)

        //then
        verify {
            mixpanelAnalytics.track(EXAMPLE_EVENT_NAME, convertedParameters)
        }
    }
}