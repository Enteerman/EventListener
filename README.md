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
