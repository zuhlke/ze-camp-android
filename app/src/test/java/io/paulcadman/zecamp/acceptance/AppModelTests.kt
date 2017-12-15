package io.paulcadman.zecamp.acceptance

import io.paulcadman.zecamp.containers.Loadable
import io.paulcadman.zecamp.model.AppModel
import org.junit.Assert.assertTrue
import org.junit.Test

class AppModelTests {
    @Test
    fun modelScheduleStartsInLoadingState() {
        val initialSchedule = AppModel().schedule.blockingFirst()
        assertTrue(initialSchedule is Loadable.Loading)
    }
}