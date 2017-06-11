package cm.ufop.br.inventariotp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by davidson on 10/06/17.
 */

public abstract class ActivityBase extends Activity {

    protected static Database bancodados;
    protected int action;
    protected Bundle params;

    String fileName = "inventario.txt";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent it = getIntent();
        params = it.getExtras();
        action = it.getIntExtra("action", 0);
        carregarDados();
        defineLayout();
    }

    protected abstract void defineLayout();

    protected void carregarDados() {
        if(bancodados!=null) return;
        try {
            FileInputStream fis = getApplicationContext().openFileInput(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);
            bancodados = (Database) is.readObject();
            is.close();
            fis.close();


        } catch (Exception e) {
            bancodados = new Database();
            Logar("Arquivo de dados nao encontrado. Criando novo");
            e.printStackTrace();
        }
    }

    protected void Logar(String m) {
        Log.e("Inventario", m);
    }

    protected void salvarDados() {
        try {
            FileOutputStream fos = getApplicationContext().openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(this.bancodados);
            os.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void encerrar() {
        finish();
    }

    public void hideTitle() {
        try {
            ((View) findViewById(android.R.id.title).getParent())
                    .setVisibility(View.GONE);
        } catch (Exception e) {
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    }

    public void showTitle() {
        try {
            ((View) findViewById(android.R.id.title).getParent())
                    .setVisibility(View.VISIBLE);
        } catch (Exception e) {
        }
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}
