package com.astoganit.invitationcardmaker.uicomponent.activity

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.activity.ComponentActivity
import com.astoganit.invitationcardmaker.databinding.ActivityMainBinding
import com.astoganit.invitationcardmaker.uicomponent.custom.CustomTextView
import com.astoganit.invitationcardmaker.R


class MainActivity : ComponentActivity() {
    lateinit var binding: ActivityMainBinding
    private val pickImage = 100
    private var imageUri: Uri? = null
    var selectedView: View? = null;
    var previousSelectedView: View? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListener()

    }


    fun changeBackground() {

        if (previousSelectedView != null && previousSelectedView != selectedView && previousSelectedView is CustomTextView) {
            (previousSelectedView as CustomTextView).setCustomBackground(
                this,
                resources.getDrawable(R.drawable.rect_transparent)

            )
        }
        previousSelectedView = selectedView
    }

    private fun setListener() {

        binding.seekbar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                (previousSelectedView as CustomTextView).scaleText(progress.toFloat())
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {

            }
        })

        binding.backBtn.setOnClickListener {
            (previousSelectedView as CustomTextView).setLeftMargin()
        }
    }
}

