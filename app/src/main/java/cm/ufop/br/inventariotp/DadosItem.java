package cm.ufop.br.inventariotp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by davidson on 10/06/17.
 */

public class DadosItem extends ActivityBase {

    private EditText codigo, descricao, local, data;
    private Switch status;
    private Item item;

    @Override
    protected void defineLayout() {
        setContentView(R.layout.dados_item);
        codigo = (EditText) findViewById(R.id.codigo_add);
        descricao = (EditText) findViewById(R.id.descricao_add);
        local = (EditText) findViewById(R.id.local_add);
        status = (Switch) findViewById(R.id.status_add);
        data = (EditText) findViewById(R.id.data_add);
        data.setText(Util.DateToString(new Date()));

        if (action == Item.ACTION_EDIT) {
            item = (Item) params.get("item");
            codigo.setText(String.valueOf(item.getCode()));
            descricao.setText(item.getDescription());
            local.setText(item.getLocalization());
            status.setText(item.getStatus());
            data.setText(Util.DateToString(item.getDateInventory()));

        }

    }

    private void atualizarObjeto() {
        item.setCode(Integer.parseInt(codigo.getText().toString()));
        item.setDateInventory(Util.stringToDate(data.getText().toString(), "dd/MM/yyyy"));
        item.setDescription(descricao.getText().toString());
        item.setLocalization(local.getText().toString());
        String stat = status.isActivated() ? "ATIVO" : "INATIVO";
        item.setStatus(stat);
    }


    public void save_next(View v) {

        //Integer codigo, String descricao, String local, String status, Date datainv, Callback onSave
        String stat = status.isActivated() ? "ATIVO" : "INATIVO";
        Date dt = Util.stringToDate(data.getText().toString(), "dd/MM/yyyy");
        try {
            if (action == Item.ACTION_ADD) {
                bancodados.adicionar(codigo.getText().toString(), descricao.getText().toString(), local.getText().toString(), stat, dt, new Callback() {
                    @Override
                    public void executar() {
                        codigo.setText(String.valueOf(Integer.valueOf(codigo.getText().toString())+1));
                        descricao.setText("");
                        descricao.requestFocus();
                        data.setText(Util.DateToString(new Date()));

                    }
                });
                salvarDados();
                Toast.makeText(this, "Dados registrados", Toast.LENGTH_SHORT).show();
            } else if (action == Item.ACTION_EDIT) {
                atualizarObjeto();
                bancodados.salvar(item);
                salvarDados();
                Toast.makeText(this, "Dados atualizados", Toast.LENGTH_SHORT).show();
            }

        } catch (ItemException ee) {
            //ee.printStackTrace();
            Toast.makeText(this, ee.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    public void save_back(View v) {
        String stat = status.isActivated() ? "ATIVO" : "INATIVO";
        Date dt = Util.stringToDate(data.getText().toString(), "EEE MMM d HH:mm:ss zz yyyy");
        try {
            if (action == Item.ACTION_ADD) {
                bancodados.adicionar(codigo.getText().toString(), descricao.getText().toString(), local.getText().toString(), stat, dt, new Callback() {
                    @Override
                    public void executar() {
                        salvarDados();
                        encerrar();
                    }
                });
            } else if (action == Item.ACTION_EDIT) {
                atualizarObjeto();
                bancodados.salvar(item);
                salvarDados();
                Toast.makeText(this, "Dados atualizados", Toast.LENGTH_SHORT).show();
                encerrar();
            }

        } catch (ItemException ee) {
            //ee.printStackTrace();
            Toast.makeText(this, ee.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void back(View v) {
        encerrar();
    }

}
