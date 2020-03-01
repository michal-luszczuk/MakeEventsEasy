package com.luszczuk.makeeventseasy.base.tracker

import com.luszczuk.makeeventseasy.base.Event
import java.util.LinkedHashMap

class CompositeEventTracker<K, P> private constructor(
    private val eventTrackers: Map<Class<*>, EventTracker<*>>
) : EventTracker<Event<K, P>> {

    override fun trackEvent(event: Event<K, P>) {
        for ((key, value) in eventTrackers) {
            if (key.isInstance(event)) {
                @Suppress("UNCHECKED_CAST")
                (value as EventTracker<Event<K, P>>).trackEvent(event)
            }
        }
    }

    class Builder<K, P> {
        private val eventTrackers: LinkedHashMap<Class<*>, EventTracker<*>> = LinkedHashMap()

        inline fun <reified T : Event<K, P>> addTracker(tracker: EventTracker<T>) =
            addTracker(T::class.java, tracker)

        fun <T : Event<K, P>> addTracker(pair: Pair<Class<T>, EventTracker<T>>) =
            addTracker(pair.first, pair.second)

        fun <T : Event<K, P>> addTracker(clazz: Class<T>, tracker: EventTracker<T>) = apply {
            eventTrackers[clazz] = tracker
        }

        fun build(): CompositeEventTracker<K, P> {
            return CompositeEventTracker(eventTrackers)
        }
    }
}

