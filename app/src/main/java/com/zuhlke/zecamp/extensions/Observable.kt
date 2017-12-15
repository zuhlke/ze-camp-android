package com.zuhlke.zecamp.extensions

import com.zuhlke.zecamp.containers.Loadable
import io.reactivex.Observable

data class TemporalPair<Old, New>(val old: Old, val new: New)

fun <Element> Observable<Element>.latestPair(initial: Element): Observable<TemporalPair<Element, Element>> {
    return scan(TemporalPair(initial, initial)) { previousPair, current -> TemporalPair(previousPair.new, current) }
}

fun <Element> Observable<Element>.latestPair(): Observable<TemporalPair<Element?, Element>> {
    return scan(TemporalPair<Element?, Element?>(null, null)) { previousPair, current -> TemporalPair(previousPair.new, current) }
            .filter { (it.new != null) }
            .map { TemporalPair<Element?, Element>(it.old, it.new!!) }
}

fun <Element> Observable<Element>.asLoadable(): Observable<Loadable<Element>> {
    return map { Loadable.of(it) }.startWith(Loadable.loading())
}
