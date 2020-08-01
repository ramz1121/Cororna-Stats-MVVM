package com.kotlin.corornastats.mvvm.utils

import com.kotlin.corornastats.mvvm.utils.common.NumberFormater
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class NumberFormaterTest {
    @Test
    fun givenValidDoubleNumber_whenConvert_shouldReturnFormattedString() {
        val number = 1235767.54
        val formater = NumberFormater.formatNumber(number)
        assert(formater is String)


    }
}