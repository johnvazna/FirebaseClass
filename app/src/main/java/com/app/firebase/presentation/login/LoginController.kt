package com.app.firebase.presentation.login

import com.google.firebase.auth.FirebaseUser

interface LoginController {

    interface View {
        fun onError(msg: String)
        fun onSuccess()
    }

    interface Presenter {
        fun getCurrentUser(): FirebaseUser?
        fun goToLogin(email: String, password: String)
    }
}