package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class Update extends AppCompatActivity {
    //get the FirebaseFirestore instance
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth registerUser = FirebaseAuth.getInstance();
    ValidateData validator = new ValidateData();

    //create variable to get inputs which are from andriod app page
    private TextView errorMessage;
    private EditText usr;
    private EditText email;
    private EditText pass;
    private EditText  age;
    private EditText weight;
    private EditText height;
    private EditText role;
    private EditText phone;
    private EditText address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        //make alert user to input right things
        Toast.makeText(Update.this,"Please fill all of blanks and make sure type correct email address and user name",Toast.LENGTH_LONG).show();
        //initialize database
        db = FirebaseFirestore.getInstance();
        registerUser = FirebaseAuth.getInstance();
        //get inputs from app page
        errorMessage = findViewById(R.id.errorInfo);
        phone = findViewById(R.id.telnumber);
        address = findViewById(R.id.address);
        usr = findViewById(R.id.username);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        age = findViewById(R.id.age);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        role = findViewById(R.id.role);
        Button update = findViewById(R.id.Update);
        TextView return_to_main = findViewById(R.id.log);
        //call update() method for update database information
        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                update();
            }
        });
        //back to the main page
        final Intent intent=new Intent(this,LoginActivity.class);
        return_to_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
            }
        });
    }
    public void update() {
        FirebaseApp.initializeApp(this);
        //convert all inputs to string for next step
        final String username = usr.getText().toString();
        final String roleText = role.getText().toString();
        final String passText = pass.getText().toString();
        final String ageNum = age.getText().toString();
        final String weightNum = weight.getText().toString();
        final String heightNum = height.getText().toString();
        final String phoneNumber = phone.getText().toString();
        final String addressText = address.getText().toString();
        final String email_address = email.getText().toString();
        final int stepN = 0;
        final ArrayList<String> friend_request=new ArrayList<String>() ;
        final ArrayList<String> friend_list=new ArrayList<String>() ;


        //check whether inputs are meet requirements
        String error = validator.validateRegisterFields(passText,roleText,ageNum,weightNum,heightNum,username,email_address);


        if (!error.isEmpty()) {
            errorMessage.setVisibility(View.VISIBLE);
            errorMessage.setText(error);
            errorMessage.setTextColor(Color.RED);
        } else {
            //if it meet all the requirement
            DocumentReference ref = db.collection("Users").document(username + "");// to locate the special position of database for update
            //addOnCompleteListener--- check if this user is registered in the database and check if input email address and username are same as old
            ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot result = task.getResult();// get the all value for this user
                        DocumentReference ref = db.collection("Users").document(username + "");
                        if (result != null && result.getString("emailAddress")!=null) {
                            String str9 = result.getString("emailAddress");
                            if (email_address.equals(str9)) {
                                //create a new user object for this user and update information use latest input
                                User userinfo;
                                userinfo = new User(username+"",phoneNumber+"",addressText+"," +
                                        "", email_address+"",passText+"", roleText+"",friend_request, friend_list, Integer.parseInt(heightNum),
                                        Integer.parseInt(weightNum),Integer.parseInt(ageNum),stepN);
                                ref.set(userinfo);
                                //update the auth information 
                                registerUser.getCurrentUser().updatePassword(passText);
                                Toast.makeText(Update.this, "Update Successful", Toast.LENGTH_LONG).show();//tell user update successful
                            } else {
                                Toast.makeText(Update.this, "Enter correct email address", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(Update.this, "Please register first", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }
    }
}
