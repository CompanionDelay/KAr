package com.github.companiondelay.core

fun KAr.Companion.now() = KAr().apply { kArDate.epoch = System.currentTimeMillis() }