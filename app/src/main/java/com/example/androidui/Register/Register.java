package com.example.androidui.Register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.androidui.CreatePost.CreatePosts;
import com.example.androidui.Login.Login;
import com.example.androidui.MainActivity.MainActivity;
import com.example.androidui.R;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class Register extends AppCompatActivity {


    private EditText edtPhoneNumber, edtPassword, edtEmail, edtName;

    private Button btnSubmit, btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtPassword = findViewById(R.id.edtPassword);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSignup = findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(submitSignup);

        //catch text changed event
        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isValidName(edtName.getText().toString().trim())) {
                    edtName.setError("Invalid name");
                }else if(edtName.getText().toString().isEmpty()){
                    edtName.setError("Required field");
                } else {
//                    edtPhoneNumber.setError("2");
                }
            }
        });
        //catch text changed event
        edtPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isValidPhone(edtPhoneNumber.getText().toString().trim())) {
                    edtPhoneNumber.setError("Invalid phone number");
                }else if(edtPhoneNumber.getText().toString().isEmpty()){
                    edtPhoneNumber.setError("Required field");
                }
                else {
//                    edtPhoneNumber.setError("2");
                }
            }
        });

        //catch text changed event
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isValidEmailId(edtEmail.getText().toString().trim())) {
                    // email invalid
                    edtEmail.setError("Invalid email");
                }else if(edtEmail.getText().toString().isEmpty()){
                    edtEmail.setError("Required field");
                }
            }
        });

        //catch text changed event
        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isValidPassword(edtPassword.getText().toString().trim())) {
                    // email invalid
                    edtPassword.setError("Password too short");
                }else if(edtPassword.getText().toString().isEmpty()){
                    edtPassword.setError("Required field");
                }
            }
        });

    }

    // pattem name
    private boolean isValidName(String name) {

        return Pattern.compile("^[a-zA-Z\\s]{2,50}")
                .matcher(name).matches();
    }

    // pattem email
    private boolean isValidEmailId(String email) {
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$")
                .matcher(email).matches();

    }

    // pattem phone number
    private boolean isValidPhone(String phone) {

        return Pattern.compile("^\\+?\\(?[0-9]{1,3}\\)? ?-?[0-9]{1,3} ?-?[0-9]{3,5} ?-?[0-9]{4}( ?-?[0-9]{3})?")
                .matcher(phone).matches();
    }

    // pattem password
    private boolean isValidPassword(String password) {
        return password.length() >=6 ;
    }


    // back arrow event
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private View.OnClickListener submitSignup= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(edtPhoneNumber.length() == 0){
                edtPhoneNumber.setError("This field is required");
                edtPhoneNumber.requestFocus();
            }

            if(edtEmail.length() == 0){
                edtEmail.setError("This field is required");
                edtEmail.requestFocus();
            }

            if(edtPassword.length() == 0){
                edtPassword.setError("This field is required");
                edtPassword.requestFocus();
            }

            if(edtName.length() == 0){
                edtName.setError("This field is required");
                edtName.requestFocus();
            }


            String phone = edtPhoneNumber.getText().toString();
            String password = edtPassword.getText().toString();
            String name = edtName.getText().toString();
            String email = edtEmail.getText().toString();


            Signup(phone, password, name, email);
        }
    };

    //Service Signup - Call API
    private void Signup(String phone, String password, String name, String email) {
        //API
        String ServerName = "https://whatfoods.herokuapp.com/signup";
        //Call API
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, ServerName,
                //Signup Successful
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response

                        startActivity(new Intent (Register.this, Login.class));
                        Toast.makeText(Register.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                        System.out.println("Thanh cong");

                    }
                },
                //Signup Fail
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // check internet connection
                        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

                        NetworkInfo activeNetwork = null;
                        if (cm != null) {
                            activeNetwork = cm.getActiveNetworkInfo();
                        }
                        if(activeNetwork != null && activeNetwork.isConnectedOrConnecting()){
                            Toast.makeText(Register.this, "Server is not connected to internet.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Register.this, "Your device is not connected to internet.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        //ShowToast
                        //Show Toast
                        Toast.makeText(Register.this, "Signup Fail", Toast.LENGTH_SHORT).show();
                    }
                }
        )
                //Truyền dữ liệu theo params
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("phone", phone);
                params.put("password", password);
                params.put("name", name);
                params.put("email", email);
                return params;
            }
        };
        //Thực thi Call API
        queue.add(postRequest);
    }

}