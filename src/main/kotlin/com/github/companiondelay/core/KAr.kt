package com.github.companiondelay.core

class KAr private constructor() {

    internal val kArDate: KArDate = KArDate()

    companion object {
        internal operator fun invoke() = KAr()
    }
}