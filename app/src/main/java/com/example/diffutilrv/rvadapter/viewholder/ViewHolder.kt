package com.example.diffutilrv.rvadapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutilrv.R
import com.example.diffutilrv.rvadapter.payload.CheckStateChange
import com.example.diffutilrv.uimodel.EmployeeCheckbox

class ViewHolder(
    itemView: View,
    onCheckedChangeListener: (adapterPosition: Int, isChecked: Boolean) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun newInstance(
            parent: ViewGroup,
            onCheckedChangeListener: (adapterPosition: Int, isChecked: Boolean) -> Unit
        ) =
            ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false),
                onCheckedChangeListener
            )
    }

    private val role: TextView = itemView.findViewById(R.id.employee_role)
    private val name: TextView = itemView.findViewById(R.id.employee_name)
    private val cost: TextView = itemView.findViewById(R.id.employee_cost)
    private val checkbox: CheckBox = itemView.findViewById(R.id.employee_checkbox)

    private val checkboxListener = CompoundButton.OnCheckedChangeListener { _, isChecked ->
        onCheckedChangeListener(bindingAdapterPosition, isChecked)
    }

    fun bind(employee: EmployeeCheckbox) {
        name.text = employee.name
        role.text = employee.role
        cost.text = employee.cost.toString()

        setCheckboxWithoutNotifyListener(employee.isChecked)
    }

    private fun setCheckboxWithoutNotifyListener(isChecked: Boolean) {
        checkbox.setOnCheckedChangeListener(null)
        checkbox.isChecked = isChecked
        checkbox.setOnCheckedChangeListener(checkboxListener)
    }

    fun onPartialUpdate(payloads: MutableList<Any>) {
        (payloads[0] as? CheckStateChange)?.let {
            setCheckboxWithoutNotifyListener(it.newState)
        }
    }
}