package org.sussanacode.propertymanagementapplication.register

import android.util.Patterns
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sussanacode.propertymanagementapplication.api.ApiClient
import org.sussanacode.propertymanagementapplication.entity.request.RegisterRequestData
import org.sussanacode.propertymanagementapplication.entity.response.RegisterResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivityViewModel : ViewModel() {

    var error: MutableLiveData<String> = MutableLiveData()
    var registerResponse: MutableLiveData<RegisterResponseData> = MutableLiveData()

    var progressBar = ObservableField<Boolean>()
    var email = ObservableField<String>()
    var emailError = ObservableField<String>()
    var password = ObservableField<String>()
    var passwordError = ObservableField<String>()
    var type = ObservableField<String>()
    var landlordEmail = ObservableField<String>()
    var landlordEmailError = ObservableField<String>()
    var name = ObservableField<String>()
    var nameError = ObservableField<String>()
    var confirmPassword = ObservableField<String>()
    var confirmPasswordError = ObservableField<String>()

    var tenantRadioButton = ObservableField<Boolean>()

    fun register() {
        // input validation
        if (!Patterns.EMAIL_ADDRESS.matcher(email.get().toString()).matches()) {
            emailError.set("")
            emailError.set("Invalid email address")
            return@register
        }
        if (password.get()?.length ?: 0 < 6) {
            passwordError.set("")
            passwordError.set("Password length must be at least 6 characters")
            return@register
        }
        if (password.get() != confirmPassword.get()) {
            confirmPasswordError.set("")
            confirmPasswordError.set("Password not matched")
            return@register
        }
        if (name.get()?.isEmpty() == true) {
            nameError.set("")
            nameError.set("Invalid name")
            return@register
        }

        if (tenantRadioButton.get() == true) {
            if (!Patterns.EMAIL_ADDRESS.matcher(landlordEmail.get().toString()).matches()) {
                landlordEmailError.set("")
                landlordEmailError.set("Invalid email address")
                return@register
            }
        }

        val userInfo = RegisterRequestData(
            email.get() ?: "",
            name.get() ?: "",
            password.get() ?: "",
            type.get() ?: "",
            landlordEmail.get() ?: ""
        )
        val call: Call<RegisterResponseData> = ApiClient.apiService.register(userInfo)
        call.enqueue(object : Callback<RegisterResponseData> {
            override fun onResponse(
                call: Call<RegisterResponseData>,
                response: Response<RegisterResponseData>
            ) {
                progressBar.set(false)
                if (!response.isSuccessful) {
                    error.postValue("Please select either landlord or tenant and try again!")
//                    error.postValue("We cannot complete the register process right now, please come back later.")
                    return
                }
                registerResponse.postValue(response.body())
            }

            override fun onFailure(call: Call<RegisterResponseData>, t: Throwable) {
                progressBar.set(false)
                error.postValue("Unable to send register request, please try again")
            }
        })
        progressBar.set(true)
    }

    fun landlordChecked() {
        tenantRadioButton.set(false)
        type.set("landlord")
        landlordEmail.set(null)
    }

    fun tenantChecked() {
        tenantRadioButton.set(true)
        type.set("tenant")
    }
}