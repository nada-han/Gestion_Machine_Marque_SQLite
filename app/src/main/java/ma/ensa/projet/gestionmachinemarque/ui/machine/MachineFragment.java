package ma.ensa.projet.gestionmachinemarque.ui.machine;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

import ma.ensa.projet.gestionmachinemarque.AddActivity;
import ma.ensa.projet.gestionmachinemarque.R;
import ma.ensa.projet.gestionmachinemarque.MyDatabaseHelper;
import ma.ensa.projet.gestionmachinemarque.CustomAdapter;


public class MachineFragment extends Fragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton add_button;

    private MyDatabaseHelper myDB;
    private ArrayList<String> machine_id, machine_ref, machine_prix, machine_marque;
    private CustomAdapter customAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_machine, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        add_button = root.findViewById(R.id.add_button);
        swipeRefreshLayout = root.findViewById(R.id.swipeRefreshLayout);

        myDB = new MyDatabaseHelper(requireContext());
        machine_id = new ArrayList<>();
        machine_ref = new ArrayList<>();
        machine_prix = new ArrayList<>();
        machine_marque = new ArrayList<>();

        customAdapter = new CustomAdapter((Activity) requireContext(), requireContext(), machine_id, machine_ref, machine_marque, machine_prix);


        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        swipeRefreshLayout.setOnRefreshListener(() -> storeDataInArrays());

        add_button.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), AddActivity.class);
            startActivity(intent);
        });

        storeDataInArrays();

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            storeDataInArrays();
        }
    }

    private void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(requireContext(), "No data", Toast.LENGTH_SHORT).show();
        } else {
            machine_id.clear();
            machine_ref.clear();
            machine_prix.clear();
            machine_marque.clear();

            while (cursor.moveToNext()) {
                machine_id.add(cursor.getString(0));
                machine_ref.add(cursor.getString(1));
                machine_prix.add(cursor.getString(2));
                machine_marque.add(cursor.getString(3));
            }

            customAdapter.notifyDataSetChanged();
        }
        swipeRefreshLayout.setRefreshing(false);
    }


}
