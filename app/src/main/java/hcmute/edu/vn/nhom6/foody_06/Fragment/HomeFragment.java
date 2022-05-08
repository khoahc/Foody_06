package hcmute.edu.vn.nhom6.foody_06.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.nhom6.foody_06.Activity.MainActivity;
import hcmute.edu.vn.nhom6.foody_06.Activity.SearchActivity;
import hcmute.edu.vn.nhom6.foody_06.Activity.StoreDetailActivity;
import hcmute.edu.vn.nhom6.foody_06.Adapter.StoreAdapter;
import hcmute.edu.vn.nhom6.foody_06.Data.DatabaseAccess;
import hcmute.edu.vn.nhom6.foody_06.Interface.TransactStore;
import hcmute.edu.vn.nhom6.foody_06.Interface.TransactUser;
import hcmute.edu.vn.nhom6.foody_06.Modal.Store;
import hcmute.edu.vn.nhom6.foody_06.Modal.User;
import hcmute.edu.vn.nhom6.foody_06.R;
import hcmute.edu.vn.nhom6.foody_06.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    GridView listViewStore;
    List<Store> listStore = new ArrayList<Store>();
    private FragmentHomeBinding binding;
    TransactStore transactStore;
    TransactUser transactUser;
    EditText editTextSearch;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        super.onCreate(savedInstanceState);

        //get data store
        transactStore = (TransactStore) getActivity();

        //get info user
        transactUser = (TransactUser) getActivity();
        User user = transactUser.getDataUser();

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        readData();

        addControls();

        listViewStore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(getActivity(), StoreDetailActivity.class);
                //put info store
                intent.putExtra("infoStore", listStore.get(position));
                //put info user
                intent.putExtra("infoUser", user);
                startActivity(intent);
            }
        });

        //search
        editTextSearch = binding.getRoot().findViewById(R.id.editTextSearch);
        editTextSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                //put info user
                intent.putExtra("infoUser", user);
                startActivity(intent);
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

    private void readData() {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
        databaseAccess.open();

        listStore = databaseAccess.getListStore();
    }

    private void addControls(){
        listViewStore = binding.getRoot().findViewById(R.id.gridView);
        StoreAdapter adapter = new StoreAdapter(
                HomeFragment.this.getActivity(),
                R.layout.viewholder_store,
                listStore
        );

        listViewStore.setAdapter(adapter);
    }

}