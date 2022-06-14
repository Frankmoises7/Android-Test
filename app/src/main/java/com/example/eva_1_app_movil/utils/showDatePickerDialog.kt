package com.example.eva_1_app_movil.utils

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.eva_1_app_movil.ui.DatePickerFragment
import com.google.android.material.textfield.TextInputLayout
import java.util.*


//Declarar funcion lambda (funcion corta)
//Esto vendria siendo un metodo estatico en java

fun showDatePickerDialog(activity: AppCompatActivity, til: TextInputLayout, initialDate: Date){
    val listener = DatePickerDialog.OnDateSetListener { activity, year, month, day ->
        til.editText?.setText(String.format("%d-%02d-%02d", year, (month + 1), day))
    }
    val fragment = DatePickerFragment(listener, initialDate)
    fragment.show(activity.supportFragmentManager, "datePicker")
}

//Lo que sucede aqui es que cuando se llama el DatepickerFragmen, vamos a estar llamando
// a la funcion ShowDatePickerDialog para llamar el datepickerfragment