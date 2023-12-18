package ma.ensa.projet.gestionmachinemarque;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText ref_input, marque_input, prix_input;
    Button update_button, delete_button;

    String id, reference, marque, prix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        ref_input = findViewById(R.id.ref_input2);
        marque_input = findViewById(R.id.marque_input2);
        prix_input = findViewById(R.id.prix_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(reference);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                reference = ref_input.getText().toString().trim();
                marque = marque_input.getText().toString().trim();
                prix = prix_input.getText().toString().trim();
                myDB.updateData(id, reference, marque, prix);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("reference") &&
                getIntent().hasExtra("marque") && getIntent().hasExtra("prix")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            reference = getIntent().getStringExtra("reference");
            marque = getIntent().getStringExtra("marque");
            prix = getIntent().getStringExtra("prix");

            //Setting Intent Data
            ref_input.setText(reference);
            marque_input.setText(marque);
            prix_input.setText(prix);
            Log.d("stev", reference+" "+marque+" "+prix);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + reference + " ?");
        builder.setMessage("Are you sure you want to delete " + reference + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}