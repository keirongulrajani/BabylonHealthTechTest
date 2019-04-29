package com.keiron.techtest.drawable

import android.content.Context
import android.graphics.ColorFilter
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.SimpleColorFilter
import com.airbnb.lottie.model.KeyPath
import com.airbnb.lottie.value.LottieValueCallback

class LoadingDrawable(context: Context) : LottieDrawable() {

    init {
        val accentColour = ContextCompat.getColor(context, com.keiron.techtest.R.color.colorAccent)
        val filter = SimpleColorFilter(accentColour)
        val keyPath = KeyPath("**")
        val callback = LottieValueCallback<ColorFilter>(filter)
        addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callback)
        val lottieTask = LottieCompositionFactory.fromRawRes(context, com.keiron.techtest.R.raw.loader_anim)
        lottieTask.addListener { lottieComposition ->
            composition = lottieComposition
            playAnimation()
        }
    }
}