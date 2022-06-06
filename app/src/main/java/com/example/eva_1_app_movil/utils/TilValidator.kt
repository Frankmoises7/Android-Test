package com.example.eva_1_app_movil.utils

import android.util.Patterns
import com.google.android.material.textfield.TextInputLayout

class TilValidator constructor(private val til: TextInputLayout){
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

    fun isValid(): Boolean {
        return  !this.invalid
    }
}