package com.luszczuk.makeeventseasy.sample.events

import com.luszczuk.makeeventseasy.base.EventParameter
import com.luszczuk.makeeventseasy.firebase.FirebaseEvent
import com.luszczuk.makeeventseasy.firebase.FlurryEvent
import com.luszczuk.makeeventseasy.firebase.MixpanelEvent

class SampleClickButtonEventWithConstParameters : FirebaseEvent, FlurryEvent, MixpanelEvent {

    override val name: String = "secondButtonClickWithParams"

    override val parameters: List<EventParameter<String, *>> = listOf(
        EventParameter.BooleanEventParameter(name = "parameterOne", value = true),
        EventParameter.IntEventParameter(name = "secondParameterOfIntType", value = 35),
        EventParameter.StringEventParameter(name = "textTypeParameter", value = "exampleParamValue")
    )
}