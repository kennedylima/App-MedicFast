package com.medicfast.appmedicfast.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.medicfast.appmedicfast.R;
import com.medicfast.appmedicfast.model.PontoAtendimento;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListaPontoAtendimentoActivity extends AppCompatActivity {

    @Bind(R.id.lista)
    ListView lista;

    public static boolean chamadoPelaListaDeMediacamento =false ;

    public static boolean isChamadoPelaListaDeEspecialidade() {
        return chamadoPelaListaDeEspecialidade;
    }

    public static void setChamadoPelaListaDeEspecialidade(boolean chamadoPelaListaDeEspecialidade) {
        ListaPontoAtendimentoActivity.chamadoPelaListaDeEspecialidade = chamadoPelaListaDeEspecialidade;
    }

    public static boolean chamadoPelaListaDeEspecialidade =false ;

    public static boolean isChamadoPelaListaDeMediacamento() {
        return chamadoPelaListaDeMediacamento;
    }

    public static void setChamadoPelaListaDeMediacamento(boolean chamadoPelaListaDeMediacamento) {
        ListaPontoAtendimentoActivity.chamadoPelaListaDeMediacamento = chamadoPelaListaDeMediacamento;
    }

    public static List<PontoAtendimento> pontosDeAtendimento = new ArrayList<PontoAtendimento>();

    public static List<PontoAtendimento> getPontosDeAtendimento() {
        return pontosDeAtendimento;
    }

    public static void setPontosDeAtendimento(List<PontoAtendimento> pontosDeAtendimento) {
        ListaPontoAtendimentoActivity.pontosDeAtendimento = pontosDeAtendimento;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        ButterKnife.bind(this);
        this.setTitle("Pontos de Atendimento");
        ArrayAdapter<PontoAtendimento> adapter = new ArrayAdapter<PontoAtendimento>(ListaPontoAtendimentoActivity.this, android.R.layout.simple_list_item_activated_1,pontosDeAtendimento );
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final PontoAtendimento ponto = (PontoAtendimento) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListaPontoAtendimentoActivity.this, SenhaActivity.class);
                intent.putExtra("Ponto", ponto);
                startActivity(intent);
            }
        });

        if(chamadoPelaListaDeMediacamento == false && chamadoPelaListaDeEspecialidade==false){
            PontoAtendimentoActivity.fecharTelaDeCarregando();
        }

    }

    @Override
    public void onBackPressed(){
        finish();
        Intent intent = new Intent(ListaPontoAtendimentoActivity.this, MainActivity.class);
        startActivity(intent);
    }



}
