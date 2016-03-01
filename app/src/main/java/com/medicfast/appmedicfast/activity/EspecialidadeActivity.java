package com.medicfast.appmedicfast.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.medicfast.appmedicfast.R;
import com.medicfast.appmedicfast.api.ApiWeb;
import com.medicfast.appmedicfast.model.Especialidade;
import com.medicfast.appmedicfast.model.PontoAtendimento;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class EspecialidadeActivity extends AppCompatActivity {

    public static ProgressDialog  progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        progressDialog = ProgressDialog.show(this, "Aguarde ...", "Carregando as Especialidades ...", true);
        progressDialog.setCancelable(true);
        progressDialog.show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_especialidade);
        ButterKnife.bind(this);
        this.setTitle("Especialidade Médica");

        ApiWeb.getRotas().listarEspecialidade(new Callback<List<Especialidade>>() {
            @Override
            public void success(List<Especialidade> especialidades, Response response) {
                ListaEspecialdadeActivity.setEspecialidades(especialidades);
                Intent intent = new Intent(EspecialidadeActivity.this, ListaEspecialdadeActivity.class);
                startActivity(intent);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(EspecialidadeActivity.this, "Não foi possivel se conectar ao servidor", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public static void fecharTelaDeCarregando(){
        //verifica se a tela está aberta, se sim então a janela será fechada
        if(progressDialog.isShowing()){
            progressDialog.dismiss();}
    }

    /*@OnClick(R.id.buscarEspecialidade)
    public void buscarEspecialdiadeMedicaNosPontosDeAtendimento(){
        Especialidade especialidade =  (Especialidade)especialidadeMedica.getSelectedItem();

        ApiWeb.getRotas().listarPontoPorEspecialidade(especialidade.getId(), new Callback<List<PontoAtendimento>>() {
            @Override
            public void success(List<PontoAtendimento> pontoAtendimentos, Response response) {
                ListaPontoAtendimentoActivity.setPontosDeAtendimento(pontoAtendimentos);
                Intent intent = new Intent(EspecialidadeActivity.this,ListaPontoAtendimentoActivity.class);
                startActivity(intent);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


    }*/
}
