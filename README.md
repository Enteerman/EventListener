# EventListener [![HitCount](http://hits.dwyl.com/Enteerman/EventListener.svg)](http://hits.dwyl.com/Enteerman/EventListener)
Simple event system with Kotlin's lambda

Example Usage:
```kotlin
object Event
class EventTester: EventTarget() {
    init {
        eventSubscribers {
            eventHandler<Event> {
                println("Event")
            }
            eventHandler<Any> {
                println("Any")
            }
            enabled = true // without this it won't work
        }
    }
}

fun main(){
    val tester = EventTester() // Initializing the eventtarget automatically subscribes it because it sets enabled to true
    DefaultBus.post(Event)
}
```
## Advantages

It's beautiful and easy to understand. You don't have to create functions and annotate them like in java.  
It's easy to manage. You can disable or enable an eventlistener at anytime.

## Disadvantages

In java, event listeners gets compiled to anonymous classes. The instance of EventListeners store the instance of these classes in memory. A better way to do this is to take advantage of java 8 `MethodHandle` api, and invoke the listeners dynamically. I suggest you check out [this event system library](https://github.com/cookiedragon234/EventDispatcher).
