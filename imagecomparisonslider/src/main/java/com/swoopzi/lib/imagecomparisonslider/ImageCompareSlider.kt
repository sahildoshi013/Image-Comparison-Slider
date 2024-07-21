package com.swoopzi.lib.imagecomparisonslider

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import com.swoopzi.lib.imagecomparisonslider.databinding.ImageCompareSliderBinding

class ImageCompareSlider @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        init(attrs)
    }

    private lateinit var binding: ImageCompareSliderBinding

    private fun init(attrs: AttributeSet?) {
        binding = ImageCompareSliderBinding.bind(View.inflate(context, R.layout.image_compare_slider, this))

        val styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.ImageCompareSlider)
        try {
            val drawableBackground =
                styledAttrs.getResourceId(R.styleable.ImageCompareSlider_background_image, 0)
            if (drawableBackground != 0) {
                val drawable = AppCompatResources.getDrawable(context, drawableBackground)
                binding.backgroundImage.setImageDrawable(drawable)
            }
            val drawableForeground =
                styledAttrs.getResourceId(R.styleable.ImageCompareSlider_foreground_image, 0)
            if (drawableForeground != 0) {
                val drawable = AppCompatResources.getDrawable(context, drawableForeground)
                binding.foregroundImage.setImageDrawable(drawable)
            }
            val drawableSliderIcon = styledAttrs.getResourceId(R.styleable.ImageCompareSlider_slider_icon, 0)
            if (drawableSliderIcon != 0) {
                val drawable = AppCompatResources.getDrawable(context, drawableSliderIcon)
                binding.sliderImage.setImageDrawable(drawable)
            }
        } finally {
            styledAttrs.recycle()
        }

        binding.sbImageSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                setImageWidth(i)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        binding.backgroundImage.post {
            binding.foregroundImage.layoutParams.height = binding.backgroundImage.height
            binding.foregroundImage.layoutParams.width = binding.backgroundImage.width
            binding.sbImageSeek.max = binding.backgroundImage.width
            binding.sliderBar.layoutParams.height = binding.backgroundImage.height
        }
    }

    private fun setImageWidth(progress: Int) {
        if (progress <= 0)
            return
        val lp: ViewGroup.LayoutParams = binding.target.layoutParams
        lp.width = progress
        binding.target.layoutParams = lp
    }
}