package com.qzc.adaptescreen

import android.app.Activity
import android.content.res.Resources
import android.util.Log

object AdaptScreenUtils {
    private val TAG = "AdaptScreenUtils"
    // 系统的Density
    private var sNoncompatDensity: Float = 0.toFloat()
    // 系统的ScaledDensity
    private var sNoncompatScaledDensity: Float = 0.toFloat()
    // 目标的Density
    private var sTargetDensity: Float = 0.toFloat()
    // 目标的ScaledDensity
    private var sTargetScaledDensity: Float = 0.toFloat()
    // 目标的DensityDpi
    private var sTargetDensityDpi: Int = 0

    /**
     * 屏幕适配
     *
     * @param activity
     * @param designSize        设计图尺寸
     * @param isVerticalSlide   垂直方向是否滚动
     */
    @JvmStatic
    fun adaptScreen(activity: Activity, designSize: IntArray, isVerticalSlide: Boolean) {
        if (isAdaptScreen(activity)) {
            Log.d(TAG, "Already adapted!")
            return
        }
        val sysDisplayMetrics = Resources.getSystem().displayMetrics
        val appDisplayMetrics = activity.applicationContext.resources.displayMetrics
        val actDisplayMetrics = activity.resources.displayMetrics
        sNoncompatDensity = sysDisplayMetrics.density
        sNoncompatScaledDensity = sysDisplayMetrics.scaledDensity
        Log.d(TAG, "Before adaptScreen=================================")
        Log.d(TAG, "sysDisplayMetrics.density: ${sysDisplayMetrics.density}")
        Log.d(TAG, "sysDisplayMetrics.scaledDensity: ${sysDisplayMetrics.scaledDensity}")
        Log.d(TAG, "sysDisplayMetrics.densityDpi: ${sysDisplayMetrics.densityDpi}")

        sTargetDensity = if (isVerticalSlide) {
            actDisplayMetrics.widthPixels.toFloat() / designSize[0]
        } else {
            actDisplayMetrics.heightPixels.toFloat() / designSize[1]
        }
        sTargetScaledDensity = sTargetDensity * (sNoncompatScaledDensity / sNoncompatDensity)
        sTargetDensityDpi = (160 * sTargetDensity).toInt()

        appDisplayMetrics.density = sTargetDensity
        appDisplayMetrics.scaledDensity = sTargetScaledDensity
        appDisplayMetrics.densityDpi = sTargetDensityDpi

        actDisplayMetrics.density = sTargetDensity
        actDisplayMetrics.scaledDensity = sTargetScaledDensity
        actDisplayMetrics.densityDpi = sTargetDensityDpi
        Log.d(TAG, "After adaptScreen=================================")
        Log.d(TAG, "sTargetDensity: $sTargetDensity")
        Log.d(TAG, "sTargetScaledDensity: $sTargetScaledDensity")
        Log.d(TAG, "sTargetDensityDpi: $sTargetDensityDpi")
    }

    /**
     * 取消屏幕适配
     *
     * @param activity
     */
    @JvmStatic
    fun cancelAdapt(activity: Activity) {
        val sysDisplayMetrics = Resources.getSystem().displayMetrics
        val appDisplayMetrics = activity.applicationContext.resources.displayMetrics
        val actDisplayMetrics = activity.resources.displayMetrics

        appDisplayMetrics.density = sysDisplayMetrics.density
        appDisplayMetrics.scaledDensity = sysDisplayMetrics.scaledDensity
        appDisplayMetrics.densityDpi = sysDisplayMetrics.densityDpi

        actDisplayMetrics.density = sysDisplayMetrics.density
        actDisplayMetrics.scaledDensity = sysDisplayMetrics.scaledDensity
        actDisplayMetrics.densityDpi = sysDisplayMetrics.densityDpi
        Log.d(TAG, "cancelAdapt=================================")
    }

    /**
     * 是否已屏幕适配
     *
     * @param activity
     * @return
     */
    @JvmStatic
    fun isAdaptScreen(activity: Activity): Boolean {
        val sysDm = Resources.getSystem().displayMetrics
        val actDm = activity.resources.displayMetrics
        return actDm.density != sysDm.density
    }

}