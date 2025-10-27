package com.example.activitytransfer

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.app.ActivityManager

fun Context.showStackInfo(): String {
    val task = (getSystemService(AppCompatActivity.ACTIVITY_SERVICE) as ActivityManager)
        .getRunningTasks(10)
        .filter { it.baseActivity!!.flattenToShortString()
            .startsWith("com.example.activitytransfer") }[0]

    return """Num -> ${task.numActivities}
             |Top -> ${task.topActivity!!.shortClassName}
             |Base -> ${task.baseActivity!!.shortClassName}""".trimMargin()
}