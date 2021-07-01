package br.org.venturus.venturmessenger.utils

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import br.org.venturus.venturmessenger.MainActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Utils {
    fun getUser() : FirebaseUser? {
        return  FirebaseAuth.getInstance().currentUser
    }

    fun getProviders() : ArrayList<AuthUI.IdpConfig> {
        return arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
    }
}