package com.luszczuk.makeeventseasy.base

sealed class EventParameter<K, V> {
    abstract val name: K
    abstract val value: V

    data class StringEventParameter<K>(
        override val name: K,
        override val value: String
    ) : EventParameter<K, String>()

    data class IntEventParameter<K>(
        override val name: K,
        override val value: Int
    ) : EventParameter<K, Int>()

    data class LongEventParameter<K>(
        override val name: K,
        override val value: Long
    ) : EventParameter<K, Long>()

    data class BooleanEventParameter<K>(
        override val name: K,
        override val value: Boolean
    ) : EventParameter<K, Boolean>()

    data class DoubleEventParameter<K>(
        override val name: K,
        override val value: Double
    ) : EventParameter<K, Double>()

    data class FloatEventParameter<K>(
        override val name: K,
        override val value: Float
    ) : EventParameter<K, Float>()
}

