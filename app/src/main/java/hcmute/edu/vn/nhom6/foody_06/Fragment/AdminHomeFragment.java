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
import hcmute.edu.vn.nhom6.foody_06.Data.DatabaseAccess;
import hcmute.edu.vn.nhom6.foody_06.Interface.TransactStore;
import hcmute.edu.vn.nhom6.foody_06.Modal.Store;
import hcmute.edu.vn.nhom6.foody_06.R;
import hcmute.edu.vn.nhom6.foody_06.databinding.FragmentAdminHomeBinding;

public class AdminHomeFragment extends Fragment {

    GridView listViewStore;
    List<Store> listStore = new ArrayList<Store>();
    Button btnAddStore;
    private FragmentAdminHomeBinding binding;
    TransactStore transactStore;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        super.onCreate(savedInstanceState);
        transactStore = (TransactStore) getActivity();

        binding = FragmentAdminHomeBinding.inflate(inflater, container, false);

        listViewStore = binding.getRoot().findViewById(R.id.gridView);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
        databaseAccess.open();

        listStore = databaseAccess.getListStore();

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

}