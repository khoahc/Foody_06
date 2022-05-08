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

import hcmute.edu.vn.nhom6.foody_06.Modal.Cart;
import hcmute.edu.vn.nhom6.foody_06.Modal.CartItem;
import hcmute.edu.vn.nhom6.foody_06.Modal.Food;
import hcmute.edu.vn.nhom6.foody_06.Modal.Store;
import hcmute.edu.vn.nhom6.foody_06.R;

public class CartAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<CartItem> cartList;

    public CartAdapter(Context context, int layout, List<CartItem> cartList) {
        this.context = context;
        this.layout = layout;
        this.cartList = cartList;
    }

    @Override
    public int getCount() {
        return cartList.size();
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
        txtNameStore.setText(cartList.get(i).getNameStore());

        TextView txtTotalPrice = (TextView) view.findViewById(R.id.textViewTotalPrice);
        int totalPrice = cartList.get(i).getTotalPrice().intValue();
        txtTotalPrice.setText(totalPrice + " VNĐ");

        TextView textViewTime = (TextView) view.findViewById(R.id.textViewTime);
        textViewTime.setText(cartList.get(i).getTimeCreate());

        return view;
    }
}
