package org.sussanacode.propertymanagementapplication.holder

import androidx.recyclerview.widget.RecyclerView

import org.sussanacode.propertymanagementapplication.databinding.HolderPropertyBinding
import org.sussanacode.propertymanagementapplication.entity.response.Property

class PropertyListHolder(val binding: HolderPropertyBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(property: Property){
        binding.properties = property
    }

}