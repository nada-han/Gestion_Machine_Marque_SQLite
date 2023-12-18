package ma.ensa.projet.gestionmachinemarque;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    EditText ref_input, prix_input, marque_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ref_input = findViewById(R.id.ref_input);
        prix_input = findViewById(R.id.prix_input);
        marque_input = findViewById(R.id.marque_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(ref_input.getText().toString().trim(),
                        Integer.valueOf(prix_input.getText().toString().trim()),
                        marque_input.getText().toString().trim());
            }
        });
    }
}