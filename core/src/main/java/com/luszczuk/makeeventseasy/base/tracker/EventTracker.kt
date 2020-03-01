package com.luszczuk.makeeventseasy.base.tracker

import com.luszczuk.makeeventseasy.base.Event

interface EventTracker<in T : Event<*, *>> {
    fun trackEvent(event: T)
}
