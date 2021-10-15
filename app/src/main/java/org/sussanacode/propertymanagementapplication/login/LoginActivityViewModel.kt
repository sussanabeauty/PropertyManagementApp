package org.sussanacode.propertymanagementapplication.login

import android.util.Patterns
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sussanacode.propertymanagementapplication.api.ApiClient
import org.sussanacode.propertymanagementapplication.entity.request.LoginRequestData
import org.sussanacode.propertymanagementapplication.entity.response.LoginResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivityViewModel : ViewModel() {

    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val emailError = ObservableField<String>()
    val passwordError = ObservableField<String>()
    val progressBar = ObservableField<Boolean>()

    val loginResponse = MutableLiveData<LoginResponseData>()
    val error = MutableLiveData<String>()

    fun login() {
        if (!Patterns.EMAIL_ADDRESS.matcher(email.get().toString()).matches()) {
            emailError.set("")
            emailError.set("Invalid email address")
            return@login
        }

        if (password.get()?.length ?: 0 < 6) {
            passwordError.set("")
            passwordError.set("Invalid password")
            return@login
        }

        val userInfo = LoginRequestData(email.get() ?: "", password.get() ?: "")
        val call: Call<LoginResponseData> = ApiClient.apiService.login(userInfo)

        call.enqueue(object : Callback<LoginResponseData> {
            override fun onResponse(
                call: Call<LoginResponseData>,
                response: Response<LoginResponseData>
            ) {
                progressBar.set(false)
                if (!response.isSuccessful) {
                    error.postValue("Invalid username/password, please try again!")
                    return
                }
                loginResponse.postValue(response.body())
            }

            override fun onFailure(call: Call<LoginResponseData>, t: Throwable) {
                progressBar.set(false)
                error.postValue("Unable to login, please try again!")
            }
        })
        progressBar.set(true)
    }
}