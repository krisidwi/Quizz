package com.krisi.quizz3

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.krisi.quizz3.databinding.ActivityRegisterBinding
import java.util.regex.Pattern


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textField.requestFocus()

        binding.txtName.doOnTextChanged { text, _, _, _ ->
            when {
                text.toString().isEmpty() ->
                    binding.textField.error = "Nama tidak boleh kosong"

                else -> binding.textField.error = null
            }
            validateButton()
        }
        binding.txtEmail.doOnTextChanged { text, _, _, _ ->
            when {
                text.isNullOrEmpty() ->
                    binding.textField1.error = "Email tidak boleh kosong"

                !Patterns.EMAIL_ADDRESS.matcher(text ?: "").matches() ->
                    binding.textField1.error = "Format email tidak valid"

                else -> binding.textField1.error = null
            }
            validateButton()
        }
        binding.txtUsername.doOnTextChanged { text, _, _, _ ->
            when {
                text.isNullOrEmpty() ->
                    binding.textField2.error = "Username tidak boleh kosong"

                text.length < 3 || text.length > 15 ->
                    binding.textField2.error =
                        "Username minimal 3 karakter dan maksimal 15 karakter"

                else -> binding.textField2.error = null
            }
            validateButton()
        }
        binding.txtPw.doOnTextChanged { text, _, _, _ ->
            val txt = text.toString().trim()
            when {
                txt.isEmpty() ->
                    binding.txtPassword.error = "Kata sandi tidak boleh kosong"

                !isPasswordValid(txt) ->
                    binding.txtPassword.error =
                        "Kata sandi minimal 8 karakter dengan kombinasi huruf, angka dan satu huruf kapital"

                txt != binding.txtKonfirPw.text.toString() -> {
                    binding.txtKonfir.error = "Konfirmasi kata sandi tidak sama"
                    binding.txtPassword.error = null
                }

                else -> {
                    binding.txtPassword.error = null
                    binding.txtKonfir.error = null
                }
            }
            validateButton()
        }
        binding.txtKonfirPw.doOnTextChanged { text, _, _, _ ->
            when {
                text.isNullOrEmpty() ->
                    binding.txtKonfir.error = "Konfirmasi sandi tidak boleh kosong"

                !text.isNullOrEmpty() && text.toString() != binding.txtPw.text.toString() ->
                    binding.txtKonfir.error = "Konfirmasi kata sandi tidak sama"

                else -> binding.txtKonfir.error = null
            }
            validateButton()
        }

        binding.txtMasuk.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)

        }
        binding.btnDaftar.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z]).{8,}$"
        val pattern = Pattern.compile(passwordPattern)
        val matcher = pattern.matcher(password)

        return matcher.matches()
    }

    private fun validateButton() {
        val nameOK = binding.textField.error == null
        val emailOK = binding.textField1.error == null
        val usernameOK = binding.textField2.error == null
        val passOK = binding.txtPassword.error == null
        val konfirOK = binding.txtKonfir.error == null


        binding.btnDaftar.isEnabled =
            nameOK && emailOK && usernameOK && passOK && konfirOK
    }


}

