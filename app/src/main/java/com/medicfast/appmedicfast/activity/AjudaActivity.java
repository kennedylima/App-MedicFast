package com.medicfast.appmedicfast.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.medicfast.appmedicfast.R;

import butterknife.Bind;


public class AjudaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda);
        this.setTitle("Ajuda");
    }

    /*android:text="
 Medicamento



 Especialidade Médica


  Ponto de Atendimento


  " Medicamento \n"+
                "- Para visualizar os medicamentos disponiveis pela rede de saúde brasileira, utilize a opção medicamento.\n"+
                "- Selecione o medicamento que deseja.\n"+
                "- Após selecionar o medicamento, o aplicativo irá lhe mostrar os locais onde contem aquele medicamento disponivel.\n"+
                "\n"+
                "Especialidade Médica \n"+
                "Para visuzalizar os médicos disponiveis para atendimento rede de saúde brasileira, escolha a opção Especialidade Médica \n"+
                "Selecione a opção que melhor atende-lo. , ao abrir a tela de senha.\n"+
                "Em seguida selecione o ponto de atendimento mais próximo da sua localização de acordo com o endereço \n"+
                "Escolha uma especialidade e um tipo de ocorrência, caso necessário descreva os sintomas e selecione gerar senha.\n"+
                "\n"
            */


}
