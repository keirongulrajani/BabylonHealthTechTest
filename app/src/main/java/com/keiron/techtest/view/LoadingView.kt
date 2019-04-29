package com.keiron.techtest.view

import android.content.Context
import android.graphics.ColorFilter
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.SimpleColorFilter
import com.airbnb.lottie.model.KeyPath
import com.airbnb.lottie.value.LottieValueCallback
import com.keiron.techtest.R

class LoadingView : LottieAnimationView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        val accentColour = ContextCompat.getColor(context, com.keiron.techtest.R.color.colorAccent)
        val filter = SimpleColorFilter(accentColour)
        val keyPath = KeyPath("**")
        val callback = LottieValueCallback<ColorFilter>(filter)
        addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callback)

        setAnimation(R.raw.loader_anim)
        playAnimation()
    }
}