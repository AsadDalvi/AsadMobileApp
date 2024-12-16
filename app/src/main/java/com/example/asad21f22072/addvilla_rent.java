package com.example.asad21f22072;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class addvilla_rent extends AppCompatActivity {
    EditText vv1r, vv1m, vv2r, vv2m, vv3r, vv3m, totalr;
    EditText villaid, prdate, tmmmnth, trtotal, tphno;
    CheckBox ccb1, ccb2, ccb3;
    Button tcalcr, addv, clr;
    EditText tmth, vv1, vv2, vv3;

    private villa1calculation v1calc;
    private villa2calculation v2calc;
    private villa3calculation v3calc;
    private totalcalculationcost ttcc;

    String n1,n2,n3;

    VDatabase21F22072 vdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addvilla_rent);

        ccb1=findViewById(R.id.cb1);
        vv1r=findViewById(R.id.v1r);
        vv1m=findViewById(R.id.v1m);

        ccb2=findViewById(R.id.cb2);
        vv2r=findViewById(R.id.v2r);
        vv2m=findViewById(R.id.v2m);

        ccb3=findViewById(R.id.cb3);
        vv3r=findViewById(R.id.v3r);
        vv3m=findViewById(R.id.v3m);

        tmth=findViewById(R.id.tmonth);
        vv1=findViewById(R.id.v1m);
        vv2=findViewById(R.id.v2m);
        vv3=findViewById(R.id.v3m);

        ccb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vv1r.setText("20");
                n1="Villa 1";
            }
        });

        ccb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vv2r.setText("20");
                n2="Villa 2";
            }
        });

        ccb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vv3r.setText("20");
                n3="Villa 3";
            }
        });

        v1calc=new villa1calculation();
        v2calc=new villa2calculation();
        v3calc=new villa3calculation();
        ttcc=new totalcalculationcost();

        //total calculation of rent with months
        totalr=findViewById(R.id.totalclc);
        tcalcr=findViewById(R.id.rentcalc);
        tcalcr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String va11=vv1r.getText().toString();
                String va22=vv2r.getText().toString();
                String va33=vv3r.getText().toString();

                String vm11=vv1m.getText().toString();
                String vm22=vv2m.getText().toString();
                String vm33=vv3m.getText().toString();

                //converting to double
                Double vrr1=Double.parseDouble(va11);
                Double vrr2=Double.parseDouble(va22);
                Double vrr3=Double.parseDouble(va33);

                Double vmm1=Double.parseDouble(vm11);
                Double vmm2=Double.parseDouble(vm22);
                Double vmm3=Double.parseDouble(vm33);

                Double vcalc1=v1calc.villa1calculationn(vrr1, vmm1);
                Double vcalc2=v2calc.villa2calculationn(vrr2, vmm2);
                Double vcalc3=v3calc.villa3calculationn(vrr3, vmm3);

                //calculation of total rent with months
                Double trr= ttcc.totalrentcalculation(vcalc1, vcalc2, vcalc3);

                totalr.setText(""+trr);
                totalr.setVisibility(View.VISIBLE);
                tmth.setText(n1+"-"+vm11+","+n2+"-"+vm22+","+n3+"-"+vm33);
            }
        });
        //codes for inputting data into sales database
        addv=findViewById(R.id.add);
        villaid=findViewById(R.id.villaID);
        trtotal=findViewById(R.id.totalclc);
        tmmmnth=findViewById(R.id.tmonth);
        prdate=findViewById(R.id.pdate);
        tphno=findViewById(R.id.phno);

        clr=findViewById(R.id.clear);
        clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tmth.setText("");
                tmmmnth.setText("");
                villaid.setText("");
                prdate.setText("");
                tphno.setText("");
                vv1r.setText("");
                vv2m.setText("");
                vv2r.setText("");
                vv2m.setText("");
                vv3r.setText("");
                vv3m.setText("");
            }
        });
        vdb=new VDatabase21F22072(this);
        addvillas();
    }
    public void addvillas(){
        addv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Tid=villaid.getText().toString();
                String TR=trtotal.getText().toString();
                String totalm=tmmmnth.getText().toString();
                String lprd=prdate.getText().toString();
                String tph_no=tphno.getText().toString();
                boolean insert=vdb.addvilla(Tid, totalm, TR, lprd, tph_no);
                if(insert==true)
                {
                    Toast.makeText(addvilla_rent.this,"Villa rent added succesfully", Toast.LENGTH_SHORT).show();
                    Intent login=new Intent(addvilla_rent.this,tenantpage.class);
                    startActivity(login);
                }
                else
                {
                    Toast.makeText(addvilla_rent.this,"Villa rent not added",Toast.LENGTH_SHORT).show();
                }}
        });
    }}