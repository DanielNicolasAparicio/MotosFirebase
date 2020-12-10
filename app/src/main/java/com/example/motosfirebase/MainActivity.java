package com.example.motosfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    EditText etProductName, etBrand, etMotor, etColor, etModelo, etVelocidad, etPrice;
    FloatingActionButton fabGuardar, fabListar;

    ProgressDialog progressDialog;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String updateId, updateProductName, updateBrand, updateMotor, updateColor, updateModelo, updateVelocidad, updatePrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etProductName = findViewById(R.id.etProductName);
        etBrand = findViewById(R.id.etBrand);
        etMotor = findViewById(R.id.etMotor);
        etColor = findViewById(R.id.etColor);
        etModelo = findViewById(R.id.etModelo);
        etVelocidad = findViewById(R.id.etVelocidad);
        etPrice = findViewById(R.id.etPrice);

        fabGuardar = findViewById(R.id.fabGuardar);
        fabListar = findViewById(R.id.fabListar);

        progressDialog = new ProgressDialog(this);

        db = FirebaseFirestore.getInstance();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Agregar registro");


        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            actionBar.setTitle("Actualizar Datos");

            updateId = bundle.getString("updateId");
            updateProductName = bundle.getString("updateProductName");
            updateBrand = bundle.getString("updateBrand");
            updateMotor = bundle.getString("updateMotor");
            updateColor = bundle.getString("updateColor");
            updateModelo = bundle.getString("updateModelo");
            updateVelocidad = bundle.getString("updateVelocidad");
            updatePrice = bundle.getString("updatePrice");

            etProductName.setText(updateProductName);
            etBrand.setText(updateBrand);
            etMotor.setText(updateMotor);
            etColor.setText(updateColor);
            etModelo.setText(updateModelo);
            etVelocidad.setText(updateVelocidad);
            etPrice.setText(updatePrice);

        } else {
            actionBar.setTitle("Agregar");
        }


        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle1 = getIntent().getExtras();
                if (bundle1 != null) {
                    String id = updateId;
                    String ProductName = etProductName.getText().toString().trim();
                    String brand = etBrand.getText().toString().trim();
                    String motor = etMotor.getText().toString().trim();
                    String color = etColor.getText().toString().trim();
                    String modelo = etModelo.getText().toString().trim();
                    String velocidad = etVelocidad.getText().toString().trim();
                    String price = etPrice.getText().toString().trim();

                    actualizarDatos(id, ProductName, brand, motor, color, modelo, velocidad, price);

                } else {
                    String ProductName = etProductName.getText().toString().trim();
                    String brand = etBrand.getText().toString().trim();
                    String motor = etMotor.getText().toString().trim();
                    String color = etColor.getText().toString().trim();
                    String modelo = etModelo.getText().toString().trim();
                    String velocidad = etVelocidad.getText().toString().trim();
                    String price = etPrice.getText().toString().trim();

                    cargarDatos(ProductName, brand, motor, color, modelo, velocidad, price);
                }
            }
        });


        fabListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListActivityPerson.class));
                finish();
            }
        });

    }


    private void cargarDatos(String ProductName, String brand, String motor, String color, String modelo, String velocidad, String price) {
        progressDialog.setTitle("Agregar datos");
        progressDialog.show();
        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("id", id);
        doc.put("ProductName", ProductName);
        doc.put("brand", brand);
        doc.put("motor", motor);
        doc.put("color", color);
        doc.put("modelo", modelo);
        doc.put("velocidad", velocidad);
        doc.put("price", price);


        db.collection("Documents").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Datos almacenados con éxito...", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Ha ocurrido un error..." + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void actualizarDatos(String id, String ProductName, String brand, String motor, String color, String modelo, String velocidad, String price) {
        progressDialog.setTitle("Actualizando datos a Firebase");
        progressDialog.show();

        /*
        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("id", id);
        doc.put("nombre", nombre);
        doc.put("apaterno", apaterno);
        doc.put("amaterno", amaterno);
        doc.put("sexo", sexo);
        doc.put("direccion", direccion);
        doc.put("facebook", facebook);
        doc.put("instagram", instagram);

         */


        db.collection("Documents")
                .document(id).update(
                "ProductName", ProductName,
                "brand", brand,
                "motor", motor,
                "color", color,
                "modelo", modelo,
                "velocidad", velocidad,
                "price", price
        )
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Actualización exitosa...", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Ha ocurrido un error..." + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}