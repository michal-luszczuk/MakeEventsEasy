package com.luszczuk.makeeventseasy.flurry

import com.luszczuk.makeeventseasy.base.EventParameter

class EventParametersToStringMapConverter {

    fun convert(parameters: List<EventParameter<String, *>>): Map<String, String> {
        return parameters.associateBy({ it.name }, { it.value.toString() })
    }
}