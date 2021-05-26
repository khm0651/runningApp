package com.example.runningappvita.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.runningappvita.R
import com.example.runningappvita.VitaAsyncStorage
import com.example.runningappvita.databinding.ActivityLoginBinding
import com.example.runningappvita.utils.Device
import com.example.runningappvita.utils.FirebaseMessagingHelper
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import java.util.function.Consumer
import javax.inject.Inject

@AndroidEntryPoint
class LoginAct : AppCompatActivity() {

    private val TAG = LoginAct::class.java.simpleName
    private lateinit var viewModel: LoginVM
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient
    private lateinit var binding: ActivityLoginBinding
    private val RC_SIGN_IN = 1001

    @Inject
    lateinit var vitaAsyncStorage: VitaAsyncStorage



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(LoginVM::class.java)

        setUp()
    }

    private fun setUp() {
        setUpConfigureGoogleClient()
        setUpLoginClickListener()
    }

    private fun setUpConfigureGoogleClient() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)
        firebaseAuth = FirebaseAuth.getInstance()
    }

    private fun setUpLoginClickListener() {
        binding.googleLoginBtn.setOnClickListener {
            if(Device.checkonlineStatus(this)) loginGoogleAccount()
            else Toast.makeText(this,"wi-fi와 데이터 연결 상태를 확인해 주세요",Toast.LENGTH_LONG).show()
        }
    }

    private fun loginGoogleAccount() {
        val signInIntent = googleSignInClient.signInIntent
        googleLoginResultLauncher.launch(signInIntent)
    }

    private val googleLoginResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->

        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            Log.d(TAG,"Google sign in Succeeded")
            firebaseAuthWithGoogle(account)
        }catch (e: ApiException){
            Log.d(TAG,"Google sign in Failed resultCode : ${result.resultCode}", e)
        }

    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        Log.d(TAG,"firebaseAuthWithgoogle: ${account.id}")
        val credential : AuthCredential = GoogleAuthProvider.getCredential(account.idToken,null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    Log.d(TAG, "FIREBASE - success: currentUser: ${user.email}")
                    getFcmIdToken()
                    vitaAsyncStorage.getUserStore().setGoogleIdToken(account.idToken!!, account.email!!)
                    handleWhenFirebaseSuccess(task.result, account)
                }
            }
    }

    private fun handleWhenFirebaseSuccess(authResult: AuthResult, account: GoogleSignInAccount) {
        val isNewUser = authResult.additionalUserInfo != null && authResult.additionalUserInfo.isNewUser
        if(isNewUser){
//            goPolicyScreen()
        }else{

        }
    }

    private fun getFcmIdToken() {
        FirebaseMessagingHelper.getFCMToken(Consumer { fcmIdToken ->
            Log.d(TAG,"FIREBASE Fcm Token: ${fcmIdToken}")
            vitaAsyncStorage.getUserStore().setFcmAccessToken(fcmIdToken)
        })
    }


}