package com.iquinteros.taller_prototipos.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.iquinteros.taller_prototipos.R;
import com.iquinteros.taller_prototipos.api.MiPerfilConexion;
import com.iquinteros.taller_prototipos.databinding.FragmentHomeBinding;
import com.iquinteros.taller_prototipos.interfaces.ICallbackMiPerfil;
import com.iquinteros.taller_prototipos.modelos.MiPerfilModelo;
import com.iquinteros.taller_prototipos.ui.editar.EditarActivity;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();

        cargarPerfiles();
    }

    private void cargarPerfiles(){
        MiPerfilConexion perfilConexion = new MiPerfilConexion();
        perfilConexion.seleccionar(new ICallbackMiPerfil() {
            @Override
            public void retornar(List<MiPerfilModelo> resultado) {

                ArrayAdapter<MiPerfilModelo> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, resultado);

                final ListView listaPerfiles = getView().findViewById(R.id.seleccionar_perfiles);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listaPerfiles.setAdapter(adapter);
                    }
                });

                listaPerfiles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        if(listaPerfiles.getItemAtPosition(position) == null){
                            return;
                        }

                        MiPerfilModelo perfilSeleccionado = (MiPerfilModelo) listaPerfiles.getItemAtPosition(position);

                        Intent intent = new Intent(getActivity(), EditarActivity.class);
                        intent.putExtra("id", perfilSeleccionado.getId());
                        startActivity(intent);

                    }
                });

            }
        });
    }
}