package com.ukhanyov.run.location

import android.location.Location
import com.ukhanyov.core.domain.location.LocationWithAltitude

fun Location.toLocationWithAltitude(): LocationWithAltitude {
    return LocationWithAltitude(
        location = com.ukhanyov.core.domain.location.Location(
            lat = latitude,
            long = longitude,
        ),
        altitude = altitude
    )
}