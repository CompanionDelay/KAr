package com.github.companiondelay

import com.github.companiondelay.core.KAr
import org.junit.Assert.assertEquals
import org.junit.Test

class KArTest {
    @Test
    fun testMyLanguage() {
        assertEquals("KAr", KAr().libName())
    }
}
