package com.example.androidapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class AdapterC extends RecyclerView.Adapter<AdapterC.Holder> {

    private Context context;
    private ArrayList<ModelC> arrayList;
    private DatabaseHelper databaseHelper;
    private boolean isButtonsNeeded = false;

    public AdapterC(Context context, ArrayList<ModelC> arrayList, boolean isButtonsNeeded) {
        this.context = context;
        this.arrayList = arrayList;

        databaseHelper = new DatabaseHelper(context);
        this.isButtonsNeeded = isButtonsNeeded;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_c, parent, false );
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {

        ModelC model = arrayList.get(position);

        final String id = model.getId();
        final String image = model.getImage();
        final String brand = model.getBrand();
        final String Model = model.getModel();
        final String transmission = model.getTransmission();
        final String ModYear = model.getModYear();
        final String mileage = model.getMileage();
        final String fuel = model.getFuel();
        final String contact = model.getContact();
        final String add_car = model.getAdd_car();
        final String update_car = model.getUpdate_car();

        if(image != null)
            holder.profileIv.setImageURI(Uri.parse(image));
        if(brand != null)
            holder.brand.setText(brand);
        if(Model != null)
         holder.Model.setText(Model);
        if(transmission != null)
            holder.transmission.setText(transmission);
        if(ModYear != null)
            holder.ModYear.setText(ModYear);
        if(mileage != null)
            holder.mileage.setText(mileage);
        if(fuel != null)
            holder.fuel.setText(fuel);
        if(contact != null)
        holder.contact.setText(contact);


        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDialog(
                        ""+position,
                        ""+id,
                        ""+brand,
                        ""+Model,
                        ""+transmission,
                        ""+ModYear,
                        ""+image,
                        ""+mileage,
                        ""+fuel,
                        ""+contact,
                        ""+add_car,
                        ""+update_car

                );
            }
        });


        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                deleteDialog(String.valueOf(id));

            }
        });


        if(isButtonsNeeded)
        {
            holder.deleteBtn.setVisibility(View.VISIBLE);
            holder.editButton.setVisibility(View.VISIBLE);

        }
        else
        {
            holder.deleteBtn.setVisibility(View.GONE);
            holder.editButton.setVisibility(View.GONE);
        }



    }

    private void deleteDialog(final String id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure you want to delete this?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_delete);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHelper.deleteACar(id);
                ((ListCarView)context).onResume();
                Toast.makeText(context,"Deleted Successfully!",Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    private void editDialog(String position, final String id, final String brand, final String Model, final String transmission, final String ModYear, final String image, final String mileage, final String fuel, final String contact, final String add_car, final String update_car) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit");
        builder.setMessage("Do you want to edit this?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_edit);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, UpdateACar.class);
                intent.putExtra("ID", id);
                intent.putExtra("BRAND", brand);
                intent.putExtra("MODEL", Model);
                intent.putExtra("TRANSMISSION", transmission);
                intent.putExtra("MODYEAR", ModYear);
                intent.putExtra("IMAGE", image);
                intent.putExtra("MILEAGE", mileage);
                intent.putExtra("FUEL", fuel);
                intent.putExtra("CONTACT", contact);
                intent.putExtra("ADD_CAR", add_car);
                intent.putExtra("UPDATE_CAR", update_car);
                intent.putExtra("editMode", true);


                context.startActivity(intent);

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        ImageView profileIv;
        TextView brand, Model, transmission, ModYear,mileage,fuel,contact;
        ImageButton editButton,deleteBtn;

        public Holder(@NonNull View itemView) {
            super(itemView);

            profileIv = itemView.findViewById(R.id.profileIv_c);
            brand = itemView.findViewById(R.id.brand);
            Model = itemView.findViewById(R.id.model);
            transmission = itemView.findViewById(R.id.transmission);
            ModYear = itemView.findViewById(R.id.Modyear);
            mileage = itemView.findViewById(R.id.mileage);
            fuel = itemView.findViewById(R.id.fuel);
            contact = itemView.findViewById(R.id.contact);
            editButton = itemView.findViewById(R.id.editBtn);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);

        }
    }


}
