package com.example.asad21f22072;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText id, passwd;
    Button log;
    RDatabase21F22072 uadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        log = findViewById(R.id.button);
        id = findViewById(R.id.idtext);
        passwd = findViewById(R.id.passwordtxt);

        uadd = new RDatabase21F22072(this);
        setupLogin();
    }
    public void setupLogin() {
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id1 = id.getText().toString().trim(); // Fetch ID input
                String p1 = passwd.getText().toString().trim(); // Fetch password input
                // Checking if the owner is logging in
                if (id1.equals("owner") && p1.equals("owner")) {
                    Intent intent = new Intent(login.this, ownerpage.class);
                    startActivity(intent);
                } else {
                    // Attempt to login as tenant
                    try {
                        String rp = uadd.getPassword(id1); // Fetch password from database
                        if (p1.equals(rp)) {
                            Toast.makeText(login.this, "WELCOME TO RENT VILLA", Toast.LENGTH_SHORT).show();
                            id.setText(""); // Clear input fields
                            passwd.setText("");
                            Intent intent = new Intent(login.this, tenantpage.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(login.this, "Wrong credentials given", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(login.this, "Failed to Login: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }}
        });
    }}