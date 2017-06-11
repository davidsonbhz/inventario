package cm.ufop.br.inventariotp;

import android.provider.ContactsContract;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by davidson on 10/06/17.
 */

public class Database implements Serializable {

    public ArrayList<Item> inventario;
    public String filename;

    public Database() {
        this.inventario = new ArrayList<Item>();
    }

    public void adicionar(String codigo, String descricao, String local, String status, Date datainv, Callback onSave) throws ItemException {
        Log.e("db", "adicionando "+codigo+" "+descricao);
        if(codigo.isEmpty()) {
            throw new ItemException("Por gentileza, não teste minha paciência! defina o codigo!!");
        }

        Integer cod = Integer.parseInt(codigo);
        Item n = new Item(cod, descricao, local, status, datainv);

        if(codigo==null || cod==0) {
            throw new ItemException("Codigo invalido!");
        }
        if(descricao.isEmpty()) {
            throw new ItemException("A descricao é obrigatória");
        }


        //verifica se o item ja existe
        if(checaExistencia(n)) {
            throw new ItemException("Item ja cadastrado!");
        }

        inventario.add(n);

        if(onSave!=null) {
            onSave.executar();
        }

    }

    public boolean checaExistencia(Item it) {
        for(Item i: inventario) {
            if(i.getCode()==it.getCode()) {
                return true;
            }
        }
        return false;
    }

    public void salvar(Item item) {
        for(int i=0;i<inventario.size();i++) {
            if(inventario.get(i).getCode()==item.getCode()) {
                inventario.set(i, item);
                Log.e("database", "Salvando item: "+item);
                return;
            }
        }
    }

    public boolean isEmpty() {
        if(inventario==null) return true;
        return inventario.isEmpty();
    }

    public Item findByCode(String cod) {
        int c = Integer.valueOf(cod);
        for(int i=0;i<inventario.size();i++) {
            if(inventario.get(i).getCode()==c) {
                return inventario.get(i);
            }
        }
        return null;
    }
}
