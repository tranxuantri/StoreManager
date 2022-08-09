package com.example.myapplication.presentation.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentActivity

abstract class BaseActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    createViewForActivity(savedInstanceState)
    }

    open fun createViewForActivity(savedInstanceState: Bundle?) {
        getLayoutResource()?.run { setContentView(this) }
    }

    @LayoutRes
    open fun getLayoutResource(): Int? = null
}