package com.ukhanyov.run.domain

//import com.ukhanyov.core.connectivity.domain.messaging.MessagingAction
//import com.ukhanyov.test.PhoneToWatchConnectorFake
import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.ukhanyov.core.domain.location.Location
import com.ukhanyov.core.domain.location.LocationWithAltitude
import com.ukhanyov.test.LocationObserverFake
import com.ukhanyov.test.MainCoroutineExtension
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class RunningTrackerTest {

    companion object {
        @JvmField
        @RegisterExtension
        val mainCoroutineExtension = MainCoroutineExtension()
    }

    private lateinit var runningTracker: RunningTracker
    private lateinit var locationObserverFake: LocationObserverFake
//    private lateinit var watchConnectorFake: PhoneToWatchConnectorFake

    private lateinit var testDispatcher: TestDispatcher
    private lateinit var testScope: CoroutineScope

    @BeforeEach
    fun setUp() {
        locationObserverFake = LocationObserverFake()
//        watchConnectorFake = PhoneToWatchConnectorFake()

        testDispatcher = mainCoroutineExtension.testDispatcher
        testScope = CoroutineScope(testDispatcher)

        runningTracker = RunningTracker(
            locationObserver = locationObserverFake,
            applicationScope = testScope,
//            watchConnector = watchConnectorFake
        )
    }

    @Test
    fun testCombiningRunData() = runTest {
        runningTracker.runData.test {
            skipItems(1)

            runningTracker.startObservingLocation()
            runningTracker.setIsTracking(true)

            val location1 = LocationWithAltitude(Location(1.0, 1.0), 1.0)
            locationObserverFake.trackLocation(location1)
            val emission1 = awaitItem()

            val location2 = LocationWithAltitude(Location(2.0, 2.0), 2.0)
            locationObserverFake.trackLocation(location2)
            val emission2 = awaitItem()

//            watchConnectorFake.sendFromWatchToPhone(MessagingAction.HeartRateUpdate(160))
//            val emission3 = awaitItem()

//            watchConnectorFake.sendFromWatchToPhone(MessagingAction.HeartRateUpdate(170))
//            val emission4 = awaitItem()

            testScope.cancel()

            assertThat(emission1.locations[0][0].location).isEqualTo(location1)
            assertThat(emission2.locations[0][1].location).isEqualTo(location2)
//            assertThat(emission3.heartRates).isEqualTo(listOf(160))
//            assertThat(emission4.heartRates).isEqualTo(listOf(160, 170))

//            val expectedDistance = 156.9 * 1000L
//            val tolerance = 0.03
//            val lowerBound = (expectedDistance * (1 - tolerance)).roundToInt()
//            val upperBound = (expectedDistance * (1 + tolerance)).roundToInt()
//            assertThat(emission4.distanceMeters).isBetween(lowerBound, upperBound)
//
//            assertThat(emission4.locations.first()).hasSize(2)
        }
    }
}