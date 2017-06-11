package cm.ufop.br.inventariotp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by davidson on 10/06/17.
 */

public class AdapterItem extends BaseAdapter {

    private ArrayList<Item> itens;
    private Context context;

    public AdapterItem(ArrayList<Item> itens, Context context) {
        this.itens = itens;
        this.context = context;
        Log.e("constructor", String.valueOf(itens.size()));

    }




    @Override
    public int getCount() {
        Log.e("getcount", String.valueOf(itens.size()));

        return itens.size();
    }

    @Override
    public Object getItem(int i) {
        Log.e("item i", String.valueOf(i));

        return itens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return itens.get(i).getCode();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Item item = itens.get(position);

        Log.e("adapter", item.getDescription());

        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);

        setText(rowView, String.valueOf(item.getCode()), R.id.codigo_view);
        setText(rowView, item.getDescription(), R.id.descricao_view);
        setText(rowView, Util.DateToString(item.getDateInventory()) , R.id.data_view);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img_view);
        // change the icon for Windows and iPhone


        if (itens.get(position).isDelayed()) {
            imageView.setImageResource(R.drawable.exclamacao);
        } else {
            imageView.setImageResource(R.drawable.checked);
        }

        return rowView;
    }


    private void setText(View v, String t, int id) {
        TextView tt = (TextView) v.findViewById(id);
        tt.setText(t);
    }
}
