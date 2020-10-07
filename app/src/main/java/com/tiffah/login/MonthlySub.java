package com.tiffah.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MonthlySub extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_sub);
        Button request = findViewById(R.id.req_button);
        final EditText location = findViewById(R.id.req_location);
        final EditText phone =findViewById(R.id.req_phone);

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String area=location.getText().toString();
                String phony = phone.getText().toString();

                Map<String, Object> request = new HashMap<>();
                request.put("location", area);
                request.put("phone", phony);
// Add a new document with a generated ID
                db.collection("monthly_requests")
                        .add(request)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("TAG", "Error adding document", e);
                            }
                        });
                Toast.makeText(MonthlySub.this,"your request is being processed", Toast.LENGTH_SHORT);

            }
        });
        Toast.makeText(MonthlySub.this,"your request is being processed", Toast.LENGTH_SHORT);

        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
        finish();
    }
}