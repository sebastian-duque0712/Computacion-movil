package com.example.crudandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.example.crudandroid.model.Empleado;
import com.example.crudandroid.sinterface.CrudEmpleadoInterface;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    CrudEmpleadoInterface crudempleado;

    private EditText id;
    private EditText nombre;
    private EditText contraseña;
    private EditText email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void limpiarCampos(View view){
        id.setText("");
        nombre.setText("");
        contraseña.setText("");
        email.setText("");
    }

    public void crearEmpleado(View view){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.16.10.27:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        crudempleado = retrofit.create(CrudEmpleadoInterface.class);
        id = findViewById(R.id.TextID);
        Long id_emple = Long.parseLong(id.getText().toString());
        nombre = findViewById(R.id.TextNombre);
        String nombre_emple = nombre.getText().toString();
        contraseña = findViewById(R.id.TextContraseña);
        String password_emple = contraseña.getText().toString();
        email = findViewById(R.id.TextEmail);
        String email_emple = email.getText().toString();
        Empleado empleado = new Empleado(id_emple, nombre_emple, password_emple, email_emple);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),
                new Gson().toJson(empleado));
        Call<Empleado> call = crudempleado.createEmployee(requestBody);

        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if (response.isSuccessful()) {
                    System.out.println(response.message());
                    return;
                }
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Log.e("Throw error: ",t.getMessage());
            }
        });
    }

    public void BorrarEmpleado(View view){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.16.10.27:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        crudempleado = retrofit.create(CrudEmpleadoInterface.class);
        id = findViewById(R.id.TextID);
        Long id_empleado = Long.parseLong(id.getText().toString());
        Call<Void> call = crudempleado.deleteEmployee(id_empleado);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    System.out.println(response.message());
                    return;
                }
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Throw error: ",t.getMessage());
            }
        });
    }

    public void BuscarEmpleado(View view){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.16.10.27:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        crudempleado = retrofit.create(CrudEmpleadoInterface.class);
        id = findViewById(R.id.TextID);
        nombre = findViewById(R.id.TextNombre);
        contraseña = findViewById(R.id.TextContraseña);
        email = findViewById(R.id.TextEmail);
        Long id_emplado = Long.parseLong(id.getText().toString());
        Call<Empleado> call = crudempleado.searchEmployee(id_emplado);
        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if (response.isSuccessful()) {
                    Empleado empleado = response.body();
                    nombre.setText(empleado.getNombre());
                    contraseña.setText(empleado.getPassword());
                    email.setText(empleado.getEmail());
                }
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Log.e("Throw error: ",t.getMessage());
            }
        });
    }

    public void ActializarEpleado(View view){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.16.10.27:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        crudempleado = retrofit.create(CrudEmpleadoInterface.class);
        id = findViewById(R.id.TextID);
        nombre = findViewById(R.id.TextNombre);
        contraseña = findViewById(R.id.TextContraseña);
        email = findViewById(R.id.TextEmail);
        Long ID = Long.parseLong(id.getText().toString());
        String nombre_empleado = nombre.getText().toString();
        String password = contraseña.getText().toString();
        String email_empleado = email.getText().toString();
        Empleado empleado = new Empleado(ID, nombre_empleado, password, email_empleado);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),
                new Gson().toJson(empleado));
        Call<Empleado> call = crudempleado.updateEmployee(ID, requestBody);

        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if (response.isSuccessful()) {
                    System.out.println(response.message());
                    return;
                }
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Log.e("Throw error: ",t.getMessage());
            }
        });
    }
}