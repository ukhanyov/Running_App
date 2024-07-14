package com.ukhanyov.android_test

import com.ukhanyov.core.domain.AuthInfo
import com.ukhanyov.core.domain.SessionStorage

class SessionStorageFake : SessionStorage {

    private var authInfo: AuthInfo? = null

    override suspend fun get(): AuthInfo? {
        return authInfo
    }

    override suspend fun set(info: AuthInfo?) {
        authInfo = info
    }

}