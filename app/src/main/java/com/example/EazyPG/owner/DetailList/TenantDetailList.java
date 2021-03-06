package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.EazyPG.owner.Activities.PaymentActivity;
import com.example.EazyPG.owner.DetailsClasses.TenantDetails;
import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TenantDetailList extends ArrayAdapter<TenantDetails> {

    private Activity context;
    private List<TenantDetails> tenantList;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public String id;
    FloatingActionButton callButton, paymentButton;
    Button deleteTenant;
    ImageView qrButton, qrImage;

    TenantDetails newData;

    public TenantDetailList(Activity context, List<TenantDetails> tenantList) {
        super(context, R.layout.tenant_row, tenantList);

        this.context = context;
        this.tenantList = tenantList;
    }

    DatabaseReference databaseReference;
    List<String> ids = new ArrayList<>();

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemTenant = inflater.inflate(R.layout.tenant_row, null, true);
        callButton = listViewItemTenant.findViewById(R.id.callButton);
        paymentButton = listViewItemTenant.findViewById(R.id.paymentButton);
        deleteTenant = listViewItemTenant.findViewById(R.id.deleteTenant);
        final TenantDetails tenantDetails = tenantList.get(position);

        databaseReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Tenants/CurrentTenants");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ids.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    TenantDetails tenantDetails1 = snapshot.getValue(TenantDetails.class);
                    id = tenantDetails1.id;
                    ids.add(id);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listViewItemTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View viewDialog = inflater.inflate(R.layout.dialog_tenant, null);

                final EditText name, phone, room, dateOfJoining, rentAmount;

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Tenant details");

                name = viewDialog.findViewById(R.id.tenantNameEditText);
                phone = viewDialog.findViewById(R.id.tenantPhoneEditText);
                room = viewDialog.findViewById(R.id.tenantRoomEditText);
                dateOfJoining = viewDialog.findViewById(R.id.tenantDateEditText);
                rentAmount = viewDialog.findViewById(R.id.tenantRentEditText);

                name.setText(tenantDetails.name);
                phone.setText(tenantDetails.phone);
                room.setText(tenantDetails.room);
                dateOfJoining.setText(tenantDetails.dateOfJoining);
                rentAmount.setText(tenantDetails.rentAmount);

                builder.setView(viewDialog);

                builder.setPositiveButton("OK", null);
                builder.show();

            }
        });

        TextView first = listViewItemTenant.findViewById(R.id.firstTextView);
        TextView second = listViewItemTenant.findViewById(R.id.secondTextView);
        final TextView third = listViewItemTenant.findViewById(R.id.thirdTextView);

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PaymentActivity.class);
                intent.putExtra(EXTRA_MESSAGE, ids.get(position));
                context.startActivity(intent);

            }
        });


        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:" + third.getText().toString()));
                        context.startActivity(callIntent);

                }
                catch (ActivityNotFoundException activityException) {
                    Toast.makeText(context, "Call failed", Toast.LENGTH_SHORT).show();
                }
                catch (SecurityException e) {
                    Toast.makeText(context, "Call failed!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        deleteTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DatabaseReference fromReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Tenants/CurrentTenants/");
                final DatabaseReference toReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Tenants/PreviousTenants/");
                final DatabaseReference toReferenceRoom = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms/" + tenantDetails.getRoom() + "/Tenant/PreviousTenants");

                final DatabaseReference roomReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms/" + tenantDetails.getRoom() + "/Tenant/CurrentTenants");
                roomReference.child(ids.get(position)).setValue(null);

                fromReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        toReference.child(ids.get(position)).setValue(dataSnapshot.child(ids.get(position)).getValue());
                        toReferenceRoom.child(ids.get(position)).setValue(dataSnapshot.child(ids.get(position)).getValue());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        fromReference.child(ids.get(position)).setValue(null);
                    }
                });
                thread.start();

            }
        });

        first.setText(tenantDetails.getName());
        second.setText(tenantDetails.getRoom());
        third.setText(tenantDetails.getPhone());

        return listViewItemTenant;

    }
}
