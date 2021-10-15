package org.sussanacode.propertymanagementapplication.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sussanacode.propertymanagementapplication.api.ApiClient
import org.sussanacode.propertymanagementapplication.api.ApiService
import org.sussanacode.propertymanagementapplication.entity.request.PropertyRequestData
import org.sussanacode.propertymanagementapplication.entity.response.Property
import org.sussanacode.propertymanagementapplication.entity.response.PropertyResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PropertyViewModel(val apiService: ApiService): ViewModel() {

    val propertyResponses = ObservableField<List<Property>>()
    val loading = ObservableField<Boolean>()
    val error = MutableLiveData<String>()


    val addproductResponse = MutableLiveData<PropertyResponseData>()
    val address = ObservableField<String>("")
    val city = ObservableField<String>("")
    val country =  ObservableField<String>("")
    val image = ObservableField<String>("")
    val latitude = ObservableField<String>("")
    val longitude = ObservableField<String>("")
    val mortageInfo  = ObservableField<Boolean>()
    val propertyStatus = ObservableField<Boolean>()
    val purchasePrice = ObservableField<String>("")
    val state = ObservableField<String>("")
    val userId = ObservableField<String>("")
    val userType = ObservableField<String>("")



    //add property
    fun addProperty(){

        val addpropertyrequest = PropertyRequestData(
            address.get()?:"", city.get()?:"", state.get()?:"", country.get()?:"",
            image.get()?:"", latitude.get()?:"", longitude.get()?:"", mortageInfo.get()?: false,
            propertyStatus.get()?: false , purchasePrice.get()?:"", userId.get()?:"", userType.get()?:""
        )

        val call : Call<PropertyResponseData> = ApiClient.apiService.postProperty(addpropertyrequest)

        call.enqueue(object: Callback<PropertyResponseData> {
            override fun onResponse(call: Call<PropertyResponseData>,
                                    response: Response<PropertyResponseData>)
            {

                loading.set(false)
                if(!response.isSuccessful){
                    error.postValue("Failed to add new Property. Error code: ${response.code()}")
                    return
                }

                addproductResponse.postValue(response.body())

            }

            override fun onFailure(call: Call<PropertyResponseData>, t: Throwable) {
                loading.set(false)
                t.printStackTrace()
                error.postValue("Failed to add new property user. Error: ${t.toString()}")
            }
        })
        loading.set(true)


    }


    //get all property by userid
    fun loadPropertyBy(userId: String){

        error.postValue("")

        val call: Call<PropertyResponseData> = apiService.getPropertyByUserID(userId)

        call.enqueue(object: Callback<PropertyResponseData> {

            override fun onResponse(call: Call<PropertyResponseData>, response: Response<PropertyResponseData>
            ) {
                loading.set(false)

                if(!response.isSuccessful){
                    error.postValue("Failed to get properties. Error code: ${response.code()}")
                    return
                }

                val getProperties = response.body()

                getProperties?.let { propertiesData ->

                    if(propertiesData.error) {
                        error.postValue("Failed to get properties.")
                        return
                    }
                    propertyResponses.set(propertiesData.data)
                }
            }

            override fun onFailure(call: Call<PropertyResponseData>, t: Throwable) {
                loading.set(false)
                // t.printStackTrace()
                error.postValue("Failed to get properties. Error: ${t.toString()}")
            }

        })
        loading.set(true)

    }




    //get all properties
    fun loadProperties(){

        error.postValue("")

        val call: Call<PropertyResponseData> = apiService.getProperty()

        call.enqueue(object: Callback<PropertyResponseData> {

            override fun onResponse(call: Call<PropertyResponseData>, response: Response<PropertyResponseData>
            ) {
                loading.set(false)

                if(!response.isSuccessful){
                    error.postValue("Failed to get properties. Error code: ${response.code()}")
                    return
                }

                val getProperties = response.body()

                getProperties?.let { propertiesData ->

                    if(propertiesData.error) {
                        error.postValue("Failed to get properties.")
                        return
                    }
                    propertyResponses.set(propertiesData.data)
                }
            }

            override fun onFailure(call: Call<PropertyResponseData>, t: Throwable) {
               loading.set(false)
               // t.printStackTrace()
                error.postValue("Failed to get properties. Error: ${t.toString()}")
            }

        })
        loading.set(true)
    }
}