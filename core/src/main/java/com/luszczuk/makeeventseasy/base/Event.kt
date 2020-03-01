package com.luszczuk.makeeventseasy.base

interface Event<K, P> {
    val name: K
    val parameters: List<P>
        get() = emptyList()
}