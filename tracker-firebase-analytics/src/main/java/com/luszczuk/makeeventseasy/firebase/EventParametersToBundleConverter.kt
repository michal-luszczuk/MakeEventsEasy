package com.luszczuk.makeeventseasy.firebase

import android.os.Bundle
import com.luszczuk.makeeventseasy.base.EventParameter

class EventParametersToBundleConverter {

    fun convert(parameters: List<EventParameter<String, *>>): Bundle {
        return Bundle().also { bundle ->
            parameters.forEach { parameter ->
                when (parameter) {
                    is EventParameter.StringEventParameter -> bundle.putString(
                        parameter.name,
                        parameter.value
                    )
                    is EventParameter.BooleanEventParameter -> bundle.putBoolean(
                        parameter.name,
                        parameter.value
                    )
                    is EventParameter.IntEventParameter -> bundle.putInt(
                        parameter.name,
                        parameter.value
                    )
                    is EventParameter.LongEventParameter -> bundle.putLong(
                        parameter.name,
                        parameter.value
                    )
                    is EventParameter.DoubleEventParameter -> bundle.putDouble(
                        parameter.name,
                        parameter.value
                    )
                    is EventParameter.FloatEventParameter -> bundle.putFloat(
                        parameter.name,
                        parameter.value
                    )
                }
            }
        }
    }
}