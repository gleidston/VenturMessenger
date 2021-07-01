package br.org.venturus.venturmessenger.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import br.org.venturus.venturmessenger.LoginActivity
import br.org.venturus.venturmessenger.MainActivity
import br.org.venturus.venturmessenger.utils.Utils

class SplashViewModel : ViewModel() {

    fun userIsLogged(context: Context): Intent {
        return if(Utils().getUser() == null) {
            Intent(context, LoginActivity::class.java)
        } else {
            Intent(context, MainActivity::class.java)
        }
    }
}