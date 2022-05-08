package hcmute.edu.vn.nhom6.foody_06.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.nhom6.foody_06.Adapter.CartAdapter;
import hcmute.edu.vn.nhom6.foody_06.Adapter.StoreAdapter;
import hcmute.edu.vn.nhom6.foody_06.Data.DatabaseAccess;
import hcmute.edu.vn.nhom6.foody_06.Interface.TransactUser;
import hcmute.edu.vn.nhom6.foody_06.Modal.Cart;
import hcmute.edu.vn.nhom6.foody_06.Modal.CartItem;
import hcmute.edu.vn.nhom6.foody_06.Modal.Store;
import hcmute.edu.vn.nhom6.foody_06.Modal.User;
import hcmute.edu.vn.nhom6.foody_06.R;
import hcmute.edu.vn.nhom6.foody_06.databinding.FragmentCartBinding;


public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private ListView listViewCart;
    private List<CartItem> listCart = new ArrayList<CartItem>();
    TransactUser transactUser;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentCartBinding.inflate(inflater, container, false);
        //get info user
        transactUser = (TransactUser) getActivity();
        User user = transactUser.getDataUser();

        readData(user.getId());
        addControls();
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void readData(Integer idUser) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
        databaseAccess.open();

        listCart = databaseAccess.getListStoreOfCartUser(idUser);
    }

    private void addControls(){
        listViewCart = binding.getRoot().findViewById(R.id.listViewOrderHistory);
        CartAdapter adapter = new CartAdapter(
                CartFragment.this.getActivity(),
                R.layout.viewholder_order,
                listCart
        );

        listViewCart.setAdapter(adapter);
    }

}