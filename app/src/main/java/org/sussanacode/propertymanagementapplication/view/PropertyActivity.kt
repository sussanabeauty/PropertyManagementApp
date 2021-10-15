package org.sussanacode.propertymanagementapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import org.sussanacode.propertymanagementapplication.R
import org.sussanacode.propertymanagementapplication.api.ApiClient
import org.sussanacode.propertymanagementapplication.databinding.ActivityPropertyBinding
import org.sussanacode.propertymanagementapplication.viewmodel.PropertyViewModel
import org.sussanacode.propertymanagementapplication.viewmodel.PropertyViewModelFactory

class PropertyActivity : AppCompatActivity() {
    lateinit var binding: ActivityPropertyBinding
    lateinit var viewModel: PropertyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // binding = ActivityPropertyBinding.inflate(layoutInflater)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_property)


        binding.rvProperties.layoutManager = LinearLayoutManager(this)
        initViewModel()
        viewModel.loadProperties()

    }


    private fun initViewModel() {
        val factory = PropertyViewModelFactory(ApiClient.apiService)
        viewModel = ViewModelProvider(this, factory).get(PropertyViewModel::class.java)

        binding.viewmodel = viewModel
    }
}