package com.example.motosfirebase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<ViewHolder> {

    ListActivityPerson listActivityPerson;
    List<PersonModel> mPersonModelList;

    public CustomAdapter(ListActivityPerson listActivity, List<PersonModel> personModelList) {
        this.listActivityPerson = listActivity;
        this.mPersonModelList = personModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.model, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String ProductName = mPersonModelList.get(position).getProductName();
                String brand = mPersonModelList.get(position).getBrand();
                String motor = mPersonModelList.get(position).getMotor();
                String modelo = mPersonModelList.get(position).getModelo();
                Toast.makeText(listActivityPerson, ProductName + " " + brand + " " + motor + " " + modelo, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(listActivityPerson);
                String[] options = {"Actualizar datos", "Eliminar"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            String id = mPersonModelList.get(position).getId();
                            String ProductName = mPersonModelList.get(position).getProductName();
                            String brand = mPersonModelList.get(position).getBrand();
                            String motor = mPersonModelList.get(position).getMotor();
                            String color = mPersonModelList.get(position).getColor();
                            String modelo = mPersonModelList.get(position).getModelo();
                            String velocidad = mPersonModelList.get(position).getVelocidad();
                            String price = mPersonModelList.get(position).getPrice();

                            Intent actualizarDatos = new Intent(listActivityPerson,MainActivity.class);
                            actualizarDatos.putExtra("updateId", id);
                            actualizarDatos.putExtra("updateProductName", ProductName);
                            actualizarDatos.putExtra("updateBrand", brand);
                            actualizarDatos.putExtra("updateMotor", motor);
                            actualizarDatos.putExtra("updateColor", color);
                            actualizarDatos.putExtra("updateModelo", modelo);
                            actualizarDatos.putExtra("updateVelocidad", velocidad);
                            actualizarDatos.putExtra("updatePrice", price);

                            listActivityPerson.startActivity(actualizarDatos);
                            // String id, String nombre, String apaterno, String amaterno, String sexo, String direccion, String facebook, String instagram
                        }

                        if (which == 1) {
                            listActivityPerson.eliminarRegistro(position);
                        }
                    }
                }).create().show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvNombre.setText(
                mPersonModelList.get(i).getProductName()
                        + " " + mPersonModelList.get(i).getBrand()
                        + " " + mPersonModelList.get(i).getMotor()
                        + " " + mPersonModelList.get(i).getModelo()
        );
    }

    @Override
    public int getItemCount() {
        return mPersonModelList.size();
    }
}
