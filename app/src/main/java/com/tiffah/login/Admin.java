package com.tiffah.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.tiffah.login.models.Request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Admin extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    List<Request> list = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        recyclerView = findViewById(R.id.recycler_view);

//        list.addAll(Arrays.asList("elephant","hyena","chicken",
//                "girrafe","penguin","blackbird","crown","dove","lion",
//                "quora","bird","snake","milk","hawky","turkey"));
        recyclerView.setHasFixedSize(true);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(list);
        recyclerView.setAdapter(mAdapter);
        FirebaseFirestore.getInstance()
                .collection("monthly_requests")
                .get()
                .addOnSuccessListener(this, new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(  QuerySnapshot queryDocumentSnapshots) {
//                        list.   removeAll(Arrays.asList("elephant","hyena","chicken",
//                                "girrafe","penguin","blackbird","crown","dove","lion",
//                                "quora","bird","snake","milk","hawky","turkey"));
                        for (DocumentSnapshot document :queryDocumentSnapshots.getDocuments()) {
                            Request request = new Request(document.getString("location"), document.getString("phone"));
                            list.add(request);
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });


    }
}