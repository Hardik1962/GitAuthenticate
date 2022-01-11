package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.OAuthProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseUser: FirebaseUser
    private lateinit var auth: FirebaseAuth
    private val provider = OAuthProvider.newBuilder("github.com")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        auth = FirebaseAuth.getInstance()

        val scopes: ArrayList<String?> = object : ArrayList<String?>() {
            init {
                add("user:email")
            }
        }

        provider.scopes = scopes

        btn_login.setOnClickListener {
                signInWithGithubProvider()
        }
    }

    private fun signInWithGithubProvider() {

        // There's something already here! Finish the sign-in for your user.
        val pendingResultTask: Task<AuthResult>? = auth.pendingAuthResult
        if (pendingResultTask != null) {
            pendingResultTask
                .addOnSuccessListener {
                    // User is signed in.
                    Toast.makeText(this, "User exist", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    // Handle failure.
                    Toast.makeText(this, "Error : $it", Toast.LENGTH_LONG).show()
                }
        } else {

            auth.startActivityForSignInWithProvider( this, provider.build())
                .addOnSuccessListener(
                    OnSuccessListener<AuthResult?> {
                        // User is signed in.
                        // retrieve the current user
                        firebaseUser = auth.currentUser!!
                        Log.e("username",firebaseUser.email.toString())

                        // navigate to HomePageActivity after successful login
                        val intent = Intent(this, DataActivity::class.java)

                        // send github user name from MainActivity to HomePageActivity
                        intent.putExtra("githubUserName", firebaseUser.email)
                        this.startActivity(intent)
                        Toast.makeText(this, "Login Successfully", Toast.LENGTH_LONG).show()

                    })
                .addOnFailureListener(
                    OnFailureListener {
                        // Handle failure.
                        Toast.makeText(this, "Error : $it", Toast.LENGTH_LONG).show()
                    })
        }

    }
}