package com.luszczuk.makeeventseasy.sample.events

import com.luszczuk.makeeventseasy.firebase.FirebaseEvent
import com.luszczuk.makeeventseasy.flurry.FlurryEvent
import com.luszczuk.makeeventseasy.mixpanel.MixpanelEvent

class SampleClickButtonEvent : FirebaseEvent, FlurryEvent, MixpanelEvent {

    override val name: String = "exampleButtonClickEvent"
}