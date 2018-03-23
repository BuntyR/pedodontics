package com.dentist.pedodontics.features.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.dentist.pedodontics.R
import com.dentist.pedodontics.features.drawer.DrawerActivity
import com.dentist.pedodontics.util.Constants
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.orhanobut.logger.Logger
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_authentication.*
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class AuthenticationActivity : AppCompatActivity() {


  @Inject
  lateinit var mAuth: FirebaseAuth

  lateinit var mGoogleSignInClient: GoogleSignInClient

  lateinit var mCallback: OnVerificationStateChangedCallbacks

  lateinit var mFacebookCallbackManager: CallbackManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_authentication)
    AndroidInjection.inject(this)

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(getString(R.string.default_web_client_id))
        .requestEmail()
        .requestProfile()
        .build()

    mFacebookCallbackManager = CallbackManager.Factory.create();
    fblogin_button.setReadPermissions(Arrays.asList("email"));

    mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
  }


  override fun onStart() {
    super.onStart()
    handleClickListeners()
    handleCallBacks()
  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)
    val fbIconScale = 1.45F;
    val drawable = this.getResources().getDrawable(
        com.facebook.login.R.drawable.com_facebook_button_icon);
    drawable.setBounds(0, 0, ((drawable.getIntrinsicWidth() * fbIconScale).toInt()),
        ((drawable.getIntrinsicHeight() * fbIconScale).toInt()));
    fblogin_button.setCompoundDrawables(drawable, null, null, null);
    fblogin_button.setCompoundDrawablePadding(
        this.getResources().getDimensionPixelSize(R.dimen.fb_margin_override_textpadding));
    fblogin_button.setPadding(
        this.getResources().getDimensionPixelSize(
            R.dimen.fb_margin_override_lr),
        this.getResources().getDimensionPixelSize(
            R.dimen.fb_margin_override_top),
        this.getResources().getDimensionPixelSize(
            R.dimen.fb_margin_override_lr),
        this.getResources().getDimensionPixelSize(
            R.dimen.fb_margin_override_bottom));
  }


  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
    if (requestCode == Constants.GOOGLE_SIGN_IN) {
      val task = GoogleSignIn.getSignedInAccountFromIntent(data)
      handleSignInResult(task)

    }
  }

  private fun handleCallBacks() {
    mCallback = object : OnVerificationStateChangedCallbacks() {
      override fun onVerificationCompleted(p0: PhoneAuthCredential?) {
        Logger.d("Firebase PhoneAuthCredential : $p0")
        signInWithPhoneAuthCredential(p0);
      }

      override fun onVerificationFailed(p0: FirebaseException?) {
        if (p0 is FirebaseAuthInvalidCredentialsException) {
          Logger.d("Firebase Invalid Request")
        } else if (p0 is FirebaseTooManyRequestsException) {
          Logger.d("Firebase Free Requests Completed")
        }
      }

      override fun onCodeSent(p0: String?, p1: PhoneAuthProvider.ForceResendingToken?) {
        super.onCodeSent(p0, p1)
        Logger.d("Code is $p0")
        Logger.d("Token is $p1")
      }
    }


    fblogin_button.registerCallback(mFacebookCallbackManager,
        object : FacebookCallback<LoginResult> {
          override fun onSuccess(result: LoginResult?) {
            Logger.d("facebook:onSuccess:" + result);
            if (result != null) {
              handleFacebookAccessToken(result.accessToken)
            }

          }

          override fun onCancel() {
            Logger.d("facebook:onCancel")

          }

          override fun onError(error: FacebookException?) {
            Logger.d("facebook:onError", error)
          }
        });

  }


  private fun handleClickListeners() {

    loginBtn.setOnClickListener(View.OnClickListener {
      PhoneAuthProvider.getInstance().verifyPhoneNumber(mobileNumber.text.toString(), 120,
          TimeUnit.SECONDS, this, mCallback)
    })

    sign_in_button.setOnClickListener { google -> googleSignIn() }

  }

  private fun signInWithPhoneAuthCredential(p0: PhoneAuthCredential?) {
    if (p0 != null) {
      mAuth.signInWithCredential(p0)
          .addOnCompleteListener(this, object : OnCompleteListener<AuthResult> {
            override fun onComplete(task: Task<AuthResult>) {
              if (task.isSuccessful()) {
                Logger.d("signInWithCredential:success")
                val user = task.getResult().getUser()
                handleUser(user)
              } else {
                Logger.d("signInWithCredential:failure : ${task.exception}")

                if (task.getException() is FirebaseAuthInvalidCredentialsException) {
                  // The verification code entered was invalid
                }
              }
            }
          })
    }
  }

  private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
    try {
      val account = completedTask.getResult(ApiException::class.java)

      // Signed in successfully, show authenticated UI.
      Logger.d("Google Sign In Result : ${account.email}")
      firebaseAuthWithGoogle(account);
    } catch (e: ApiException) {
      // The ApiException status code indicates the detailed failure reason.
      // Please refer to the GoogleSignInStatusCodes class reference for more information.
      Logger.d("signInResult:failed code=" + e.statusCode)
    }

  }


  private fun handleUser(user: FirebaseUser?) {
    startActivity(DrawerActivity.newInstance(this))
  }

  private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
    Logger.d("firebaseAuthWithGoogle:" + acct.id!!)

    val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
    mAuth.signInWithCredential(credential)
        .addOnCompleteListener(this) { task ->
          if (task.isSuccessful) {
            // Sign in success, update UI with the signed-in user's information
            Logger.d("signInWithCredential:success")
            val user = mAuth.currentUser
            handleUser(user)
          } else {
            // If sign in fails, display a message to the user.
            Logger.d("signInWithCredential:failure", task.exception)
//            updateUI(null)
          }

        }
  }


  private fun handleFacebookAccessToken(token: AccessToken) {
    Logger.d("handleFacebookAccessToken:" + token);

    val credential = FacebookAuthProvider.getCredential(token.getToken());
    mAuth.signInWithCredential(credential)
        .addOnCompleteListener(this, object : OnCompleteListener<AuthResult> {
          override fun onComplete(task: Task<AuthResult>) {
            if (task.isSuccessful) {
              // Sign in success, update UI with the signed-in user's information
              Logger.d("signInWithFacebookCredential:success");
              val user = mAuth.currentUser
              handleUser(user);
            } else {
              // If sign in fails, display a message to the user.
              Logger.d("signInWithFacebookCredential:failure", task.getException());
            }
          }
        });
  }


  private fun googleSignIn() {
    val signInIntent = mGoogleSignInClient.signInIntent
    startActivityForResult(signInIntent, Constants.GOOGLE_SIGN_IN)
  }


  companion object {
    fun newInstance(context: Context): Intent {
      return Intent(context, AuthenticationActivity::class.java)
    }
  }
}
