package com.example.mobilprogramlamafinalsinavi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MesajOlustur extends Fragment {

    EditText mesajAd, mesajGovdesi;
    RecyclerView listeRec;
    DatabaseReference database;
    MesajAdaptor mesajAdaptor;
    ArrayList<MesajModel> list;
    Button btn;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mesaj_olustur, container, false);
        mesajAd = (EditText) root.findViewById(R.id.MesajAdi);
        mesajGovdesi = (EditText) root.findViewById(R.id.Mesaj);
        context = getContext();
        btn = root.findViewById(R.id.button);
        listeRec = root.findViewById(R.id.recyclerView);
        database = FirebaseDatabase.getInstance().getReference("Mesajlar");
        listeRec.setHasFixedSize(true);
        listeRec.setLayoutManager(new LinearLayoutManager(context));
        list = new ArrayList<>();
        mesajAdaptor = new MesajAdaptor(context,list);
        listeRec.setAdapter(mesajAdaptor);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                MesajModel message = new MesajModel(mesajAd.getText().toString(), mesajGovdesi.getText().toString());
                database.push().setValue(message);
            }
        });

        database.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    MesajModel message =new MesajModel(dataSnapshot.child("mesajAdi").getValue(String.class),dataSnapshot.child("mesajIcerik").getValue(String.class));
                    list.add(message);
                }
                mesajAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return root;
    }
}