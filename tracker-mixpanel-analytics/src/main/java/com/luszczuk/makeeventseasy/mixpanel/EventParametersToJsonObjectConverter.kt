package com.luszczuk.makeeventseasy.mixpanel

import com.luszczuk.makeeventseasy.base.EventParameter
import org.json.JSONObject

class EventParametersToJsonObjectConverter {

    fun convert(parameters: List<EventParameter<String, *>>): JSONObject {
        return JSONObject().also { jsonObject ->
            parameters.forEach { parameter ->
                when (parameter) {
                    is EventParameter.StringEventParameter -> jsonObject.put(
                        parameter.name,
                        parameter.value
                    )
                    is EventParameter.BooleanEventParameter -> jsonObject.put(
                        parameter.name,
                        parameter.value
                    )
                    is EventParameter.IntEventParameter -> jsonObject.put(
                        parameter.name,
                        parameter.value
                    )
                    is EventParameter.LongEventParameter -> jsonObject.put(
                        parameter.name,
                        parameter.value
                    )
                    is EventParameter.DoubleEventParameter -> jsonObject.put(
                        parameter.name,
                        parameter.value
                    )
                    is EventParameter.FloatEventParameter -> jsonObject.put(
                        parameter.name,
                        parameter.value.toDouble()
                    )
                }
            }
        }
    }
}