package application.sample.coffeehouse;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import java.util.Arrays;

public class LoginScreenActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#D2B48C"));
        }

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Bundle params = new Bundle();
                        params.putString("fields", "id,first_name,last_name,name,email,gender,picture.type(large)");
                        new GraphRequest(AccessToken.getCurrentAccessToken(), "me", params, HttpMethod.GET,
                                new GraphRequest.Callback() {
                                    @Override
                                    public void onCompleted(GraphResponse response) {
                                        if (response != null){
                                            JSONObject data = response.getJSONObject();
                                            try {
                                                if (data.has("first_name")) {
                                                    String strFacebookFirstName = data.getString("first_name");
                                                    Log.i("first name from fb", strFacebookFirstName);
                                                }
                                                if (data.has("last_name")){
                                                    String strFacebookLastName = data.getString("last_name");
                                                    Log.i("last name from fb", strFacebookLastName);
                                                }

                                                if (data.has("picture")){
                                                    String strPictureUrl = data.getJSONObject("picture").getJSONObject("data").getString("url");
                                                }

                                                if (data.has("email")){
                                                    String strFacebookEmail = data.getString("email");
                                                }

                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }).executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(LoginScreenActivity.this, "An error has been encountered while signing in.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(LoginScreenActivity.this, "Could not sign in.", Toast.LENGTH_SHORT).show();
                    }

                });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // FOR FACEBOOK AUTHENTICATION
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // FOR GOOGLE AUTHENTICATION
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 9001) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    public void fncFacebookLogin(View view){
        LoginManager.getInstance().logInWithReadPermissions(LoginScreenActivity.this, Arrays.asList("public_profile", "email"));
    }

    public void fncGoogleLogin(View view){
        signInWithGoogle();
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("Result", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.i("Result", "display name: " + acct.getDisplayName());

            String strGoogleUserName = acct.getDisplayName();
            String strGoogleFirstName = acct.getGivenName();
            String strGoogleLastName = acct.getFamilyName();

            String imgGoogleUserUrl = null;
            if (acct.getPhotoUrl().toString() != null || !acct.getPhotoUrl().toString().equals("")) {
                imgGoogleUserUrl = acct.getPhotoUrl().toString();
            } else if (imgGoogleUserUrl == null) {
                imgGoogleUserUrl = "";
            }

            String GoogleEmail = acct.getEmail();

//            Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
//            startActivity(intent);


        } else {
            Toast.makeText(this, "Could not Sign Up", Toast.LENGTH_SHORT).show();
        }
    }

    private void signInWithGoogle() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleApiClient = new GoogleApiClient.Builder(this).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();

        final Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, 9001);
    }

}
