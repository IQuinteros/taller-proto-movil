package com.iquinteros.taller_prototipos.ui.gallery;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.iquinteros.taller_prototipos.R;
import com.iquinteros.taller_prototipos.api.MiPerfilConexion;
import com.iquinteros.taller_prototipos.databinding.FragmentGalleryBinding;
import com.iquinteros.taller_prototipos.interfaces.ICallbackFunciono;
import com.iquinteros.taller_prototipos.modelos.MiPerfilModelo;
import com.iquinteros.taller_prototipos.ui.editar.EditarActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;

    final Calendar miCalendario = Calendar.getInstance();

    MiPerfilConexion perfilConexion = new MiPerfilConexion();


    EditText newNombre;
    EditText newApellido;
    EditText newEdad;
    EditText newDescripcion;
    Switch newGenio;
    EditText newFechaEspecial;
    EditText newPrecio;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        inicializarCampos();
        cargarDialogoFecha();
    }

    private void inicializarCampos(){
        newNombre = getActivity().findViewById(R.id.new_nombre);
        newApellido = getActivity().findViewById(R.id.new_apellido);
        newEdad = getActivity().findViewById(R.id.new_edad);
        newDescripcion = getActivity().findViewById(R.id.new_descripcion);
        newGenio = getActivity().findViewById(R.id.new_genio);
        newFechaEspecial = getActivity().findViewById(R.id.new_fecha_especial);
        newPrecio = getActivity().findViewById(R.id.new_precio);

        Button insertarBoton = getView().findViewById(R.id.btn_insertar);
        insertarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Funcionalidad insertar
                MiPerfilModelo nuevoPerfil = new MiPerfilModelo();

                nuevoPerfil.setNombre(newNombre.getText().toString());
                nuevoPerfil.setApellido(newApellido.getText().toString());

                try{
                    nuevoPerfil.setEdad(Integer.parseInt(newEdad.getText().toString()));
                } catch(Exception e){}
                nuevoPerfil.setDescripcion(newDescripcion.getText().toString());

                nuevoPerfil.setEsGenio(newGenio.isChecked());
                nuevoPerfil.setHoraCreacion(new Date());

                try{
                    nuevoPerfil.setFechaEspecial(miCalendario.getTime());
                } catch(Exception e){}
                try{
                    nuevoPerfil.setPrecio(Double.parseDouble(newPrecio.getText().toString()));
                } catch(Exception e){}

                perfilConexion.insertar(nuevoPerfil, new ICallbackFunciono() {
                    @Override
                    public void retornar(boolean funciono) {
                        // Resultado
                        getActivity().getSupportFragmentManager().popBackStack();
                    }
                });
            }
        });
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

        newFechaEspecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), callback,
                        miCalendario.get(Calendar.YEAR),
                        miCalendario.get(Calendar.MONTH),
                        miCalendario.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });
    }

    private void actualizarTextoFecha(){
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        newFechaEspecial.setText(formato.format(miCalendario.getTime()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}