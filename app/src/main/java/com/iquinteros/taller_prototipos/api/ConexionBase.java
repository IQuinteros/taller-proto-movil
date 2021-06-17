package com.iquinteros.taller_prototipos.api;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class ConexionBase {

    private static OkHttpClient client = new OkHttpClient();

    protected void solicitar(String url, List<Parametro> parametros, Callback callback){
        FormBody.Builder builder = new FormBody.Builder();
        for(Parametro parametro : parametros){
            builder.add(parametro.getNombre(), parametro.getValor());
        }
        RequestBody body = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(callback);
    }

}
