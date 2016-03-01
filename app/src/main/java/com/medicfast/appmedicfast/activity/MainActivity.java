package com.medicfast.appmedicfast.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.medicfast.appmedicfast.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.setTitle("MedicFast");
    }


    @OnClick(R.id.btnMedicamento)
    public void abrirTelaMedicamento(){
        Intent intent =  new Intent(this, MedicamentoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnMedico)
    public void abrirTelaMedico(){
        Intent intent =  new Intent(this, EspecialidadeActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnPonto)
    public void abrirTelaPontoAtendimento(){
        Intent intent =  new Intent(this, PontoAtendimentoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnSenha)
    public void abrirTelaSenha(){
        Intent intent =  new Intent(this, AjudaActivity.class);
        startActivity(intent);
    }

}
