## Motivation
To make a great app and provide value to the users you need to understand them. Analytical data helps us to achieve that.
There are many solutions/systems to track user behavior, i.e. [Firebase Analytics](https://firebase.google.com/docs/analytics), [Flurry SDK for Android](https://developer.yahoo.com/flurry/docs/integrateflurry/android/), [MixPanel SDK](https://developer.mixpanel.com/docs/android) and more...

They all have some pros and cons and sometimes using one specific solution is not enough.

What prevents us from integrating several solutions at once?
- Integrating one analytical system could be a mess, so... integrating few will cause a bigger mess?
- Is it the fear of the clutter in the code after doing so?

## Goal
Events tracking must be fun, easy and clean! No mater if we want to use one, two, or multiple analytical solutions at once!

## Solution - MakeEventsEasy library!
### Benefits
- **Easy to use**: You can easily build your main tracker by composing multiple specific trackers. 
- **Customizable**: Conveniently implement your own specific tracker, handle any tracking library and plug it to the solution. Everyting is based on composite pattern.
- **Clean**: The main tracker hides the implementation and details about the specific trackers, so while sending events you don't have to worry about internal implementation and plugged trackers. 
- **Lightweight**: The core module and the whole concept is super small so size of your app will not increase.
- **Testable**: This library makes event testing easier so you never miss or lose any data

## Download
MakeEventsEasy is available on `mavenCentral()`.

```kotlin
// core module
implementation("com.luszczuk.makeeventseasy:core:0.4.0")

// specific tracker modules
// firebase analytics
implementation("com.luszczuk.makeeventseasy:firebase-analytics:0.4.0")

// flurry analytics
implementation("com.luszczuk.makeeventseasy:tracker-flurry-analytics:0.4.0")

// mixpanel analytics
implementation("com.luszczuk.makeeventseasy:tracker-mixpanel-analytics:0.4.0")
```

## Quick Start

### 1. Build your tracker
Example, shows how to compose and build our main app tracker using Firebase, Flurry and MixPanel trackers 
```kotlin
val firebaseAnalyticsEventTracker = FirebaseAnalyticsEventTracker(firebaseAnalyticsInstance, EventParametersToBundleConverter())
val mixpanelAnalyticsEventTracker = MixpanelAnalyticsEventTracker(mixpanelApi, EventParametersToJsonObjectConverter())
val flurryAnalyticsEventTracker = FlurryAnalyticsEventTracker(EventParametersToStringMapConverter())

val appTracker = StandardCompositeEventTrackerBuilder()
            .addTracker(firebaseAnalyticsEventTracker)
            .addTracker(mixpanelAnalyticsEventTracker)
            .addTracker(flurryAnalyticsEventTracker)
            .build()
```

### 2. Define your event
```kotlin
// without parameters
class SampleClickButtonEvent : FirebaseEvent, FlurryEvent, MixpanelEvent {
    override val name: String = "exampleButtonClickEvent"
}

// with additional parameters
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
```

### 3. Create and track your event 
```kotlin
//event without parameters
appTracker.trackEvent(SampleClickButtonEvent())

//event with additional parameters
appTracker.trackEvent(
    SampleClickButtonEventWithDynamicParameters(buttonPressed = true, price = 350.0)
)
```
