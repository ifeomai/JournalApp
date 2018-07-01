package com.ifeomai.apps.journalapp.Utils;

import com.google.firebase.auth.FirebaseAuth;

public class LoginUtils {

      public static String getUid() {

        return FirebaseAuth.getInstance().getCurrentUser().getUid();

    }
    /*private static GoogleSignInClient mGoogleSignInClient;
    private static FirebaseAuth mAuth;


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

    public static void signOut() {

        FirebaseAuth.getInstance().signOut();
       /* GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                //.requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);
        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });*/

    }
}
