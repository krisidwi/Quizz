package com.krisi.quizz3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.krisi.quizz3.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textField.requestFocus()

        binding.txtUsername.doOnTextChanged { text, _, _, _ ->
            when {
                text.toString().isEmpty() ->
                    binding.textField.error = "Email tidak boleh kosong"
//                !Patterns.EMAIL_ADDRESS.matcher(text ?: "").matches() ->
//                    binding.textField.error = "Format email tidak valid"
                else -> binding.textField.error = null
            }
            validateButton()
        }
        binding.txtPw.doOnTextChanged { text, _, _, _ ->
            when {
                text.toString().isEmpty() -> {
                    binding.txtPassword.error = "Password tidak boleh kosong"
                }

                else -> binding.txtPassword.error = null
            }
            validateButton()
        }

        binding.btnMasuk.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        binding.txtDaftarDisini.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
            finishAffinity()
        }


    }

    private fun validateButton() {
        val emailOK = binding.textField.error == null
        val passOK = binding.txtPassword.error == null
        binding.btnMasuk.isEnabled = emailOK && passOK
    }


}