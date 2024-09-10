package com.example.myanimationproject

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.icu.text.ListFormatter.Width
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.Animation.INFINITE
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import java.util.EventListener

class MainActivity : AppCompatActivity() {

    lateinit var txt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt = findViewById(R.id.txtmain)

        animate(txt)


    }
    fun animate(view: View) {
        val parentView = view.parent as ViewGroup

        // image 1
        val image = AppCompatImageView(view.context)
        image.setImageResource(R.drawable.scribblecircle)
        image.layoutParams = ViewGroup.LayoutParams(150, 150)
//        image.scaleX = 3f
//        image.scaleY = image.scaleX
        parentView.addView(image)

        // image 2
        val image2 = AppCompatImageView(view.context)
        image2.setImageResource(R.drawable.halfcircle)
        image2.layoutParams = ViewGroup.LayoutParams(300, 300)
//        image2.scaleX = 3f
//        image2.scaleY = image2.scaleX
        parentView.addView(image2)

        // set screen dimensions
        val screenSize = view.context.resources.displayMetrics
        val screenWidth = screenSize.widthPixels
        val screenHeight = screenSize.heightPixels

        // Start off-screen to the right
        image.x = screenWidth.toFloat() + image.layoutParams.width
        image.y = ((screenHeight / 2) - (image.layoutParams.height / 2)).toFloat()

        image2.x = screenWidth.toFloat() + image2.layoutParams.width
        image2.y = ((screenHeight / 2) - (image2.layoutParams.height / 2)).toFloat()
        image2.rotation = -90f

        // serrated circle animations
        // move to center
        val moveToCenter = ObjectAnimator.ofFloat(image, "translationX", (screenWidth / 2f) - (image.layoutParams.width / 2f)).apply {
            duration = 1000
        }

        // spin
        val spin = ObjectAnimator.ofFloat(image, "rotation", 0f, 360f).apply {
            duration = 1000
        }

        // move from center to left side of screen
        val moveToLeft = ObjectAnimator.ofFloat(image, "translationX", (-image.layoutParams.width - image.layoutParams.width).toFloat()).apply {
            duration = 1000
        }

        // half circle animations
        // move to center
        val moveToCenter2 = ObjectAnimator.ofFloat(image2, "translationX", (screenWidth / 2f) - (image2.layoutParams.width / 2f)).apply {
            duration = 1000
        }

        // spin
        val spin2 = ObjectAnimator.ofFloat(image2, "rotation", 270f, 90f).apply {
            duration = 1000
        }

        // move from center to left side of screen
        val moveToLeft2 = ObjectAnimator.ofFloat(image2, "translationX", (-image2.layoutParams.width - image2.layoutParams.width).toFloat()).apply {
            duration = 1000
        }

        // constant spin
        val constantSpin = ObjectAnimator.ofFloat(image, "rotation", -360f, 0f)
//        constantSpin.repeatMode = ValueAnimator.RESTART
        constantSpin.repeatCount = INFINITE


        // play animations
        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(moveToCenter2, moveToCenter, spin2, moveToLeft, moveToLeft2)

        animatorSet.start()
        constantSpin.start()

        animatorSet.addListener(object: AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                animate(txt)
            }

        })

    }

}