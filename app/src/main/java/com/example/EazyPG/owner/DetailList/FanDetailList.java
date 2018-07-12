package com.example.EazyPG.owner.DetailList;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.EazyPG.owner.ApplianceDetail.ApplianceDetailFan;
import com.example.ainesh.eazypg_owner.R;

import java.util.List;

public class FanDetailList extends ArrayAdapter<ApplianceDetailFan> {

    private Activity context;
    private List<ApplianceDetailFan> fanList;

    public FanDetailList(Activity context, List<ApplianceDetailFan> fanList) {
        super(context, R.layout.appliance_row, fanList);

        this.context = context;
        this.fanList = fanList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        final View viewDialog = inflater.inflate(R.layout.dialog_appliance, null);
        View listViewItemFan = inflater.inflate(R.layout.appliance_row, null, true);

        TextView first = listViewItemFan.findViewById(R.id.firstTextView);
        TextView second = listViewItemFan.findViewById(R.id.secondTextView);
        TextView third = listViewItemFan.findViewById(R.id.thirdTextView);

        final ApplianceDetailFan applianceDetailFan = fanList.get(position);

        listViewItemFan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText FanRoomNo, FanCompanyName, FanModel, FanDays, FanBlades;

                FanRoomNo = view.findViewById(R.id.fanRoomNumberEditText);
                FanCompanyName = view.findViewById(R.id.fanCompanyNameEditText);
                FanModel = view.findViewById(R.id.fanModelEditText);
                FanDays = view.findViewById(R.id.fanDaysEditText);
                FanBlades = view.findViewById(R.id.fanBladesEditText);

                RelativeLayout ACLayout, fanLayout, liftLayout, geyserLayout, washingMachineLayout, ROLayout, dishwasherLayout, microwaveLayout,
                        fridgeLayout, TVLayout, CCTVLayout, ironLayout, inductionLayout, routerLayout, heaterLayout, D2HLayout, otherLayout;

                ACLayout = viewDialog.findViewById(R.id.ACLayout);
                fanLayout = viewDialog.findViewById(R.id.fanLayout);
                liftLayout = viewDialog.findViewById(R.id.liftLayout);
                geyserLayout = viewDialog.findViewById(R.id.geyserLayout);
                washingMachineLayout = viewDialog.findViewById(R.id.washingMachineLayout);
                ROLayout = viewDialog.findViewById(R.id.ROLayout);
                dishwasherLayout = viewDialog.findViewById(R.id.dishwasherLayout);
                microwaveLayout = viewDialog.findViewById(R.id.microwaveLayout);
                fridgeLayout = viewDialog.findViewById(R.id.fridgeLayout);
                TVLayout = viewDialog.findViewById(R.id.TVLayout);
                CCTVLayout = viewDialog.findViewById(R.id.CCTVLayout);
                ironLayout = viewDialog.findViewById(R.id.ironLayout);
                inductionLayout = viewDialog.findViewById(R.id.inductionLayout);
                routerLayout = viewDialog.findViewById(R.id.routerLayout);
                heaterLayout = viewDialog.findViewById(R.id.heaterLayout);
                D2HLayout = viewDialog.findViewById(R.id.D2HLayout);
                otherLayout = viewDialog.findViewById(R.id.otherLayout);

                ACLayout.setVisibility(View.GONE);
                fanLayout.setVisibility(View.VISIBLE);
                liftLayout.setVisibility(View.GONE);
                geyserLayout.setVisibility(View.GONE);
                washingMachineLayout.setVisibility(View.GONE);
                ROLayout.setVisibility(View.GONE);
                dishwasherLayout.setVisibility(View.GONE);
                microwaveLayout.setVisibility(View.GONE);
                fridgeLayout.setVisibility(View.GONE);
                TVLayout.setVisibility(View.GONE);
                CCTVLayout.setVisibility(View.GONE);
                ironLayout.setVisibility(View.GONE);
                inductionLayout.setVisibility(View.GONE);
                routerLayout.setVisibility(View.GONE);
                heaterLayout.setVisibility(View.GONE);
                D2HLayout.setVisibility(View.GONE);
                otherLayout.setVisibility(View.GONE);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Fan" + " details");

                FanBlades.setText(applianceDetailFan.noOfBlades);
                FanCompanyName.setText(applianceDetailFan.brand);
                FanDays.setText(applianceDetailFan.timeSinceInstallation);
                FanModel.setText(applianceDetailFan.model);
                FanRoomNo.setText(applianceDetailFan.roomNo);

                builder.setView(viewDialog);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.setNegativeButton("Cancel",null);

                builder.show();
            }
        });

        first.setText(applianceDetailFan.getRoomNo());
        second.setText(applianceDetailFan.getBrand());
        third.setText(applianceDetailFan.getTimeSinceInstallation());

        return listViewItemFan;
    }


}

