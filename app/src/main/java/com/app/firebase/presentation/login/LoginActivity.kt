package com.app.firebase.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.firebase.R
import com.app.firebase.presentation.forgot.ForgotActivity
import com.app.firebase.presentation.main.MainActivity

class LoginActivity : AppCompatActivity(), LoginController.View, View.OnClickListener {

    private lateinit var btnLogin: Button
    private lateinit var etEmail: EditText
    private lateinit var tvSignUp: TextView
    private lateinit var tvForgot: TextView
    private lateinit var etPassword: EditText

    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        presenter = LoginPresenter(this)

        btnLogin = findViewById(R.id.btn_login)
        btnLogin.setOnClickListener(this)

        tvForgot = findViewById(R.id.tv_forgot_password)
        tvForgot.setOnClickListener(this)

        tvSignUp = findViewById(R.id.tv_create_account)
        tvSignUp.setOnClickListener(this)

        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
    }

    override fun onStart() {
        super.onStart()
        if (presenter.getCurrentUser() != null) {
            onSuccess()
        }
    }

    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_login -> {
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()
                presenter.goToLogin(email, password)
            }
            R.id.tv_forgot_password -> {
                startActivity(Intent(this, ForgotActivity::class.java))
            }
            R.id.tv_create_account -> {
                startActivity(Intent(this, ForgotActivity::class.java))
            }
        }
    }
}