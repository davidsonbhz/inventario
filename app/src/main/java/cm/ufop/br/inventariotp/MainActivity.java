package cm.ufop.br.inventariotp;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends ActivityBase {


    @Override
    protected void defineLayout() {
        setContentView(R.layout.activity_main);

        try {
            /*
            bancodados.adicionar("1001", "cadeira", "c304", "ativo", new Date(), null);
            bancodados.adicionar("1002", "cadeira", "c304", "ativo", new Date(), null);
            bancodados.adicionar("1003", "mesa", "c304", "ativo", new Date(), null);
            bancodados.adicionar("1004", "projetor", "c304", "ativo", new Date(), null);
            bancodados.adicionar("1005", "mouse", "c304", "ativo", new Date(), null);
*/
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void adicionar(View v) {
        Intent it = new Intent(this, DadosItem.class);
        //startActivity(it);
        it.putExtra("action", Item.ACTION_ADD);
        startActivityForResult(it, Item.ACTION_ADD);
    }

    public void listar(View v) {
        Intent it = new Intent(this, ListarItens.class);
        it.putExtra("action", Item.ACTION_EDIT);
        startActivity(it);
    }

    public void inventariar(View v) {

        try {
            //Start barcode scanner
            Intent it = new Intent("com.google.zxing.client.android.SCAN");
            startActivityForResult(it, Item.ACTION_READ);

        } catch(ActivityNotFoundException e) {
            //Barcode scanner is not installed
            Toast.makeText(this, "Please install Barcode Scanner from Play Store.", Toast.LENGTH_SHORT).show();

            //Optional: direct the user to Barcode Scanner's page on Play Store
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.zxing.client.android")));
            } catch (ActivityNotFoundException e2) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" +
                        "com.google.zxing.client.android")));
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== Activity.RESULT_OK) {
            String result = data.getStringExtra("SCAN_RESULT");
            String format = data.getStringExtra("SCAN_RESULT_FORMAT");
            //Toast.makeText(this, "Result: " + result + "\nFormat: " + format, Toast.LENGTH_SHORT).show();

            Item found = bancodados.findByCode(result);
            if(found==null) {
                Toast.makeText(this, "Item nao encontrado", Toast.LENGTH_SHORT).show();
            } else {
                found.setDateInventory(new Date());
                bancodados.salvar(found);
                salvarDados();
                Toast.makeText(this, "Item "+found.getDescription()+" inventariado com sucesso!", Toast.LENGTH_SHORT).show();
            }

        }

    }
}
