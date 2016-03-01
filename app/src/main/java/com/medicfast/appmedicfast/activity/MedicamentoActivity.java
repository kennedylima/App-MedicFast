package com.medicfast.appmedicfast.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.medicfast.appmedicfast.R;
import com.medicfast.appmedicfast.api.ApiWeb;
import com.medicfast.appmedicfast.model.Medicamento;
import com.medicfast.appmedicfast.model.PontoAtendimento;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MedicamentoActivity extends AppCompatActivity {


    @Bind(R.id.medicamento)
    ListView medicamento;

    public static ProgressDialog  progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        progressDialog = ProgressDialog.show(this, "Aguarde ...", "Carregando Medicamentos ...", true);
        progressDialog.setCancelable(true);
        progressDialog.show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento);
        ButterKnife.bind(this);
        this.setTitle("Medicamento");

        ApiWeb.getRotas().listarMedicamento(new Callback<List<Medicamento>>() {
            @Override
            public void success(List<Medicamento> medicamentos, Response response) {
                ListaMedicamentoActivity.setMedicamentos(medicamentos);
                Intent intent = new Intent(MedicamentoActivity.this, ListaMedicamentoActivity.class);
                startActivity(intent);


            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MedicamentoActivity.this, "Não foi possivel se conectar ao servidor", Toast.LENGTH_SHORT).show();
            }
        });


    }

   /* @OnClick(R.id.buscarMedicamento)
    public void buscarMedicamentoPorPontosDeAtendimento(){
        Medicamento medicamentoSelecionado =  (Medicamento)medicamento.getSelectedItem();

        ApiWeb.getRotas().listarPontoPorMedicamento(medicamentoSelecionado.getId(), new Callback<List<PontoAtendimento>>() {

            @Override
            public void success(List<PontoAtendimento> pontoAtendimentos, Response response) {
                ListaPontoAtendimentoActivity.setPontosDeAtendimento(pontoAtendimentos);
                Intent intent = new Intent(MedicamentoActivity.this, ListaPontoAtendimentoActivity.class);
                startActivity(intent);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MedicamentoActivity.this, "Não foi possivel se conectar ao servidor", Toast.LENGTH_SHORT).show();
            }
        });


    }*/
}
