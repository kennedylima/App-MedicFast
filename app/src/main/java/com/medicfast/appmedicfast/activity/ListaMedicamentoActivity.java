package com.medicfast.appmedicfast.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.medicfast.appmedicfast.R;
import com.medicfast.appmedicfast.api.ApiWeb;
import com.medicfast.appmedicfast.model.Medicamento;
import com.medicfast.appmedicfast.model.PontoAtendimento;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ListaMedicamentoActivity extends AppCompatActivity {


    @Bind(R.id.listaMedicamento)
    ListView listaMedicamento;

    public static List<Medicamento> medicamentos = new ArrayList<Medicamento>();

    public static List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public static void setMedicamentos(List<Medicamento> medicamentos) {
        ListaMedicamentoActivity.medicamentos = medicamentos;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicamento);
        ButterKnife.bind(this);
        this.setTitle("Medicamentos");
        ArrayAdapter<Medicamento> adapter = new ArrayAdapter<Medicamento>(ListaMedicamentoActivity.this, android.R.layout.simple_list_item_activated_1,medicamentos );
        listaMedicamento.setAdapter(adapter);

        listaMedicamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Medicamento medicamento = (Medicamento) parent.getItemAtPosition(position);
                ApiWeb.getRotas().listarPontoPorMedicamento(medicamento.getId(), new Callback<List<PontoAtendimento>>() {

                    @Override
                    public void success(List<PontoAtendimento> pontoAtendimentos, Response response) {
                        ListaPontoAtendimentoActivity.setPontosDeAtendimento(pontoAtendimentos);
                        Intent intent = new Intent(ListaMedicamentoActivity.this, ListaPontoAtendimentoActivity.class);
                        ListaPontoAtendimentoActivity.setChamadoPelaListaDeMediacamento(true);

                        startActivity(intent);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(ListaMedicamentoActivity.this, "Não foi possivel se conectar ao servidor", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    @Override
    public void onBackPressed(){
        finish();
        Intent intent = new Intent(ListaMedicamentoActivity.this, MainActivity.class);
        startActivity(intent);
    }


}
