package com.luszczuk.makeeventseasy.flurry

import com.flurry.android.FlurryAgent
import com.flurry.android.FlurryEventRecordStatus
import com.luszczuk.makeeventseasy.base.EventParameter
import io.mockk.*
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Before
import org.junit.Test

class FlurryAnalyticsEventTrackerTest {

    companion object {
        private const val EXAMPLE_NAME = "name"
    }

    @MockK
    private lateinit var converter: EventParametersToStringMapConverter

    @InjectMockKs
    private lateinit var flurryTracker: FlurryAnalyticsEventTracker

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `GIVEN specific event and converter returns parameters map WHEN trackEvent THEN flurryAgent log event called with parameters`() {
        //given
        mockStaticFlurryAgentLogEventMethod()

        val exampleParameters = mockk<List<EventParameter<String, *>>>()
        val event = mockk<FlurryEvent> {
            every { name } returns EXAMPLE_NAME
            every { parameters } returns exampleParameters
        }

        val convertedParameters = mockk<Map<String, String>>()
        every { converter.convert(exampleParameters) } returns convertedParameters

        //when
        flurryTracker.trackEvent(event)

        //then
        verify {
            FlurryAgent.logEvent(EXAMPLE_NAME, convertedParameters)
        }
    }

    private fun mockStaticFlurryAgentLogEventMethod() {
        mockkStatic(FlurryAgent::class)
        every {
            FlurryAgent.logEvent(any(), any() as Map<String, String>)
        } returns FlurryEventRecordStatus.kFlurryEventRecorded
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}