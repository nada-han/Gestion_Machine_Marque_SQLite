package ma.ensa.projet.gestionmachinemarque;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;

    private Activity activity;
    private ArrayList machine_id, machine_ref, machine_marque, machine_prix;


    public CustomAdapter(Activity activity, Context context, ArrayList machine_id, ArrayList machine_ref, ArrayList machine_marque, ArrayList machine_prix){
        this.activity = activity;
        this.context = context;
        this.machine_id = machine_id;
        this.machine_ref = machine_ref;
        this.machine_marque = machine_marque;
        this.machine_prix = machine_prix;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.machine_id_txt.setText(String.valueOf(machine_id.get(position)));
        holder.machine_ref_txt.setText(String.valueOf(machine_ref.get(position)));
        holder.machine_marque_txt.setText(String.valueOf(machine_marque.get(position)));
        holder.machine_prix_txt.setText(String.valueOf(machine_prix.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(machine_id.get(position)));
                intent.putExtra("reference", String.valueOf(machine_ref.get(position)));
                intent.putExtra("marque", String.valueOf(machine_marque.get(position)));
                intent.putExtra("prix", String.valueOf(machine_prix.get(position)));
                activity.startActivityForResult(intent,1);

            }
        });
    }

    @Override
    public int getItemCount() {
        return machine_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView machine_id_txt, machine_ref_txt, machine_marque_txt, machine_prix_txt;

        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            machine_id_txt = itemView.findViewById(R.id.machine_id_txt);
            machine_ref_txt = itemView.findViewById(R.id.machine_ref_txt);
            machine_marque_txt = itemView.findViewById(R.id.machine_marque_txt);
            machine_prix_txt = itemView.findViewById(R.id.machine_prix_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);


        }
    }
}
