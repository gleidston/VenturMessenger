package br.org.venturus.venturmessenger

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.org.venturus.venturmessenger.database.firestormDAO
import br.org.venturus.venturmessenger.utils.Utils
import br.org.venturus.venturmessenger.viewmodel.LoginViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                activityResult(result.resultCode, result.data)
            }

        findViewById<Button>(R.id.loginButton).setOnClickListener {
            resultLauncher.launch(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(Utils().getProviders())
                    .build(),
            )
        }
    }

    fun activityResult(resultCode: Int, data: Intent?) {
        val response = IdpResponse.fromResultIntent(data)

        if (resultCode == Activity.RESULT_OK) {
            firestormDAO().insertUser()
        } else {
            Toast.makeText(this, response?.error?.message, Toast.LENGTH_LONG).show()
        }
    }

    fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun failToLogin(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
