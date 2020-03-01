package com.luszczuk.makeeventseasy.base.tracker

import com.luszczuk.makeeventseasy.base.EventParameter

typealias StandardCompositeEventTracker = CompositeEventTracker<String, EventParameter<String, *>>

typealias StandardCompositeEventTrackerBuilder = CompositeEventTracker.Builder<String, EventParameter<String, *>>
