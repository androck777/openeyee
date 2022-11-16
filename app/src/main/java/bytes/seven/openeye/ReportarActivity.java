package bytes.seven.openeye;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skydoves.elasticviews.ElasticCheckButton;

public class ReportarActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    ElasticCheckButton consultarlatlong, btnGuardar;
    EditText edtlat, edtlong, edtdelincuentes, edtdescripcion, edtresumen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar);

        consultarlatlong = (ElasticCheckButton)findViewById(R.id.bottonn);
        btnGuardar = (ElasticCheckButton)findViewById(R.id.btnenviar);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        getlocalizacion();
        edtlat = findViewById(R.id.txtlat);
        edtlong = findViewById(R.id.txtlong);
        edtdelincuentes = findViewById(R.id.txtcuerpo);
        edtdescripcion = findViewById(R.id.txtdelito);
        edtresumen = findViewById(R.id.txtdescrip);
        getCargarLocalizacion();
        guardarDatos();
    }

    private void guardarDatos() {
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latitud = edtlat.getText().toString().trim();
                String longitud = edtlong.getText().toString().trim();
                String delincuentes = edtdelincuentes.getText().toString().trim();
                String descripcion = edtdescripcion.getText().toString().trim();
                String resumen = edtresumen.getText().toString().trim();

                if(TextUtils.isEmpty(latitud)){
                    Toast.makeText(getApplicationContext(), "por favor pulsar el boton Generar Ubicacion", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(longitud)){
                    Toast.makeText(getApplicationContext(), "por favor pulsar el boton Generar Ubicacion", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(delincuentes)){
                    Toast.makeText(getApplicationContext(), "por favor ingresar descripcion", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(descripcion)){
                    Toast.makeText(getApplicationContext(), "por favor da ingresar descripcion", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(resumen)){
                    Toast.makeText(getApplicationContext(), "por favor escribe una breve resumen del acontecimiento", Toast.LENGTH_SHORT).show();
                }else{
                    lugares lugares = new lugares(Double.valueOf(latitud),Double.valueOf(longitud), delincuentes, descripcion, resumen);

                    databaseReference.child("Lugares").child(delincuentes).setValue(lugares);
                    Toast.makeText(getApplicationContext(), "Datos Enviados Correctamente", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ReportarActivity.this,MenuActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        });
    }

    private void getCargarLocalizacion() {
        consultarlatlong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager locationManager = (LocationManager) ReportarActivity.this.getSystemService(Context.LOCATION_SERVICE);

                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(@NonNull Location location) {
                        edtlat.setText(""+location.getLatitude());
                        edtlong.setText(""+location.getLongitude());
                    }
                };
                int permiso = ContextCompat.checkSelfPermission(ReportarActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
                locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                Toast.makeText(ReportarActivity.this,  "Ubicacion generada con exito", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getlocalizacion (){
        int permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if(permiso == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }
    }
}