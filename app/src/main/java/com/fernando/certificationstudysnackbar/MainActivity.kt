package com.fernando.certificationstudysnackbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.fernando.certificationstudysnackbar.databinding.ActivityMainBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

private const val snack_hidden = "Your SnackBar is hidden.."
private const val snack_visible = "Your SnackBar is visible.."

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var snackbar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lblTitle.text = snack_hidden

        binding.apply {
            btnShow.setOnClickListener { showClick() }
        }
    }

    private fun getMessage(): String {
        if (binding.edtMessage.text.toString().isNotEmpty()) {
            return binding.edtMessage.text.toString()
        }
        return "You didn't type anything!"
    }

    private fun getAnimationMode(): Int {
        if (binding.rbFade.isChecked) {
            return Snackbar.ANIMATION_MODE_FADE
        }
        return Snackbar.ANIMATION_MODE_SLIDE
    }

    private fun getDuration(): Int {
        return when {
            binding.rbLong.isChecked -> Snackbar.LENGTH_LONG
            binding.rbShort.isChecked -> Snackbar.LENGTH_SHORT
            else -> Snackbar.LENGTH_INDEFINITE
        }
    }

    private fun getBackground(): Int {
        return when {
            binding.rbBackRed.isChecked -> R.color.red
            binding.rbBackBlue.isChecked -> R.color.blue
            else -> R.color.yellow
        }
    }

    private fun getTextColor(): Int {
        return when {
            binding.rbTextGreen.isChecked -> R.color.green
            binding.rbTextOrange.isChecked -> R.color.orange
            else -> R.color.purple
        }
    }

    private fun getActionColor(): Int {
        return when {
            binding.rbActionBlack.isChecked -> R.color.black
            else -> R.color.white
        }
    }

    private val callBack = object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
        override fun onShown(transientBottomBar: Snackbar?) {
            super.onShown(transientBottomBar)
            binding.lblTitle.text = snack_visible
        }

        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
            super.onDismissed(transientBottomBar, event)
            binding.lblTitle.text = snack_hidden
        }
    }

    private fun showClick() {
        snackbar = Snackbar.make(binding.lblContent, getMessage(), getDuration())

        snackbar.setAction("Cancel Snack") { snackbar.dismiss() }
        snackbar.setBackgroundTint(ContextCompat.getColor(this, getBackground()))
        snackbar.setTextColor(ContextCompat.getColor(this, getTextColor()))
        snackbar.setActionTextColor(ContextCompat.getColor(this, getActionColor()))


        snackbar.addCallback(callBack)

        snackbar.animationMode = getAnimationMode()

        snackbar.show()
    }
}