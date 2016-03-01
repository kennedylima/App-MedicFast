package com.medicfast.appmedicfast.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.medicfast.appmedicfast.R;
import com.medicfast.appmedicfast.api.ApiWeb;
import com.medicfast.appmedicfast.model.Especialidade;
import com.medicfast.appmedicfast.model.PontoAtendimento;
import com.medicfast.appmedicfast.model.Senha;
import com.medicfast.appmedicfast.model.TipoOcorrencia;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SenhaActivity extends AppCompatActivity {

    @Bind(R.id.spinnerEspecialidade)
    Spinner especialidadeMedica;

    @Bind(R.id.spinnerOcorrencia)
    Spinner ocorrencia;

    @Bind(R.id.sintomas)
    EditText sintomas;

    public static PontoAtendimento  ponto ;
    public static ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senha);
        ButterKnife.bind(this);
        this.setTitle("Senha");

        progressDialog = ProgressDialog.show(this, "Aguarde ...", "Carregando as Informações...", true);
        progressDialog.setCancelable(true);
        progressDialog.show();

        Intent intent = getIntent();
        ponto =(PontoAtendimento) intent.getSerializableExtra("Ponto");


        ApiWeb.getRotas().listarEspecialidadePorPonto(ponto.getId(), new Callback<List<Especialidade>>() {
            @Override
            public void success(List<Especialidade> especialidade, Response response) {
                ArrayAdapter<Especialidade> adapter = new ArrayAdapter<Especialidade>(SenhaActivity.this, android.R.layout.simple_list_item_activated_1, especialidade);
                especialidadeMedica.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(SenhaActivity.this, "Não foi possivel se conectar ao servidor", Toast.LENGTH_SHORT).show();
            }
        });

        ApiWeb.getRotas().listarOcorrencia(new Callback<List<TipoOcorrencia>>() {
            @Override
            public void success(List<TipoOcorrencia> tipoOcorrencias, Response response) {
                ArrayAdapter<TipoOcorrencia> adapter = new ArrayAdapter<TipoOcorrencia>(SenhaActivity.this, android.R.layout.simple_list_item_activated_1, tipoOcorrencias);
                ocorrencia.setAdapter(adapter);
                fecharTelaDeCarregarConversa();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(SenhaActivity.this, "Não foi possivel se conectar ao servidor", Toast.LENGTH_SHORT).show();
            }
        });



    }

    public static void fecharTelaDeCarregarConversa(){
        //verifica se a tela está aberta, se sim então a janela será fechada
        if(progressDialog.isShowing()){
            progressDialog.dismiss();}
    }

    @OnClick(R.id.gerarSenha)
    public void gerarSenha(){
        if(ocorrencia.getSelectedItem()!=null || especialidadeMedica.getSelectedItem() != null) {

            AlertDialog alertDialog = new AlertDialog.Builder(SenhaActivity.this).create();
            alertDialog.setTitle("Atenção");
            alertDialog.setMessage("As informações estão corretas ?");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            progressDialog = ProgressDialog.show(SenhaActivity.this, "Aguarde ...", "Gerando Senha de Atendimento...", true);
                            progressDialog.setCancelable(true);
                            progressDialog.show();
                            final Senha senha1 = new Senha();
                            senha1.setPontoAtendimento((PontoAtendimento) ponto);
                            senha1.setOcorrencia((TipoOcorrencia) ocorrencia.getSelectedItem());
                            senha1.setEspecialidade((Especialidade) especialidadeMedica.getSelectedItem());
                            senha1.setSintoma(sintomas.getText().toString());
                            senha1.setChamado(false);

                            ApiWeb.getRotas().salvarSenha(senha1, new Callback<Senha>() {
                                @Override
                                public void success(Senha senha, Response response) {
                                    //System.out.println("---->"+senha.getNumero());
                                    //Toast.makeText(SenhaActivity.this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();



                                    Intent intent = new Intent(SenhaActivity.this, ExibeSenhaActivity.class);
                                    intent.putExtra("senha", senha);
                                    finish();
                                    startActivity(intent);

                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    Toast.makeText(SenhaActivity.this, "Falha ao salvar", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
            alertDialog.show();


        }else if (ocorrencia.getSelectedItem()==null){
            AlertDialog alertDialog = new AlertDialog.Builder(SenhaActivity.this).create();
            alertDialog.setTitle("Atenção");
            alertDialog.setMessage("Selecione um tipo de Ocorrência !");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

        }else if (especialidadeMedica.getSelectedItem()==null){
            AlertDialog alertDialog = new AlertDialog.Builder(SenhaActivity.this).create();
            alertDialog.setTitle("Atenção");
            alertDialog.setMessage("Selecione uma Especialidade Médica !");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }


}
