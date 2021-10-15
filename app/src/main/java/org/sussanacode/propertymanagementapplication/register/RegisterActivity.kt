package org.sussanacode.propertymanagementapplication.register

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
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import org.sussanacode.propertymanagementapplication.R
import org.sussanacode.propertymanagementapplication.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var viewModel: RegisterActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        viewModel = ViewModelProvider(this).get(RegisterActivityViewModel::class.java)
        binding.userDetails = viewModel

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        modifyTextView()
        initObservers()
    }


    private fun initObservers() {
        viewModel.error.observe(this) {
            Toast.makeText(baseContext, it, Toast.LENGTH_LONG).show()
        }

        viewModel.registerResponse.observe(this) {
            Toast.makeText(baseContext, "Register successfully, please sign in", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun modifyTextView() {
        val text = binding.tvLogIn.text.toString()
        val spannable = SpannableString(text)
        val clickable = object: ClickableSpan() {
            override fun onClick(p0: View) {
                finish()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.DKGRAY
                ds.isUnderlineText = false
            }
        }

        spannable.setSpan(
            clickable,
            text.length - "Log in".length, text.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        binding.tvLogIn.text = spannable
        binding.tvLogIn.movementMethod = LinkMovementMethod.getInstance()
        binding.tvLogIn.highlightColor = Color.TRANSPARENT
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}