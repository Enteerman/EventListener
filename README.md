# EventListener [![HitCount](http://hits.dwyl.com/Enteerman/EventListener.svg)](http://hits.dwyl.com/Enteerman/EventListener)

[![Donate with Ethereum](https://en.cryptobadges.io/badge/small/0x4D1Cd15c88AA85c0E7C83df9255Ab7B7E3637D28)](https://en.cryptobadges.io/donate/0x4D1Cd15c88AA85c0E7C83df9255Ab7B7E3637D28)
[![Donate with Bitcoin](https://en.cryptobadges.io/badge/small/16NTcxGawB1fEANesfHcN2dJF8g5VDvC1B)](https://en.cryptobadges.io/donate/16NTcxGawB1fEANesfHcN2dJF8g5VDvC1B)
[![Donate with Litecoin](https://en.cryptobadges.io/badge/small/LTov1i9vvhAikCeJ4wRcrRKsy1kVLRHiPr)](https://en.cryptobadges.io/donate/LTov1i9vvhAikCeJ4wRcrRKsy1kVLRHiPr)
[![Donate with Ripple](https://en.cryptobadges.io/badge/small/rnAGkqY4hrNhRJKaVXuBYyv6AxSXEFFnoe)](https://en.cryptobadges.io/donate/rnAGkqY4hrNhRJKaVXuBYyv6AxSXEFFnoe)

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
