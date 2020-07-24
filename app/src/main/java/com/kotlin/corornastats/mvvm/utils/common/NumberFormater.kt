package com.kotlin.corornastats.mvvm.utils.common

import java.text.DecimalFormat

object NumberFormater {
    fun formatNumber(cases: Double?): String {
        var formatter = DecimalFormat("#,###,###")
        var formattedString: String = formatter.format(cases)

        return formattedString

    }
}