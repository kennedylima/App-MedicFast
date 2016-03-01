package com.medicfast.appmedicfast.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.medicfast.appmedicfast.model.Especialidade;
import com.medicfast.appmedicfast.model.Medicamento;
import com.medicfast.appmedicfast.model.Medico;
import com.medicfast.appmedicfast.model.PontoAtendimento;
import com.medicfast.appmedicfast.model.Senha;
import com.medicfast.appmedicfast.model.TipoOcorrencia;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Kennedy on 18/10/2015.
 */
public class ApiWeb {


        public static final String BASE_URL = "http://192.168.0.3:8080/medicfast/ws"; // Casa
        //public static final String BASE_URL = "http://192.168.0.146:8080/medicfast/ws";//Metodo


        public static Rotas rotasApi;

        public static Rotas getRotas() {
            if (rotasApi == null) {

                //Serializador Client  Json
                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                        .create();

                //Inicializa o Rest Client
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .setConverter(new GsonConverter(gson))
                        .setEndpoint(String.format("%s", BASE_URL))
                        .build();

                //Objeto rest
                rotasApi = restAdapter.create(Rotas.class);
            }

            return rotasApi;
        }

        public interface Rotas{

            //Senha
            @POST("/senha/salvar")
            public void salvarSenha(@Body Senha senha, Callback<Senha> callback);

            @GET("/senha/buscarPorId/{idSenha}")
            public void buscarPorId(@Path("idSenha") Integer senha, Callback<Senha> callback);

            //Medicamento
            @GET("/medicamento/listar")
            public void listarMedicamento(Callback<List<Medicamento>> callback);


            //Medico
            @GET("/especialidade/listar")
            public void listarEspecialidade( Callback<List<Especialidade>> callback);

            @GET("/especialidade/listarPorPonto/{idPonto}")
            public void listarEspecialidadePorPonto(@Path("idPonto")Integer idPonto, Callback<List<Especialidade>> callback);

            //Ocorrencia
            @GET("/ocorrencia/lista")
            public void listarOcorrencia(Callback<List<TipoOcorrencia>> callback);

            //Ponto de Atendimento
            @GET("/ponto/lista")
            public void listarPonto(Callback<List<PontoAtendimento>> callback);

            @GET("/ponto/lista/{idPonto}")
            public void buscarPontoPorId(@Path("idPonto") Integer idPonto, Callback<PontoAtendimento> callback);


            @GET("/ponto/listarPorEspecialidade/{idEspecialidade}")
            public void listarPontoPorEspecialidade(@Path("idEspecialidade") Integer especialidade,Callback<List<PontoAtendimento>> callback);

            @GET("/ponto/listarPorMedicamento/{idMedicamento}")
            public void listarPontoPorMedicamento(@Path("idMedicamento") Integer medicamento, Callback<List<PontoAtendimento>> callback);


        }

}
