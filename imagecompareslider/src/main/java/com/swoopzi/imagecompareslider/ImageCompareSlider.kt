package com.swoopzi.imagecompareslider

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.image_compare_slider.view.*

class ImageCompareSlider @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        View.inflate(context, R.layout.image_compare_slider, this)

        val ta = context.obtainStyledAttributes(attrs, R.styleable.ImageCompareSlider)
        try {
            val drawableBackground =
                ta.getResourceId(R.styleable.ImageCompareSlider_background_image, 0)
            if (drawableBackground != 0) {
                val drawable = AppCompatResources.getDrawable(context, drawableBackground)
                background_image.setImageDrawable(drawable)
            }
            val drawableForeground =
                ta.getResourceId(R.styleable.ImageCompareSlider_foreground_image, 0)
            if (drawableForeground != 0) {
                val drawable = AppCompatResources.getDrawable(context, drawableForeground)
                foreground_image.setImageDrawable(drawable)
            }
            val drawableSliderIcon = ta.getResourceId(R.styleable.ImageCompareSlider_slider_icon, 0)
            if (drawableSliderIcon != 0) {
                val drawable = AppCompatResources.getDrawable(context, drawableSliderIcon)
                slider_image.setImageDrawable(drawable)
            }
        } finally {
            ta.recycle()
        }

        sbImageSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                setImageWidth(i)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        background_image.post {
            foreground_image.layoutParams.height = background_image.height
            foreground_image.layoutParams.width = background_image.width
            sbImageSeek.max = background_image.width
            slider_bar.layoutParams.height = background_image.height
        }
    }

    private fun setImageWidth(progress: Int) {
        if (progress <= 0)
            return
        val lp: ViewGroup.LayoutParams = target.layoutParams
        lp.width = progress
        target.layoutParams = lp
    }


}