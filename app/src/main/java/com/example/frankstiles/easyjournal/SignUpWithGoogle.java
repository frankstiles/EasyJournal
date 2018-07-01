package com.example.frankstiles.easyjournal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;


public class SignUpWithGoogle extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener{


    SignInButton mSignIn;               /*GOOGLE SIGN IN BUTTON*/
    TextView mName,mEmail;
    GoogleApiClient mGoogleApiClient;
    private static final int  REQUEST_CODE = 007;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_with_google);

        /*TRAVERSING THROUGH VIEWS IN XML AS WELL AS GETTING THEIR REFERENCE*/
     //   mProfileView = (LinearLayout)findViewById(R.id.profile_layout);
        //GET SIGN OUT BUTTON REFERENCE

        mSignIn = (SignInButton) findViewById(R.id.sign_in_button);
      //  mName = (TextView) findViewById(R.id.signin_user_name);
      //  mEmail = (TextView) findViewById(R.id.signin_email);
       // mProf_pics = (ImageView) findViewById(R.id.signin_prof_pic);

        /*SETTING ON CLICK LISTENER TO SIGN IN AND SIGN OUT BUTTON*/
        mSignIn.setOnClickListener(this);
        //mSignOut.setOnClickListener(this); STILL DECIDE IF SIGN OUT BUTTON WOULD GO HERE

        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();

        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.sign_in_button:
                signIn();
                break;

        }
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result );
        }
    }


    public void signIn(){
        Intent i = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(i,REQUEST_CODE);
    }


    public void signOut(){
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>(){
                    @Override
                    public void onResult(@NonNull Status status) {
                        updateUI(false);
                    }
                }
        );
    }


    public void handleResult(GoogleSignInResult result) {
       if(result.isSuccess()){

           GoogleSignInAccount account = result.getSignInAccount();
           String name = account.getDisplayName();
           String email = account.getEmail();
           String user_id = account.getId();

           String img_url = account.getPhotoUrl().toString();

           //set values to SetViews

           Intent i = new Intent(SignUpWithGoogle.this,JournalEntries.class);
           i.putExtra("gUsername",name);
           i.putExtra("gEmail",email);
           i.putExtra("gId",user_id);
           i.putExtra("img_url",img_url);
           startActivity(i);
           
       }else{
           updateUI(false);
       }
    }


    private void updateUI(boolean isLogin){
        if(isLogin){
            mSignIn.setVisibility(View.GONE);
        }
    }

}
