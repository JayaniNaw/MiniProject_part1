package com.example.plantingshedule_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoChanges extends AppCompatActivity {

    EditText etID,etName,etDesc;
    Button add,show,delete,update;

    //gloabal variables to hold data
    String plantid,plantName,desc;
    DatabaseReference reference;

    private Boolean validateId(){
        String id = etID.getText().toString();

        if(id.isEmpty()){
            etID.setError("Field Cannot be empty");
            return false;
        }
        else{
            etID.setError(null);
            return true;
        }
    }

    private Boolean validateName(){
        String name = etName.getText().toString();

        if(name.isEmpty()){
            etName.setError("Field Cannot be empty");
            return false;
        }
        else{
            etName.setError(null);
            return true;
        }
    }

    private Boolean validateDesc(){
        String desc = etDesc.getText().toString();

        if(desc.isEmpty()){
            etDesc.setError("Field Cannot be empty");
            return false;
        }
        else{
            etDesc.setError(null);
            return true;
        }
    }

    private void clearControls(){
        etID.setText("");
        etName.setText("");
        etDesc.setText("");

    }

    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_changes);

        etID = findViewById(R.id.etid);
        etName = findViewById(R.id.etname);
        etDesc = findViewById(R.id.etdesc);

        add = findViewById(R.id.btn_add);
        delete = findViewById(R.id.btn_del);
        update = findViewById(R.id.btn_upd);

        reference = FirebaseDatabase.getInstance().getReference("PlantingShedule");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("PlantingShedule");

                if(!validateName() | !validateId() | !validateDesc()){
                    return;
                }

                String id = etID.getText().toString();
                String name = etName.getText().toString();
                String desc = etDesc.getText().toString();

                PlantingShedule plantingShedule = new PlantingShedule(id,name,desc);

                dbref.child(id).setValue(plantingShedule);

                Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_SHORT).show();
            }
        });


        showAllData();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("PlantingShedule");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(plantid)){
                            reference = FirebaseDatabase.getInstance().getReference("PlantingShedule");
                            reference.removeValue();
                            clearControls();
                            Toast.makeText(getApplicationContext(), "Data deleted Successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "No source to delete", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }

    private void showAllData() {

        Intent intent = getIntent();
         plantid = intent.getStringExtra("id");
         plantName = intent.getStringExtra("name");
         desc = intent.getStringExtra("description");

        etID.setText(plantid);
        etName.setText(plantName);
        etDesc.setText(desc);
    }

    public void update(View view){
        if(plantnameChanged()|| plantSheduleChanged()){
            Toast.makeText(getApplicationContext(), "Data has been changed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Data is same.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean plantSheduleChanged() {

        if(!desc.equals(etDesc.getText().toString())){
            reference.child(plantid).child("description").setValue(etDesc.getText().toString());
            return true;
        }
        else{
            return false;

        }
    }

    private boolean plantnameChanged() {

        if(!plantName.equals(etName.getText().toString())){
            reference.child(plantid).child("plantName").setValue(etName.getText().toString());
            return true;
        }
        else{
            return false;

        }
    }
}