package br.org.venturus.venturmessenger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.org.venturus.venturmessenger.viewmodel.SplashViewModel

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(SplashViewModel().userIsLogged(context = this))
        finish()
    }
}