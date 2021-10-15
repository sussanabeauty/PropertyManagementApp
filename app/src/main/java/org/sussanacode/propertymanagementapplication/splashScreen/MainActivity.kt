package org.sussanacode.propertymanagementapplication.splashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import org.sussanacode.propertymanagementapplication.databinding.ActivityMainBinding
import org.sussanacode.propertymanagementapplication.login.LoginActivity
import org.sussanacode.propertymanagementapplication.view.DashboardActivity


class MainActivity : AppCompatActivity() {

    companion object {
        const val LAUNCH_LOGIN_SCREEN = 100
        const val LAUNCH_DASHBOARD_SCREEN = 200
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // check the sharedPref file if the user has logged in
        handler.sendEmptyMessageDelayed(LAUNCH_LOGIN_SCREEN, 3000)
    }

    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            if (msg.what == LAUNCH_DASHBOARD_SCREEN) {
                startActivity(Intent(baseContext, DashboardActivity::class.java))
                finish()
            } else if (msg.what == LAUNCH_LOGIN_SCREEN) {
                startActivity(Intent(baseContext, LoginActivity::class.java))
                finish()
            }
        }
    }

}