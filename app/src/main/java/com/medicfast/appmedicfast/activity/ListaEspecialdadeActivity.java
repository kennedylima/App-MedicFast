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
import com.medicfast.appmedicfast.model.Especialidade;
import com.medicfast.appmedicfast.model.PontoAtendimento;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ListaEspecialdadeActivity extends AppCompatActivity {

    @Bind(R.id.listaEspecialidade)
    ListView listaEspecialidade;

    public static List<Especialidade> especialidades = new ArrayList<Especialidade>();

    public static List<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public static void setEspecialidades(List<Especialidade> especialidades) {
        ListaEspecialdadeActivity.especialidades = especialidades;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_especialdade);
        ButterKnife.bind(this);
        this.setTitle("Especialidades");

        ArrayAdapter<Especialidade> adapter = new ArrayAdapter<Especialidade>(ListaEspecialdadeActivity.this, android.R.layout.simple_list_item_activated_1,especialidades );
        listaEspecialidade.setAdapter(adapter);
        EspecialidadeActivity.fecharTelaDeCarregando();

        listaEspecialidade.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Especialidade especialidade = (Especialidade) parent.getItemAtPosition(position);
                ApiWeb.getRotas().listarPontoPorEspecialidade(especialidade.getId(), new Callback<List<PontoAtendimento>>() {
                    @Override
                    public void success(List<PontoAtendimento> pontoAtendimentos, Response response) {
                        ListaPontoAtendimentoActivity.setPontosDeAtendimento(pontoAtendimentos);
                        Intent intent = new Intent(ListaEspecialdadeActivity.this, ListaPontoAtendimentoActivity.class);
                        ListaPontoAtendimentoActivity.setChamadoPelaListaDeEspecialidade(true);
                        startActivity(intent);

                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });

            }
        });
    }

    @Override
    public void onBackPressed(){
        finish();
        Intent intent = new Intent(ListaEspecialdadeActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
