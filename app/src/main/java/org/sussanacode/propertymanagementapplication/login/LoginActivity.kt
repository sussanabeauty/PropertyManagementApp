package org.sussanacode.propertymanagementapplication.login

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import org.sussanacode.propertymanagementapplication.register.RegisterActivity
import org.sussanacode.propertymanagementapplication.resetPassword.ResetPasswordActivity
import org.sussanacode.propertymanagementapplication.view.DashboardActivity
import org.sussanacode.propertymanagementapplication.R
import org.sussanacode.propertymanagementapplication.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: LoginActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        viewModel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)
        binding.userCredential = viewModel

        setupEvents()
        setupObservers()
        modifyTextView()
    }


    private fun setupObservers() {
        viewModel.error.observe(this) {
            Toast.makeText(baseContext, it, Toast.LENGTH_LONG).show()
        }

        viewModel.loginResponse.observe(this) { response ->
            // todo - store the user info in the local database
            startActivity(Intent(baseContext, DashboardActivity::class.java))
        }
    }

    private fun setupEvents() {
        binding.btnFacebookLogin.setOnClickListener {
            AlertDialog.Builder(this).apply{
                setMessage("API not supported, please try later")
                setPositiveButton("Sure") {
                    dialog, _ -> dialog.dismiss()
                }
                create()
                show()
            }
        }

        binding.btnGoogleSignIn.setOnClickListener {
            AlertDialog.Builder(this).apply{
                setMessage("API not supported, please try later")
                setPositiveButton("Sure") {
                        dialog, _ -> dialog.dismiss()
                }
                create()
                show()
            }
        }
    }

    private fun modifyTextView() {
        val modForgotPassword = binding.tvForgotPassword.text.toString()
        val forgotPasswordSpannable = SpannableString(modForgotPassword)
        val forgotPasswordClickable = object : ClickableSpan() {
            override fun onClick(p0: View) {
                startActivity(Intent(baseContext, ResetPasswordActivity::class.java))
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.DKGRAY
                ds.isUnderlineText = false
            }
        }

        forgotPasswordSpannable.setSpan(
            forgotPasswordClickable,
            0, binding.tvForgotPassword.length(),
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        binding.tvForgotPassword.text = forgotPasswordSpannable
        binding.tvForgotPassword.movementMethod = LinkMovementMethod.getInstance()
        binding.tvForgotPassword.highlightColor = Color.TRANSPARENT


        val modSignUp = binding.tvRegister.text.toString()
        val signUpSpannable = SpannableString(modSignUp)
        val signUpClickable = object : ClickableSpan() {
            override fun onClick(p0: View) {
                startActivity(Intent(baseContext, RegisterActivity::class.java))
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.DKGRAY
                ds.isUnderlineText = false
            }
        }
        signUpSpannable.setSpan(
            signUpClickable,
            24, binding.tvRegister.length(),
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        binding.tvRegister.text = signUpSpannable
        binding.tvRegister.movementMethod = LinkMovementMethod.getInstance()
        binding.tvRegister.highlightColor = Color.TRANSPARENT

    }
}