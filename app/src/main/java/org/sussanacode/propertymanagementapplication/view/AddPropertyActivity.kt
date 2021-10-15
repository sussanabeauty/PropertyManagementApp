package org.sussanacode.propertymanagementapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import org.sussanacode.propertymanagementapplication.api.ApiClient
import org.sussanacode.propertymanagementapplication.viewmodel.PropertyViewModel
import org.sussanacode.propertymanagementapplication.viewmodel.PropertyViewModelFactory
import org.sussanacode.propertymanagementapplication.R
import org.sussanacode.propertymanagementapplication.databinding.ActivityAddPropertyBinding

class AddPropertyActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddPropertyBinding
    lateinit var viewModel: PropertyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_property)

        setupevents()
        setUpViewModel()
        setUpObservers()

    }

    private fun setupevents() {

        binding.tvaddress.setOnClickListener {
            binding.llAddress.visibility = VISIBLE
        }

        binding.tvpopertydetails.setOnClickListener {
            binding.llAddress.visibility = GONE
            binding.llPropertyDetails.visibility = VISIBLE

        }

        binding.tvmortgageinfo.setOnClickListener {
            binding.llPropertyDetails.visibility = GONE
            binding.llMortgageInfo.visibility = VISIBLE
        }

    }


    private fun setUpObservers() {
        viewModel.addproductResponse.observe(this ){

            it.error?.let { hasError ->
                if(hasError){
                    it?.message?.let { message -> Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show() }

                }
            }
        }
    }

    private fun setUpViewModel() {
        val factory = PropertyViewModelFactory(ApiClient.apiService)
        viewModel = ViewModelProvider(this, factory).get(PropertyViewModel::class.java)
        binding.viewmodel = viewModel
    }
}