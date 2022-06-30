package com.test.extensions.activity

import android.app.Activity
import android.content.Intent
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment

fun AppCompatActivity.findNavController(@IdRes resId: Int) =
    (supportFragmentManager.findFragmentById(resId) as NavHostFragment).navController




fun Activity.startAndFinish(intent: Intent) {
    startActivity(
        intent
            .newTask()
            .clearTask()
    )
    finish()
}

fun Intent.singleTop(): Intent{
    flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
    return this
}

fun Intent.newTask(): Intent{
    flags = Intent.FLAG_ACTIVITY_NEW_TASK
    return this
}

fun Intent.clearTask(): Intent{
    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
    return this
}