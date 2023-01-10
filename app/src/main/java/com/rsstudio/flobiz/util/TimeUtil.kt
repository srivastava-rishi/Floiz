package com.rsstudio.flobiz.util

import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {

    fun getTime(t: Long): String? {
        val time = (t * 1000)
        return SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(time)
    }

}