package hcmute.edu.vn.nhom6.foody_06.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hcmute.edu.vn.nhom6.foody_06.Modal.Store;
import hcmute.edu.vn.nhom6.foody_06.MyFunction.MyFunction;
import hcmute.edu.vn.nhom6.foody_06.R;

public class StoreAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Store> storeList;

    public StoreAdapter(Context context, int layout, List<Store> storeList) {
        this.context = context;
        this.layout = layout;
        this.storeList = storeList;
    }

    @Override
    public int getCount() {
        return storeList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        TextView txtNameStore = (TextView) view.findViewById(R.id.textViewNameStore);
        txtNameStore.setText(storeList.get(i).getName());

        ImageView imgImageStore = (ImageView) view.findViewById(R.id.imageViewStore);
        Bitmap bmImageStore = MyFunction.decodeImg(storeList.get(i).getImage());
        imgImageStore.setImageBitmap(bmImageStore);

        return view;
    }
}
