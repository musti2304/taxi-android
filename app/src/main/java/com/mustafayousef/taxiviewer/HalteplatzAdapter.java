package com.mustafayousef.taxiviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HalteplatzAdapter extends RecyclerView.Adapter<HalteplatzAdapter.HalteplatzViewHolder> {

    private Context mContext;
    private ArrayList<Halteplatz> mHalteplaetze;

    public HalteplatzAdapter(Context context, ArrayList<Halteplatz> halteplatzList) {
        mContext = context;
        mHalteplaetze = halteplatzList;
    }

    @NonNull
    @Override
    public HalteplatzViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.halteplatz, parent, false);
        return new HalteplatzViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HalteplatzViewHolder holder, int position) {

        Halteplatz currentHalteplatz = mHalteplaetze.get(position);
        String name = currentHalteplatz.getName();
        int auftraege = currentHalteplatz.getAuftraege();
        int einstiege = currentHalteplatz.getEinstiege();
        String wartezeit = currentHalteplatz.getWartezeit().isEmpty()
                ? "---"
                : currentHalteplatz.getWartezeit();

        holder.name.setText(name);
        holder.auftraege.setText(Integer.toString(auftraege));
        holder.einstiege.setText(Integer.toString(einstiege));
        holder.wartezeit.setText(wartezeit);
    }

    @Override
    public int getItemCount() {
        return mHalteplaetze.size();
    }

    public static class HalteplatzViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView auftraege;
        public TextView einstiege;
        public TextView wartezeit;

        public HalteplatzViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            auftraege = itemView.findViewById(R.id.auftraege);
            einstiege = itemView.findViewById(R.id.einstiege);
            wartezeit = itemView.findViewById(R.id.wartezeit);
        }

    }

}
