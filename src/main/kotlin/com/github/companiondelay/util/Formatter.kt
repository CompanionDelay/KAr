package com.github.companiondelay.util

internal fun Int.leading(char: Char, paddingSize: Int) = String.format("%$char${paddingSize}d", this)

internal fun Int.twoLeadingZeros() = leading('0', 2)

internal fun Int.threeLeadingZeros() = leading('0', 3)