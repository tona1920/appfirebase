package mx.edu.utng.appfirebase2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView tvMensaje;
    private EditText etTexto;

    DatabaseReference base = FirebaseDatabase.getInstance().getReference();
    DatabaseReference msjRef= base.child("mensaje");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMensaje=(TextView) findViewById(R.id.tVMensaje);
        etTexto=(EditText) findViewById(R.id.eTTexto);
    }

    public void modificar(View view) {
        String mensaje= etTexto.getText().toString();
        msjRef.setValue(mensaje);
        etTexto.setText("");
    }

    @Override
    protected void onStart() {
        super.onStart();

        msjRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                tvMensaje.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}