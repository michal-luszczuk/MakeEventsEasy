package com.luszczuk.makeeventseasy.base.tracker

import com.luszczuk.makeeventseasy.base.Event
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class CompositeEventTrackerTest {

    interface FirstSpecificEvent : Event<String, String>
    interface SecondSpecificEvent : Event<String, String>
    interface ThirdSpecificEvent : Event<String, String>
    interface SecondAndThirdCompositeEvent : SecondSpecificEvent, ThirdSpecificEvent

    @Test
    fun `GIVEN two trackers of which only one handling second event type WHEN trackEvent THEN single tracker handling second type called`() {
        //given
        val firstEventTracker = mockk<EventTracker<FirstSpecificEvent>>(relaxed = true)
        val secondEventTracker = mockk<EventTracker<SecondSpecificEvent>>(relaxed = true)

        val compositeEventTracker = CompositeEventTracker.Builder<String, String>()
            .addTracker(FirstSpecificEvent::class.java to firstEventTracker)
            .addTracker(SecondSpecificEvent::class.java to secondEventTracker)
            .build()
        val secondEvent = mockk<SecondSpecificEvent>()

        //when
        compositeEventTracker.trackEvent(secondEvent)

        //then
        verify {
            secondEventTracker.trackEvent(secondEvent)
        }
    }

    @Test
    fun `GIVEN multiple trackers of which none handle event WHEN trackEvent THEN no trackers called`() {
        //given
        val firstEventTracker = mockk<EventTracker<FirstSpecificEvent>>(relaxed = true)
        val secondEventTracker = mockk<EventTracker<SecondSpecificEvent>>(relaxed = true)

        val multiEventAppTracker = CompositeEventTracker.Builder<String, String>()
            .addTracker(FirstSpecificEvent::class.java, firstEventTracker)
            .addTracker(SecondSpecificEvent::class.java, secondEventTracker)
            .build()

        val event = mockk<ThirdSpecificEvent>()

        //when
        multiEventAppTracker.trackEvent(event)

        //then
        verify(inverse = true) {
            firstEventTracker.trackEvent(any())
        }

        verify(inverse = true) {
            secondEventTracker.trackEvent(any())
        }
    }

    @Test
    fun `GIVEN multiple trackers of which two handling composite event type WHEN trackEvent THEN specific trackers called`() {
        //given
        val firstEventTracker = mockk<EventTracker<FirstSpecificEvent>>(relaxed = true)
        val secondEventTracker = mockk<EventTracker<SecondSpecificEvent>>(relaxed = true)
        val thirdEventTracker = mockk<EventTracker<ThirdSpecificEvent>>(relaxed = true)

        val compositeEventTracker = CompositeEventTracker.Builder<String, String>()
            .addTracker(FirstSpecificEvent::class.java, firstEventTracker)
            .addTracker(SecondSpecificEvent::class.java to secondEventTracker)
            .addTracker(thirdEventTracker)
            .build()

        val compositeEvent = mockk<SecondAndThirdCompositeEvent>()

        //when
        compositeEventTracker.trackEvent(compositeEvent)

        //then
        verify {
            secondEventTracker.trackEvent(compositeEvent)
            thirdEventTracker.trackEvent(compositeEvent)
        }

        verify(inverse = true) {
            firstEventTracker.trackEvent(any())
        }
    }
}