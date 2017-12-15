package io.paulcadman.zecamp.extensions

import io.reactivex.Observable

data class TemporalPair<Old, New>(val old: Old, val new: New)

fun <Element> Observable<Element>.latestPair(initial: Element): Observable<TemporalPair<Element, Element>> {
    return scan(TemporalPair(initial, initial)) {previousPair, current -> TemporalPair(previousPair.new, current) }
}

fun <Element> Observable<Element>.latestPair(): Observable<TemporalPair<Element?, Element>> {
    return scan(TemporalPair<Element?, Element?>(null, null)) { previousPair, current -> TemporalPair(previousPair.new, current) }
            .filter { (it.new != null) }
            .map { TemporalPair<Element?, Element>(it.old, it.new!!) }
}
