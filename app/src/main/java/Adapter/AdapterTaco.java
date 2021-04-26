package Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dwiromadon.myapplication.R;
import com.dwiromadon.myapplication.model.ModelPetshop;
import com.dwiromadon.myapplication.server.BaseURL;

import Model.ModelTaco;
import Model.Model_Taco;
import com.example.teco.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterTaco extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelTaco> item;

    public AdapterTaco(Activity activity, List<ModelTaco> item) {
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
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.content_admin, null);


        TextView namaTaco = (TextView) convertView.findViewById(R.id.txtNamaTaco);
        ImageView gambar     = (ImageView) convertView.findViewById(R.id.gambarPetshop);
        TextView Tanggal      = (TextView) convertView.findViewById(R.id.txtTanggal);

        namaTaco.setText(item.get(position).getNamaTaco());
        Tanggal.setText(item.get(position).getTanggal());

        return convertView;
    }
}
