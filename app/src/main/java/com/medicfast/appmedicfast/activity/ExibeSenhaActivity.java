package com.medicfast.appmedicfast.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.medicfast.appmedicfast.R;
import com.medicfast.appmedicfast.api.ApiWeb;
import com.medicfast.appmedicfast.model.Senha;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExibeSenhaActivity extends AppCompatActivity {

    @Bind(R.id.numero)
    TextView numero;

    @Bind(R.id.local)
    TextView local;

    @Bind(R.id.especialidade)
    TextView especialidade;

    @Bind(R.id.ocorrencia)
    TextView ocorrencia;

    @Bind(R.id.sintoma)
    TextView sintoma;

    @Bind(R.id.data)
    TextView data;

    @Bind(R.id.horario)
    TextView horario;

    @Bind(R.id.salvarSenha)
    Button salvarSenha;

    public boolean usuarioSalvouASenha = false;
    static Senha senha;

    // Usado para formatar as Datas
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibe_senha);
        ButterKnife.bind(this);
        this.setTitle("");
        Intent intent = getIntent();
        senha =(Senha) intent.getSerializableExtra("senha");

        Time hora = Time.valueOf(senha.getHorario());
        Calendar cal = Calendar.getInstance();
        cal.setTime(hora);
        cal.add(Calendar.MINUTE,20);
        hora = new java.sql.Time(cal.getTime().getTime());
        System.out.println("Calendar hora Ori -> " + cal);
        //cal.add(Calendar.HOUR,hora.getTime());
        System.out.println("Calendar hora -> " + hora);
        senha.setHorario(hora.toString());
        local.setText(senha.getPontoAtendimento().getNome());
        especialidade.setText(senha.getEspecialidade().getNome());
        sintoma.setText(senha.getSintoma());
        horario.setText(senha.getHorario());
        ocorrencia.setText(senha.getOcorrencia().getDescricao());

        String aux = senha.getData();
        try {
            date = df.parse(aux);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        data.setText(df1.format(date));


        numero.setText(senha.getNumero().toString());
        SenhaActivity.fecharTelaDeCarregarConversa();



    }

    @OnClick(R.id.salvarSenha)
    public void salvarSenha(){
        usuarioSalvouASenha = true;
        AlertDialog alertDialog = new AlertDialog.Builder(ExibeSenhaActivity.this).create();
        alertDialog.setTitle("Atenção");
        alertDialog.setMessage("Ao chegar no estabelecimento, mostre ao atendente a sua senha, que foi salva no seu aparelho !");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        salvarSenha.setEnabled(false);
                        //Bitmap bitmap = takeScreenshot();
                        //saveBitmap(bitmap);
                        scree();
                    }
                });


        alertDialog.show();
    }

    @Override
    public void onBackPressed(){
        if(usuarioSalvouASenha == false) {
            Toast.makeText(ExibeSenhaActivity.this, "Salve sua senha ! ", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(ExibeSenhaActivity.this, "Obrigado por utilizar o MedicFast", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ExibeSenhaActivity.this, MainActivity.class);
            finish();
            startActivity(intent);

        }
    }

    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
        File imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot44545454.png");
        FileOutputStream fos;
        try
        {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        }
        catch (FileNotFoundException e)
        {
            Log.e("GREC", e.getMessage(), e);
        }
        catch (IOException e)
        {
            Log.e("GREC", e.getMessage(), e);
        }
    }

    public void scree(){
        View view =  findViewById(android.R.id.content);
        view.getRootView();
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state))
        {
            File picDir  = new File(Environment.getExternalStorageDirectory()+ "/MedicFast");
            if (!picDir.exists())
            {
                picDir.mkdir();
            }
            view.setDrawingCacheEnabled(true);
            view.buildDrawingCache(true);
            Bitmap bitmap = view.getDrawingCache();
//          Date date = new Date();
            String fileName = "Senha - "+senha.getNumero()+ ".jpg";
            File picFile = new File(picDir + "/" + fileName);
            try
            {
                picFile.createNewFile();
                FileOutputStream picOut = new FileOutputStream(picFile);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), (int)(bitmap.getHeight()/1.2));//Optional
                boolean saved = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, picOut);
                if (saved)
                {
                    Toast.makeText(getApplicationContext(), "Abra a galeria de imagens para visualizar sua senha, na pasta 'MedicFast' ", Toast.LENGTH_LONG).show();
                } else
                {
                    Toast.makeText(getApplicationContext(), "Não foi possivel salvar sua senha !", Toast.LENGTH_SHORT).show();
                }
                picOut.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            view.destroyDrawingCache();
        } else {


        }
    }
}
