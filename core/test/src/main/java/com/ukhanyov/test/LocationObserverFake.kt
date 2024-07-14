package com.ukhanyov.test

import com.ukhanyov.core.domain.location.LocationWithAltitude
import com.ukhanyov.run.domain.LocationObserver
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class LocationObserverFake : LocationObserver {

    private val _locations = MutableSharedFlow<LocationWithAltitude>()

    override fun observeLocation(interval: Long): Flow<LocationWithAltitude> {
        return _locations.asSharedFlow()
    }

    suspend fun trackLocation(location: LocationWithAltitude) {
        _locations.emit(location)
    }
}