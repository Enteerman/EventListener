package me.enterman.eventlistener

import java.util.function.Function

open class EventBus<T> {
    val subscribers = mutableListOf<EventSubscribers<T>>()
    fun eventSubscribers(op: EventSubscribers<T>.() -> Unit):EventSubscribers<T> = EventSubscribers<T>().also(op).also { subscribers.add(it) }
    inline fun <reified E:T> post(event:E) = subscribers.forEach { it.post(event) }
}
object DefaultBus: EventBus<Any>()

@Suppress("UNCHECKED_CAST")
class EventSubscribers<T> {
    var enabled = false
    private var handlers = mutableMapOf<Class<out T>, MutableList<T.() -> Unit>>()
    inline fun <reified E:T> eventHandler(noinline handler: E.() -> Unit) = handler0(handler,E::class.java)
    fun <E:T> handler0(handler:E.() -> Unit,klass:Class<E>){
        handlers.getOrPut(klass,{mutableListOf()}).add(handler as T.() -> Unit)
    }
    fun <E:T> post(event:E){
        if(enabled){
            var clazz :Class<*> = event!!::class.java
            while(true) {
                handlers[clazz]?.let {
                    it.forEach {
                        it(event)
                    }
                }
                if(clazz == Any::class.java)
                    break
                clazz = clazz.superclass
            }
            
        }
    }
}
open class EventTarget :AbstractEventTarget<Any>() {
    override val bus = DefaultBus
}

abstract class AbstractEventTarget<T> {
    abstract val bus:EventBus<T>
    fun eventSubscribers(op: EventSubscribers<T>.() -> Unit):EventSubscribers<T> = bus.eventSubscribers(op)
}