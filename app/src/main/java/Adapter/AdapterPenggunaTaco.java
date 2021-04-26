package Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import Model.ModelTaco;
import com.example.teco.R;

import java.util.List;

public class AdapterPenggunaTaco extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelTaco> item;

    public AdapterPenggunaTaco(Activity activity, List<ModelTaco> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.content_Taco, null);


        TextView namaTaco = (TextView) convertView.findViewById(R.id.txtNamaTaco);
        TextView Tanggal      = (TextView) convertView.findViewById(R.id.edtTanggal);


        namaTaco.setText(item.get(position).getNamaTaco());
        Tanggal.setText(item.get(position).getTanggal());
        return convertView;
    }
}