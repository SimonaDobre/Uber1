package com.simona.uber1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterClient extends RecyclerView.Adapter<AdapterClient.ViewHolderClient> {

    Context context;
    ArrayList<Client> clientsArray;

    public AdapterClient(Context context, ArrayList<Client> clientsArray) {
        this.context = context;
        this.clientsArray = clientsArray;
    }

    class ViewHolderClient extends RecyclerView.ViewHolder {

        TextView nameTV, phoneTV, emailTV, ratingTV;

        public ViewHolderClient(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.nameTV);
            phoneTV = itemView.findViewById(R.id.phoneTV);
            emailTV = itemView.findViewById(R.id.emailTV);
            ratingTV = itemView.findViewById(R.id.ratingTV);

        }
    }


    @NonNull
    @Override
    public ViewHolderClient onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.client_row, parent, false);
        return new ViewHolderClient(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClient holder, int position) {
        Client currentClient = clientsArray.get(position);
        holder.nameTV.setText("Name: " + currentClient.getClientFirstName() + " " + currentClient.getClientLastName());
        holder.phoneTV.setText("Phone: " + currentClient.getClientPhone());
        holder.emailTV.setText("Email: " + currentClient.getClientEmail());
        holder.ratingTV.setText("Rating: " + currentClient.getRating());
    }

    @Override
    public int getItemCount() {
        if (clientsArray.size() != 0) {
            return clientsArray.size();
        }
        return 0;
    }


}
