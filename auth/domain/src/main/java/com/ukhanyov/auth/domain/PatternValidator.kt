package com.ukhanyov.auth.domain

interface PatternValidator {
    fun matches(value: String): Boolean
}