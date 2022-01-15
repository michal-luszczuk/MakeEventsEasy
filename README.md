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



## Concept & Design
<details>
<summary>Open to understand more the concept behind this library</summary>
<br>
            
### Getting out of the bad
Most of the time when we implement event tracking, i.e. we want to log a button click event in the ViewModel, we just take a specific tracker object and call it, i.e. for Firebase it would be:
```kotlin
class ExampleViewModel @Inject constructor(
   firebaseAnalytics: FirebaseAnalytics,
   ...
) : ViewModel() {

   fun onButtonClick() {
       firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
          param(FirebaseAnalytics.Param.ITEM_ID, id)
          param(FirebaseAnalytics.Param.ITEM_NAME, name)
          param(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
      }
      
      // rest of the implementation after this action
   }
   ...
}
```
Cons of this solution?
- tracking code takes a lot of place (we need few lines to just send an event)
- even if we extract this part of code different trackers have different event parameters representation -  types like Bundle, Map<String, Any>, Map<String, String>, etc.
- makes view model harder to test, i.e. Firebase event tracking takes Bundle class as a parameter store
- the bigger the nuber of trackers the more place it will take 

### Good
#### Event
We can think about an Event (no mater which tracking solution we are using) as an enity with the **name** and **parameters list**. Implementation
So we can make an abstraction that Event is a class with name field and parameters list field.
```kotlin
// part of the library code:
interface Event<K, P> {
    val name: K
    val parameters: List<P>
        get() = emptyList()
}
```

#### Tracker
We can think about tracker as an enity which can handle events of the specific type and send them to the analytical servers
```kotlin
// part of the library code:
interface EventTracker<in T : Event<*, *>> {
    fun trackEvent(event: T)
}
```

To define the specific event details (name and parameters) we define specific class which internally holds all the details about the name and the parameters.
```kotlin
class SelectItem(id: String, name: String, type: String): FirebaseEvent {

    override val name: String = FirebaseAnalytics.Event.SELECT_ITEM

    override val parameters: List<EventParameter<String, *>> = listOf(
        EventParameter.StringEventParameter(name = FirebaseAnalytics.Param.ITEM_ID, value = id),
        EventParameter.StringEventParameter(name = FirebaseAnalytics.Param.ITEM_NAME, value = name),
        EventParameter.StringEventParameter(name = FirebaseAnalytics.Param.CONTENT_TYPE, value = type)
    )
}         
```
           
To define the specific tracker we implement the interface and handle the event by converting parameters from base parameters to parameters required by the specific tracker
```kotlin
// part of library code:
class FirebaseAnalyticsEventTracker constructor(
    private val firebaseAnalytics: FirebaseAnalytics,
    private val parametersConverter: EventParametersToBundleConverter
) : EventTracker<FirebaseEvent> {

    override fun trackEvent(event: FirebaseEvent) {
        firebaseAnalytics.logEvent(event.name, parametersConverter.convert(event.parameters))
    }
}
```
              
After that, when we create our composite tracker, we just have to pass our specific event to it         
```kotlin
class ExampleViewModel @Inject constructor(
   appTracker: StandardCompositeEventTracker,
   ...
) : ViewModel() {

   fun onButtonClick() {
       appTracker.trackEvent(SelectItem(id = id, name = name, type = "image"))
      // rest of the implementation after this action
   }
   ...
}
```

Pros of this solution?
- tracking code is a one liner
- we can build our app tracker composing multiple trackers but the tracking part in the ViewModel will not change
- its easy to add unit test here, just mock event tracker and check if `trackEvent` method is called with proper event instance
- composition and tracker preparation is out of our sight, here we only have to create an event and pass it to app tracker 
</details>
