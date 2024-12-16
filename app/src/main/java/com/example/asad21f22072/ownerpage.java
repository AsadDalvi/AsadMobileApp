package com.example.asad21f22072;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ownerpage extends AppCompatActivity {
    Button addt, showv, viewtnt, cls, logout;
    RDatabase21F22072 dbtadd;
    VDatabase21F22072 vdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ownerpage);
        addt = findViewById(R.id.add);
        viewtnt = findViewById(R.id.tenantview);
        showv = findViewById(R.id.sv);
        cls = findViewById(R.id.exit);
        logout = findViewById(R.id.logout);
        dbtadd = new RDatabase21F22072(this);
        vdb = new VDatabase21F22072(this);

        getAllAccounts();
        viewAllVilla();

        addt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ownerpage.this, addnewtenant.class);
                startActivity(intent);
            }
        });
        cls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();  // Close the current activity
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ownerpage.this, login.class);
                startActivity(intent);
                finish();
            }
        });
    }
    // Fetch and show all tenant details
    public void getAllAccounts() {
        viewtnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c2 = dbtadd.getAllAccounts();
                if (c2 == null || c2.getCount() == 0) {
                    viewInformation("Error", "Tenant accounts not found");
                    return;
                }
                StringBuffer sb = new StringBuffer();
                while (c2.moveToNext()) {
                    sb.append("=======================================" + "\n");
                    sb.append("Tenant ID :- " + c2.getString(0) + "\n");
                    sb.append("Tenant Name :- " + c2.getString(1) + "\n");
                    sb.append("Tenant Type :- " + c2.getString(2) + "\n");
                    sb.append("Tenant Password :- " + c2.getString(3) + "\n");
                    sb.append("========================================" + "\n");
                }
                viewInformation("Tenant information", sb.toString());
                c2.close();  // Close the cursor after usage
            }
        });
    }
    // Fetch and show all villa details
    public void viewAllVilla() {
        showv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c3 = vdb.viewAllVilla();
                if (c3 == null || c3.getCount() == 0) {
                    viewInformation("Error", "Villas not found");
                    return;
                }
                StringBuffer sb = new StringBuffer();
                while (c3.moveToNext()) {
                    sb.append("=======================================" + "\n");
                    sb.append("Villa ID : " + c3.getString(0) + "\n");
                    sb.append("Total Months : " + c3.getString(1) + "\n");
                    sb.append("Total Rent Payment : " + c3.getString(2) + "\n");
                    sb.append("Payment Date : " + c3.getString(3) + "\n");
                    sb.append("Tenant Ph.no : " + c3.getString(4) + "\n");
                    sb.append("=======================================" + "\n");
                }
                viewInformation("Villa information", sb.toString());
                c3.close();  // Close the cursor after usage
            }
        });
    }
    public void viewInformation(String title, String message) {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setCancelable(true);
        ad.setTitle(title);
        ad.setMessage(message);
        ad.show();
    }}
