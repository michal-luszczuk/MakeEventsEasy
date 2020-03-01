package com.luszczuk.makeeventseasy.firebase

import com.luszczuk.makeeventseasy.base.EventParameter

class EventParametersToStringMapConverter {

    fun convert(parameters: List<EventParameter<String, *>>): Map<String, String> {
        return parameters.associateBy({ it.name }, { it.value.toString() })
    }
}