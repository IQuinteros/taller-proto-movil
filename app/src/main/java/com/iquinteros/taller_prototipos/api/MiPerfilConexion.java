package com.iquinteros.taller_prototipos.api;

import android.util.Log;

import com.iquinteros.taller_prototipos.interfaces.ICallbackFunciono;
import com.iquinteros.taller_prototipos.interfaces.ICallbackMiPerfil;
import com.iquinteros.taller_prototipos.modelos.MiPerfilModelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MiPerfilConexion extends ConexionBase {
    // CRUD
    // Insertar
    // Editar
    // Eliminar
    // Seleccionar
    public void seleccionar(ICallbackMiPerfil callback){
        solicitar("https://ecomercioweb.000webhostapp.com/taller/api/solicitudes/mi_perfil/seleccionar.php", new ArrayList(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String respuesta = response.body().string();
                try {
                    JSONArray lista = new JSONArray(respuesta);
                    List<MiPerfilModelo> listaRetornar = new ArrayList<>();
                    for(int i = 0; i < lista.length(); i++){
                        MiPerfilModelo miPerfil = new MiPerfilModelo(lista.getJSONObject(i));
                        listaRetornar.add(miPerfil);
                    }
                    // Aquí vamos a llamar a nuestro propio callback
                    callback.retornar(listaRetornar);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void eliminar(MiPerfilModelo perfil, ICallbackFunciono callback){
        solicitar("https://ecomercioweb.000webhostapp.com/taller/api/solicitudes/mi_perfil/eliminar.php", perfil.parametros(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String funciono = response.body().string();
                    callback.retornar(Boolean.parseBoolean(funciono));
                } catch (Exception e){

                }
            }
        });
    }

    public void insertar(MiPerfilModelo perfil, ICallbackFunciono callback){
        solicitar("https://ecomercioweb.000webhostapp.com/taller/api/solicitudes/mi_perfil/insertar.php", perfil.parametros(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String funciono = response.body().string();
                    callback.retornar(Boolean.parseBoolean(funciono));
                } catch (Exception e){

                }
            }
        });
    }

    public void editar(MiPerfilModelo perfil, ICallbackFunciono callback){
        solicitar("https://ecomercioweb.000webhostapp.com/taller/api/solicitudes/mi_perfil/editar.php", perfil.parametros(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String funciono = response.body().string();
                    callback.retornar(Boolean.parseBoolean(funciono));
                } catch (Exception e){

                }
            }
        });
    }
}
