package com.iquinteros.taller_prototipos.ui.editar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;

import com.iquinteros.taller_prototipos.R;
import com.iquinteros.taller_prototipos.api.MiPerfilConexion;
import com.iquinteros.taller_prototipos.interfaces.ICallbackFunciono;
import com.iquinteros.taller_prototipos.interfaces.ICallbackMiPerfil;
import com.iquinteros.taller_prototipos.modelos.MiPerfilModelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class EditarActivity extends AppCompatActivity {

    final Calendar miCalendario = Calendar.getInstance();

    int id = 0;
    MiPerfilConexion perfilConexion = new MiPerfilConexion();
    MiPerfilModelo perfilCargado = null;

    EditText editId;
    EditText editNombre;
    EditText editApellido;
    EditText editEdad;
    EditText editDescripcion;
    EditText editHora;
    Switch editGenio;
    EditText editFechaEspecial;
    EditText editPrecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        inicializarCampos();
        cargarDialogoFecha();

        cargarPerfil();
    }

    private void cargarDialogoFecha(){
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                miCalendario.set(Calendar.YEAR, year);
                miCalendario.set(Calendar.MONTH, month);
                miCalendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                actualizarTextoFecha();
            }
        };

        editFechaEspecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditarActivity.this, callback,
                        miCalendario.get(Calendar.YEAR),
                        miCalendario.get(Calendar.MONTH),
                        miCalendario.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });
    }

    private void actualizarTextoFecha(){
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        editFechaEspecial.setText(formato.format(miCalendario.getTime()));
    }

    private void cargarPerfil(){
        perfilConexion.seleccionar(new ICallbackMiPerfil() {
            @Override
            public void retornar(List<MiPerfilModelo> resultado) {
                for(MiPerfilModelo fila : resultado){
                    if(fila.getId() == id){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mostrarDatos(fila);
                            }
                        });
                        return;
                    }
                }
            }
        });
    }

    private void inicializarCampos(){
        editId = findViewById(R.id.edit_id);
        editNombre = findViewById(R.id.edit_nombre);
        editApellido = findViewById(R.id.edit_apellido);
        editEdad = findViewById(R.id.edit_edad);
        editDescripcion = findViewById(R.id.edit_descripcion);
        editHora = findViewById(R.id.edit_hora);
        editGenio = findViewById(R.id.edit_genio);
        editFechaEspecial = findViewById(R.id.edit_fecha_especial);
        editPrecio = findViewById(R.id.edit_precio);
    }

    private void mostrarDatos(MiPerfilModelo perfil){
        perfilCargado = perfil;

        editId.setText(Integer.toString(perfil.getId()));
        editNombre.setText(perfil.getNombre());
        editApellido.setText(perfil.getApellido());
        editEdad.setText(Integer.toString(perfil.getEdad()));
        editDescripcion.setText(perfil.getDescripcion());

        try {
            editHora.setText(perfil.getHoraCreacion().toString());
        } catch(Exception e){}
        editGenio.setChecked(perfil.isEsGenio());
        try {
            editFechaEspecial.setText(perfil.getFechaEspecial().toString());
            miCalendario.setTime(perfil.getFechaEspecial());
        } catch(Exception e){}
        editPrecio.setText(Double.toString(perfil.getPrecio()));
    }

    public void onEditarClicked(View view){
        perfilCargado.setNombre(editNombre.getText().toString());
        perfilCargado.setApellido(editApellido.getText().toString());

        try{
            perfilCargado.setEdad(Integer.parseInt(editEdad.getText().toString()));
        } catch(Exception e){}
        perfilCargado.setDescripcion(editDescripcion.getText().toString());

        perfilCargado.setEsGenio(editGenio.isChecked());

        try{
            perfilCargado.setFechaEspecial(miCalendario.getTime());
        } catch(Exception e){}
        try{
            perfilCargado.setPrecio(Double.parseDouble(editEdad.getText().toString()));
        } catch(Exception e){}

        perfilConexion.editar(perfilCargado, new ICallbackFunciono() {
            @Override
            public void retornar(boolean funciono) {
                // Resultado
            }
        });
    }

    public void onEliminarClicked(View view){
        perfilConexion.eliminar(perfilCargado, new ICallbackFunciono() {
            @Override
            public void retornar(boolean funciono) {
                finish();
            }
        });
    }
}