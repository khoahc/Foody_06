package hcmute.edu.vn.nhom6.foody_06.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

import hcmute.edu.vn.nhom6.foody_06.Activity.StoreDetailActivity;
import hcmute.edu.vn.nhom6.foody_06.Adapter.StoreAdapter;
import hcmute.edu.vn.nhom6.foody_06.Domain.Store;
import hcmute.edu.vn.nhom6.foody_06.Interface.TransactStore;
import hcmute.edu.vn.nhom6.foody_06.R;
import hcmute.edu.vn.nhom6.foody_06.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    GridView listViewStore;
    ArrayList<Store> arrayStore = new ArrayList<Store>();;
    int viTri = 0;
    String selectedItem = "";
    private FragmentHomeBinding binding;
    TransactStore transactStore;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        super.onCreate(savedInstanceState);
        transactStore = (TransactStore) getActivity();

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        listViewStore = binding.getRoot().findViewById(R.id.gridView);

        addArrayStore();

        StoreAdapter adapter = new StoreAdapter(
            HomeFragment.this.getActivity(),
            R.layout.viewholder_store,
            arrayStore
        );

        listViewStore.setAdapter(adapter);

        listViewStore.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.d("khoa", ""+ position);
                transactStore.DataStore(arrayStore.get(position));
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

    private void addArrayStore () {
        arrayStore.add(new Store("Quán cơm chiên Đình Long", "quán nấu ngon", R.drawable.comchien));
        arrayStore.add(new Store("Quán gà chiên Đỉnh Ký", "quán ok", R.drawable.gachien));
        arrayStore.add(new Store("Quán gà xối mỡ Thiên An", "tạm được", R.drawable.gaxoimo));
        arrayStore.add(new Store("Quán hải sản Thủy Tề", "không ngon cho lắm", R.drawable.haisan));
        arrayStore.add(new Store("Quán phở Bình Minh", "quán thoáng mát", R.drawable.pho));
        arrayStore.add(new Store("Quán cơm Phúc Lộc Thọ", "lần đầu ăn ở đây khá là ngon", R.drawable.quancom));
    }

//    @Override
//    public void DataStore(Store store) {
////        StoreDetailFragment fragmentStudentInfo = (StoreDetailFragment)
////                getParentFragmentManager().findFragmentById(R.id.fragmentInfoStore);
//
//
//
//    }

}