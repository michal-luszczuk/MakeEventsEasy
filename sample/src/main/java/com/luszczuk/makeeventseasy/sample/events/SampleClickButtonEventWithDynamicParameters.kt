package com.luszczuk.makeeventseasy.sample.events

import com.luszczuk.makeeventseasy.base.EventParameter
import com.luszczuk.makeeventseasy.firebase.FirebaseEvent
import com.luszczuk.makeeventseasy.firebase.FlurryEvent
import com.luszczuk.makeeventseasy.firebase.MixpanelEvent

class SampleClickButtonEventWithDynamicParameters(
    buttonPressed: Boolean,
    price: Double
) : FirebaseEvent, FlurryEvent, MixpanelEvent {

    override val name: String = "thirdButtonClickEventWithDynamicParameters"

    override val parameters: List<EventParameter<String, *>> = listOf(
        EventParameter.BooleanEventParameter(name = "buttonPressedParam", value = buttonPressed),
        EventParameter.DoubleEventParameter(name = "priceParam", value = price)
    )
}