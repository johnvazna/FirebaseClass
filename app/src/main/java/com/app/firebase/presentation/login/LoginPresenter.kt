package com.app.firebase.presentation.login

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginPresenter(private val view: LoginController.View) : LoginController.Presenter {

    private val auth = FirebaseAuth.getInstance()

    override fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    override fun goToLogin(email: String, password: String) {
        when {
            email.isEmpty() -> {
                view.onError("Please type an email.")
            }
            password.isEmpty() -> {
                view.onError("Please type a password")
            }
            password.length < 6 -> {
                view.onError("Please write password with more length characters.")
            }
            else -> {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        view.onSuccess()

                    } else {
                        view.onError("Algo sucedio, intentelo más tarde.")
                    }
                }.addOnFailureListener {
                    view.onError(it.message.toString())
                }
            }
        }
    }
}