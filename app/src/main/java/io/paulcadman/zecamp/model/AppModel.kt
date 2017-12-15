package io.paulcadman.zecamp.model

import io.paulcadman.zecamp.containers.Loadable
import io.reactivex.Observable

class AppModel() {
    val schedule: Observable<Loadable<ScheduleModel>> = Observable.just(Loadable.loading())
}
