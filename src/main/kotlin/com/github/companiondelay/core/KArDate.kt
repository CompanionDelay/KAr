package com.github.companiondelay.core

import com.github.companiondelay.util.threeLeadingZeros
import com.github.companiondelay.util.twoLeadingZeros
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs
import kotlin.math.floor


class KArDate {

    private val grgSumOfDays = arrayOf(intArrayOf(0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365), intArrayOf(0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335, 366))
    private val hshSumOfDays = arrayOf(intArrayOf(0, 31, 62, 93, 124, 155, 186, 216, 246, 276, 306, 336, 365), intArrayOf(0, 31, 62, 93, 124, 155, 186, 216, 246, 276, 306, 336, 366))

    private var setterEnabled = true

    internal var epoch: Long = 0
        set(value) {
            if (field != value) {
                field = value
                initDate()
            }
        }


    internal var shYear: Int = 0
        set(value) {
            if (field != value) {
                field = value
                if (setterEnabled) {
                    toGregorian(shYear, shMonth, shDay)
                    updateEpoch()
                }
            }
        }

    internal var shMonth: Int = 0
        set(value) {
            if (field != value) {
                field = value
                if (setterEnabled) {
                    toGregorian(shYear, shMonth, shDay)
                    updateEpoch()
                }
            }
        }

    internal var shDay: Int = 0
        set(value) {
            if (field != value) {
                field = value
                if (setterEnabled) {
                    toGregorian(shYear, shMonth, shDay)
                    updateEpoch()
                }
            }
        }

    internal var sgYear: Int = 0
        set(value) {
            if (field != value) {
                field = value
                if (setterEnabled) updateEpoch()
            }
        }

    internal var sgMonth: Int = 0
        set(value) {
            if (field != value) {
                field = value
                if (setterEnabled) updateEpoch()
            }
        }

    internal var sgDay: Int = 0
        set(value) {
            if (field != value) {
                field = value
                if (setterEnabled) updateEpoch()
            }
        }

    internal var hour: Int = 0
        set(value) {
            if (field != value) {
                field = value
                if (setterEnabled) updateEpoch()
            }
        }

    internal var minute: Int = 0
        set(value) {
            if (field != value) {
                field = value
                if (setterEnabled) updateEpoch()
            }
        }

    internal var second: Int = 0
        set(value) {
            if (field != value) {
                field = value
                if (setterEnabled) updateEpoch()
            }
        }

    internal var milliSecond: Int = 0
    set(value) {
        if (field != value) {
            field = value
            if (setterEnabled) updateEpoch()
        }
    }

    private fun initDate() {
        setterEnabled = false

        val sgYear = Integer.parseInt(SimpleDateFormat("yyyy").format(epoch))
        val sgMonth = Integer.parseInt(SimpleDateFormat("MM").format(epoch))
        val sgDay = Integer.parseInt(SimpleDateFormat("dd").format(epoch))
        val hour = Integer.parseInt(SimpleDateFormat("HH").format(epoch))
        val minute = Integer.parseInt(SimpleDateFormat("mm").format(epoch))
        val second = Integer.parseInt(SimpleDateFormat("ss").format(epoch))
        val milliSecond = Integer.parseInt(SimpleDateFormat("SSS").format(epoch))

        initTime(hour, minute, second, milliSecond)
        initGregorian(sgYear, sgMonth, sgDay)
        toHijri(sgYear, sgMonth, sgDay)

        setterEnabled = true
    }

    private fun initTime(hour: Int, minute: Int, second: Int, milliSecond: Int) {
        setterEnabled = false

        this.hour = hour
        this.minute = minute
        this.second = second
        this.milliSecond = milliSecond

        setterEnabled = true
    }

    private fun initGregorian(sgYear: Int, sgMonth: Int, sgDay: Int) {
        setterEnabled = false

        this.sgYear = sgYear
        this.sgMonth = sgMonth
        this.sgDay = sgDay

        setterEnabled = true
    }

