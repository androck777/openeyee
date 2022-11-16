package bytes.seven.openeye;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.skydoves.elasticviews.ElasticCheckButton;

public class loginActivity extends AppCompatActivity {
    ElasticCheckButton recuperar, Registro, iniciar;
    TextInputEditText gmail, password;
    private FirebaseAuth mAuth;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        recuperar = (ElasticCheckButton) findViewById(R.id.Recuperarz);
        Registro =(ElasticCheckButton) findViewById(R.id.registro);
        iniciar =(ElasticCheckButton) findViewById(R.id.iniciar);

        gmail = findViewById(R.id.gmail);
        password = findViewById(R.id.password);
        alertDialog = new SpotsDialog.Builder().setContext(loginActivity.this).setMessage("Por favor espere...").build();

        getRecuperar();
        getRegistro();
        getLogin();
        limpiar();
    }

    private void limpiar() {
        gmail.setText("");
        password.setText("");
    }

    private void getLogin() {
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userE = gmail.getText().toString().trim();
                String passE = password.getText().toString().trim();
                if(TextUtils.isEmpty(userE)){
                    Toast.makeText(getApplicationContext(), "Ingrese un correo registrado", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(passE)){
                    Toast.makeText(getApplicationContext(), "Ingrese una contraseña valida", Toast.LENGTH_SHORT).show();
                }else{
                    alertDialog.show();
                    mAuth.signInWithEmailAndPassword(userE,passE).addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                            }else{
                                limpiar();
                                Intent i = new Intent(loginActivity.this, MenuActivity.class);
                                startActivity(i);
                            }
                            alertDialog.dismiss();
                        }
                    });
                }
            }
        });
    }

    private void getRegistro() {
        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(loginActivity.this, RegistroActivity.class);
                startActivity(i);
            }
        });
    }

    private void getRecuperar(){
        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(loginActivity.this, recuperarActivity.class);
                startActivity(i);

            }
        });
    }
}