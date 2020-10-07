package com.tiffah.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText user;
    EditText pass;
    EditText mailtext;
    EditText phonetext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        mailtext = findViewById(R.id.mailtext);
        phonetext = findViewById(R.id.phonetext);

        final FirebaseFirestore firestore = FirebaseFirestore.getInstance();


        Button reg = findViewById(R.id.registerin);
        reg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String username = user.getText().toString();
                final String password = pass.getText().toString();
                String mail = mailtext.getText().toString();
                final String phone = phonetext.getText().toString();

                mAuth.createUserWithEmailAndPassword(mail, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser currentUser = mAuth.getCurrentUser();
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("username", username);
                                    user.put("Email", currentUser.getEmail());
                                    user.put("Phone", phone);
                                    user.put("password", password);
                                    user.put("uid", currentUser.getUid());
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("TAG", "createUserWithEmail:success");
                                    firestore.collection("users").add(user)
                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    Log.e("DONE USER****", documentReference.getId());
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e("ERRR****", e.getMessage());
                                        }
                                    });
                                    updateUI(currentUser);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }

                            }
                        });


//
//// Add a new document with a generated ID
//                db.collection("users")
//                        .add(user)
//                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                            @Override
//                            public void onSuccess(DocumentReference documentReference) {
//                                Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Log.w("TAG", "Error adding document", e);
//                            }
//                        });
//
            }
        });

    }

    public void updateUI(FirebaseUser user) {

        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }


}