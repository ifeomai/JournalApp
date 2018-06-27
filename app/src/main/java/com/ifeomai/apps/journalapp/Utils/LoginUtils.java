package com.ifeomai.apps.journalapp.Utils;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.ifeomai.apps.journalapp.R;

public class LoginUtils {
    /*private static FirebaseAuth mAuth;
    private static GoogleSignInClient mGoogleSignInClient;

    public LoginUtils(Activity activity) {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                //.requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
    }


    public void signOut(View view) {
        // Firebase sign out
     //   mAuth.signOut();

        // Google sign out
     //   mGoogleSignInClient.signOut();


        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
        Snackbar.make(view, R.string.signed_out,
                Snackbar.LENGTH_SHORT)
                .show();

    }
    private void updateUI(String string){

 }*/

    public static void signOut(View view) {
        Snackbar.make(view, R.string.signed_out,
                Snackbar.LENGTH_SHORT)
                .show();

    }
}
