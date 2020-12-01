package com.recycler.mvvmbasepackapplication.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.Toast
import es.dmoral.toasty.Toasty
import javax.inject.Inject

/**
 * created By Jishnu P Dileep
 * 01-12-2020
 * */


class StylishToastUtils @Inject constructor(context: Context) {

    private var appContext = context

    fun showSuccessToast(msg: String) {
        Toasty.success(appContext, msg, Toast.LENGTH_SHORT, true).show();
    }

    fun showErrorToast(msg: String) {
        Toasty.error(appContext, msg, Toast.LENGTH_SHORT, true).show();
    }

    fun showInfoToast(msg: String) {
        Toasty.info(appContext, msg, Toast.LENGTH_SHORT, true).show();
    }

    fun showWarningToast(msg: String) {
        Toasty.warning(appContext, msg, Toast.LENGTH_SHORT, true).show();
    }

    fun showNormalToast(msg: String) {
        Toasty.normal(appContext, msg).show();
    }

    fun showWithIconToast(msg: String, drawable: Drawable) {
        Toasty.normal(appContext, msg, drawable).show();
    }

    fun createCustomToast(
        msg: String,
        drawable: Int,
        tintColor: Int,
        duration: Int,
        withIcon: Boolean,
        shouldTint: Boolean
    ) {
        Toasty.custom(appContext, msg, drawable, tintColor, duration, withIcon, shouldTint).show();
    }
}