package hcmute.edu.vn.nhom6.foody_06.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.nhom6.foody_06.Activity.AdminAddStoreActivity;
import hcmute.edu.vn.nhom6.foody_06.Adapter.StoreAdapter;
import hcmute.edu.vn.nhom6.foody_06.Data.DatabaseHelper;
import hcmute.edu.vn.nhom6.foody_06.Modal.Store;
import hcmute.edu.vn.nhom6.foody_06.Interface.TransactStore;
import hcmute.edu.vn.nhom6.foody_06.R;
import hcmute.edu.vn.nhom6.foody_06.databinding.FragmentAdminHomeBinding;

public class AdminHomeFragment extends Fragment {

    GridView listViewStore;
    ArrayList<Store> arrayStore = new ArrayList<Store>();
    Button btnAddStore;
    private FragmentAdminHomeBinding binding;
    TransactStore transactStore;
    DatabaseHelper databaseHelper;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        super.onCreate(savedInstanceState);
        transactStore = (TransactStore) getActivity();

        binding = FragmentAdminHomeBinding.inflate(inflater, container, false);

        listViewStore = binding.getRoot().findViewById(R.id.gridView);

        //addArrayStore();

        databaseHelper = new DatabaseHelper(getActivity());
        databaseHelper.createDefaultStoresIfNeed();
        List<Store> listStore = databaseHelper.getAllStores();

        StoreAdapter adapter = new StoreAdapter(
                AdminHomeFragment.this.getActivity(),
                R.layout.viewholder_store,
                listStore
        );

        //vao them quan an
        btnAddStore = binding.getRoot().findViewById(R.id.buttonAddStore);
        btnAddStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdminAddStoreActivity.class);
                startActivity(intent);
            }
        });

        //gridview quan an
        listViewStore.setAdapter(adapter);

        listViewStore.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                transactStore.DataStore(listStore.get(position));
            }
        });

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

//    private void addArrayStore () {
//        arrayStore.add(new Store("Quán cơm chiên Đình Long", "quán nấu ngon", R.drawable.comchien));
//        arrayStore.add(new Store("Quán gà chiên Đỉnh Ký", "quán ok", R.drawable.gachien));
//        arrayStore.add(new Store("Quán gà xối mỡ Thiên An", "tạm được", R.drawable.gaxoimo));
//        arrayStore.add(new Store("Quán hải sản Thủy Tề", "không ngon cho lắm", R.drawable.haisan));
//        arrayStore.add(new Store("Quán phở Bình Minh", "quán thoáng mát", R.drawable.pho));
//        arrayStore.add(new Store("Quán cơm Phúc Lộc Thọ", "lần đầu ăn ở đây khá là ngon", R.drawable.quancom));
//    }

}