package com.vedruna.brunrodriguezmultimedia.RetrofitService;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase para proporcionar el servicio Retrofit para realizar llamadas a la API.
 */
public class RetrofitService {

    private Retrofit retrofit;

    /**
     * Constructor de la clase RetrofitService.
     * Inicializa el servicio Retrofit.
     */
    public RetrofitService() {
        initializeRetrofit();
    }

    /**
     * Inicializa el objeto Retrofit con la configuración base y el convertidor Gson.
     */
    private void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.35:8080")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    /**
     * Obtiene el objeto Retrofit configurado.
     *
     * @return el objeto Retrofit.
     */
    public Retrofit getRetrofit() {
        return retrofit;
    }
}
