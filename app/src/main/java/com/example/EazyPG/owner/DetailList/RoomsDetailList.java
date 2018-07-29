package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.ainesh.eazypg_owner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RoomsDetailList extends ArrayAdapter<String> {

    private Activity context;
    private List<String> roomList;
    private TextView first, second;
    private List<String> roomTypeList;

    public RoomsDetailList(Activity context, List<String> roomList) {
        super(context, R.layout.room_row, roomList);

        this.context = context;
        this.roomList = roomList;

    }

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms");


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        roomTypeList = new ArrayList<>();

        final LayoutInflater inflater = context.getLayoutInflater();
        View listViewItemRoom = inflater.inflate(R.layout.room_row, null, true);
        first = listViewItemRoom.findViewById(R.id.firstTextView);
        second = listViewItemRoom.findViewById(R.id.secondTextView);

        databaseReference = FirebaseDatabase.getInstance().getReference("PG/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Rooms/" + roomList.get(position) + "/Room Type/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                roomTypeList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    String roomType = snapshot.getValue(String.class);
                    roomTypeList.add(roomType);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        String roomType = roomTypeList.get(position);

        second.setText(roomType);

        Log.i("C",position+ ":"+roomType);
        first.setText(roomList.get(position));



        return listViewItemRoom;
    }
}
