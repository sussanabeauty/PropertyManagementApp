package org.sussanacode.propertymanagementapplication.resetPassword

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import org.sussanacode.propertymanagementapplication.databinding.ActivityResetPasswordBinding
import org.sussanacode.propertymanagementapplication.register.RegisterActivity

class ResetPasswordActivity : AppCompatActivity() {
    lateinit var binding: ActivityResetPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        modifyTextView()
        initEvents()
    }

    private fun initEvents() {
        binding.btnSendResetLink.setOnClickListener{
            AlertDialog.Builder(this).apply{
                setMessage("API not supported")
                setPositiveButton("Sure") {
                    dialog, _ -> dialog.dismiss()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun modifyTextView() {
        val text = binding.tvRegister.text.toString()
        val spannable = SpannableString(text)
        val clickable = object: ClickableSpan() {
            override fun onClick(p0: View) {
                startActivity(Intent(baseContext, RegisterActivity::class.java))
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
                ds.color = Color.DKGRAY
            }
        }

        spannable.setSpan(
            clickable,
            text.length - "Sign up".length, text.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        binding.tvRegister.text = spannable
        binding.tvRegister.highlightColor = Color.TRANSPARENT
        binding.tvRegister.movementMethod = LinkMovementMethod.getInstance()
    }
}