    private fun updateEpoch() {
        val dateString = "${sgYear.twoLeadingZeros()}-${sgMonth.twoLeadingZeros()}-${sgDay.twoLeadingZeros()} " +
                "${hour.twoLeadingZeros()}:${minute.twoLeadingZeros()}:${second.twoLeadingZeros()}.${milliSecond.threeLeadingZeros()}"
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
        try {
            val date = format.parse(dateString)
            epoch = date.time
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

    private fun toGregorian(shYear: Int, shMonth: Int, shDay: Int) {
        setterEnabled = false

        var sgYear = shYear + 621
        var sgDay = 0
        var sgMonth = 0
        var sgElapsed: Int
        var shLeap: Boolean = shIsLeap(shYear)
        var sgLeap: Boolean = sgIsLeap(sgYear)
        val shElapsed = hshSumOfDays[if (shLeap) 1 else 0][shMonth - 1] + shDay
        if (shMonth > 10 || shMonth == 10 && shElapsed > 286 + if (sgLeap) 1 else 0) {
            sgElapsed = shElapsed - (286 + if (sgLeap) 1 else 0)
            sgLeap = sgIsLeap(++sgYear)
        } else {
            shLeap = shIsLeap(shYear - 1)
            sgElapsed = shElapsed + 79 + (if (shLeap) 1 else 0) - if (sgIsLeap(sgYear - 1)) 1 else 0
        }
        if (sgYear >= 2030 && (sgYear - 2030) % 4 == 0) {
            sgElapsed--
        }
        if (sgYear == 1989) {
            sgElapsed++
        }
        for (i in 1..12) {
            if (grgSumOfDays[if (sgLeap) 1 else 0][i] >= sgElapsed) {
                sgMonth = i
                sgDay = sgElapsed - grgSumOfDays[if (sgLeap) 1 else 0][i - 1]
                break
            }
        }
        this.sgYear = sgYear
        this.sgMonth = sgMonth
        this.sgDay = sgDay

        setterEnabled = true
    }

    private fun toHijri(sgYear: Int, sgMonth: Int, sgDay: Int) {
        setterEnabled = false

        var shDay = 0
        var shMonth = 0
        var shElapsed: Int
        var shYear = sgYear - 621
        val sgLeap = sgIsLeap(sgYear)
        var shLeap = shIsLeap(shYear - 1)
        val sgElapsed = grgSumOfDays[if (sgLeap) 1 else 0][sgMonth - 1] + sgDay
        val xmasToNorooz = if (shLeap && sgLeap) 80 else 79
        if (sgElapsed <= xmasToNorooz) {
            shElapsed = sgElapsed + 286
            shYear--
            if (shLeap && !sgLeap) shElapsed++
        } else {
            shElapsed = sgElapsed - xmasToNorooz
            shLeap = shIsLeap(shYear)
        }
        if (sgYear >= 2029 && (sgYear - 2029) % 4 == 0) {
            shElapsed++
        }
        for (i in 1..12) {
            if (hshSumOfDays[if (shLeap) 1 else 0][i] >= shElapsed) {
                shMonth = i
                shDay = shElapsed - hshSumOfDays[if (shLeap) 1 else 0][i - 1]
                break
            }
        }
        this.shYear = shYear
        this.shMonth = shMonth
        this.shDay = shDay

        setterEnabled = true
    }

    private fun sgIsLeap(year: Int): Boolean =
            year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)

    private fun shIsLeap(year: Int): Boolean {
        val referenceYear = 1375.0
        var startYear = 1375.0
        val yearRes = year - referenceYear
        if (yearRes > 0) {
            if (yearRes >= 33) {
                val numb = yearRes / 33
                startYear = referenceYear + floor(numb) * 33
            }
        } else {
            startYear = if (yearRes >= -33) {
                referenceYear - 33
            } else {
                val numb = abs(yearRes / 33)
                referenceYear - (floor(numb) + 1) * 33
            }
        }
        val leapYears = doubleArrayOf(
                startYear,
                startYear + 4,
                startYear + 8,
                startYear + 16,
                startYear + 20,
                startYear + 24,
                startYear + 28,
                startYear + 33
        )
        return Arrays.binarySearch(leapYears, year.toDouble()) >= 0
    }

}