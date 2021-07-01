package com.zhaoxuan.myandroidtraining.activity

import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.zhaoxuan.myandroidtraining.R

/**
 * author: zhaoxuan
 * created on: 2021/7/1
 * description:
 */
class ConstraintSetUsingActivity : AppCompatActivity() {

    private val constraintNormal = ConstraintSet()
    private val constraintSet = ConstraintSet()
    lateinit var constraintLayout: ConstraintLayout
    var hasMoveToBottom = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_constraint_set1)
        constraintLayout = findViewById(R.id.constraint_layout)
        constraintNormal.clone(constraintLayout)
        constraintSet.load(this, R.layout.layout_constraint_set2)
    }

    fun moveBtn(view: View) {
        hasMoveToBottom = !hasMoveToBottom
        TransitionManager.beginDelayedTransition(constraintLayout)
        if (hasMoveToBottom) {
            constraintNormal.applyTo(constraintLayout)
        } else {
            constraintSet.applyTo(constraintLayout)
        }
    }
}