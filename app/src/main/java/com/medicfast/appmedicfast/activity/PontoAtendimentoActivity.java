package com.medicfast.appmedicfast.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.medicfast.appmedicfast.R;
import com.medicfast.appmedicfast.api.ApiWeb;
import com.medicfast.appmedicfast.model.PontoAtendimento;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PontoAtendimentoActivity extends AppCompatActivity {

    public static ProgressDialog  progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ponto_atendimento);
        this.setTitle("Pontos de Atendimento");

        progressDialog = ProgressDialog.show(this, "Aguarde ...", "Carregando Pontos de Atendimento...", true);
        progressDialog.setCancelable(true);
        progressDialog.show();

        ApiWeb.getRotas().listarPonto(new Callback<List<PontoAtendimento>>() {
            @Override
            public void success(List<PontoAtendimento> pontoAtendimentos, Response response) {
                ListaPontoAtendimentoActivity.setPontosDeAtendimento(pontoAtendimentos);
                Intent intent = new Intent(PontoAtendimentoActivity.this, ListaPontoAtendimentoActivity.class);
                ListaPontoAtendimentoActivity.setChamadoPelaListaDeMediacamento(false);
                startActivity(intent);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(PontoAtendimentoActivity.this, "Não foi possivel se conectar ao servidor", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static void fecharTelaDeCarregando(){
        //verifica se a tela está aberta, se sim então a janela será fechada
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }


}
