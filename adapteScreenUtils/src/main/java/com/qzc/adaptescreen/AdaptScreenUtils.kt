package com.qzc.adaptescreen

import android.app.Activity
import android.content.res.Resources
import android.util.Log

object AdaptScreenUtils {
    private val TAG = "AdaptScreenUtils"

    /**
     * 屏幕适配
     *
     * @param activity
     * @param sizeDp
     * @param isVerticalSlide
     */
    fun adaptScreen(activity: Activity, sizeDp: Int, isVerticalSlide: Boolean) {
        if (isAdaptScreen(activity)) {
            Log.d(TAG, "Already adaptScreen!")
            return
        }
        val sysDm = Resources.getSystem().displayMetrics
        val appDm = activity.applicationContext.resources.displayMetrics
        val actDm = activity.resources.displayMetrics

        if (isVerticalSlide) {
            actDm.density = actDm.widthPixels.toFloat() / sizeDp
        } else {
            actDm.density = actDm.heightPixels.toFloat() / sizeDp
        }

        actDm.scaledDensity = actDm.density * (sysDm.scaledDensity / sysDm.density)
        actDm.densityDpi = (160 * actDm.density).toInt()

        appDm.density = actDm.density
        appDm.scaledDensity = actDm.scaledDensity
        appDm.densityDpi = actDm.densityDpi
        Log.d(TAG, "adaptScreen=================================")
        Log.d(TAG, "sysDm.density: " + sysDm.density)
        Log.d(TAG, "sysDm.scaledDensity: " + sysDm.scaledDensity)
        Log.d(TAG, "sysDm.densityDpi: " + sysDm.densityDpi)
        Log.d(TAG, "actDm.density: " + actDm.density)
        Log.d(TAG, "actDm.scaledDensity: " + actDm.scaledDensity)
        Log.d(TAG, "actDm.densityDpi: " + actDm.densityDpi)
    }

    /**
     * 取消屏幕适配
     *
     * @param activity
     */
    fun cancelAdapt(activity: Activity) {
        val sysDm = Resources.getSystem().displayMetrics
        val appDm = activity.applicationContext.resources.displayMetrics
        val actDm = activity.resources.displayMetrics

        appDm.density = sysDm.density
        appDm.scaledDensity = sysDm.scaledDensity
        appDm.densityDpi = sysDm.densityDpi

        actDm.density = sysDm.density
        actDm.scaledDensity = sysDm.scaledDensity
        actDm.densityDpi = sysDm.densityDpi
        Log.d(TAG, "cancelAdapt=================================")
        Log.d(TAG, "sysDm.density: " + sysDm.density)
        Log.d(TAG, "sysDm.scaledDensity: " + sysDm.scaledDensity)
        Log.d(TAG, "sysDm.densityDpi: " + sysDm.densityDpi)
        Log.d(TAG, "actDm.density: " + actDm.density)
        Log.d(TAG, "actDm.scaledDensity: " + actDm.scaledDensity)
        Log.d(TAG, "actDm.densityDpi: " + actDm.densityDpi)
    }

    /**
     * 是否已屏幕适配
     *
     * @param activity
     * @return
     */
    fun isAdaptScreen(activity: Activity): Boolean {
        val sysDm = Resources.getSystem().displayMetrics
        val actDm = activity.resources.displayMetrics
        return actDm.density != sysDm.density
    }

}