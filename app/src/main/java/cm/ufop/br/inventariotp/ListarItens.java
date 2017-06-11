package cm.ufop.br.inventariotp;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by davidson on 10/06/17.
 */

public class ListarItens extends ActivityBase {


    AdapterItem ad;

    @Override
    protected void defineLayout() {
        setContentView(R.layout.activity_list);
        ListView lv = (ListView) findViewById(R.id.lista_itens);

        if(bancodados.isEmpty()) {
            Toast.makeText(this, "Banco de dados vazio!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        ad = new AdapterItem(bancodados.inventario, this);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editarItem(bancodados.inventario.get(i));
            }
        });

        showTitle();

    }

    public void editarItem(Item item) {
        Intent it = new Intent(this, DadosItem.class);
        it.putExtra("item", item);
        it.putExtra("action", Item.ACTION_EDIT);
        startActivityForResult(it, Item.ACTION_EDIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ad.notifyDataSetChanged();

    }
}
