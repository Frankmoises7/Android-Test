package com.example.eva_1_app_movil.utils

import android.util.Patterns
import com.google.android.material.textfield.TextInputLayout
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class TilValidator constructor(private val til: TextInputLayout){
    private val formater = SimpleDateFormat("yyyy-MM-dd")
    private val value: String = til.editText?.text.toString()
    private var required: Boolean = false
    private var invalid: Boolean = false

    private fun setError(invalid: Boolean, message: String) {
        if (invalid) {
            this.invalid = true
            til.error = message
        } else {
            til.error = null
            til.isErrorEnabled = false
        }
    }

    private fun mustValidate(): Boolean {
        return (!this.required && this.value.isNotEmpty() || !invalid)
    }

     fun required(): TilValidator {
        this.required = true
        val invalidField = this.value.isEmpty()
        this.setError(invalidField, "Campo requerido")
        return this
    }

    fun email(): TilValidator {
        if (mustValidate()) {
            val invalidField = !Patterns.EMAIL_ADDRESS.matcher(this.value).matches()
            this.setError(invalidField, "El formato del Email es invalido")
        }
        return this
    }

    fun DateBefore(date: Date): TilValidator {
        if (mustValidate()) {
            var invalidField = false
            try {
                val dateValue = formater.parse(this.value)
                invalidField = !dateValue.after(date)
            } catch (e: Exception) {
                invalidField = true
            }
            this.setError(invalidField, "La fecha no debe ser anterior a ${formater.format(date)}")
        }
        return this
    }

    fun DateAfter(date: Date): TilValidator {
        if (mustValidate()) {
            var invalidField = false
            try {
                val dateValue = formater.parse(this.value)
                invalidField = !dateValue.before(date)
            } catch (e: Exception) {
                invalidField = true
            }
            this.setError(invalidField, "La fecha no puede ser posterior a ${formater.format(date)}")
        }
        return this
    }

    fun isValid(): Boolean {
        return  !this.invalid
    }
}