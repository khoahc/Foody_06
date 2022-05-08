package hcmute.edu.vn.nhom6.foody_06.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.nhom6.foody_06.Adapter.StoreAdapter;
import hcmute.edu.vn.nhom6.foody_06.Data.DatabaseAccess;
import hcmute.edu.vn.nhom6.foody_06.Fragment.HomeFragment;
import hcmute.edu.vn.nhom6.foody_06.Modal.Store;
import hcmute.edu.vn.nhom6.foody_06.Modal.User;
import hcmute.edu.vn.nhom6.foody_06.R;

public class SearchActivity extends AppCompatActivity {
    TextView editTextSearch;
    List<Store> listStoreSearch = new ArrayList<Store>();
    ListView listViewStore;
    User user;
    StoreAdapter adapterStore;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        intent = new Intent(getIntent());
        user = (User) intent.getSerializableExtra("infoUser");

        listViewStore = (ListView) findViewById(R.id.listViewStoreSearch);
        editTextSearch = (TextView) findViewById(R.id.editTextSearch);
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    return true;
                }
                return false;
            }
        });

        listViewStore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(SearchActivity.this, StoreDetailActivity.class);
                //put info store
                intent.putExtra("infoStore", listStoreSearch.get(position));
                //put info user
                intent.putExtra("infoUser", user);
                startActivity(intent);
            }
        });

    }

    private void performSearch() {
        editTextSearch.clearFocus();
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(editTextSearch.getWindowToken(), 0);

        //...perform search
        String keyword = editTextSearch.getText().toString();
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        listStoreSearch = databaseAccess.findStore(keyword);
        Log.d("numStore", listStoreSearch.size() + "");

        adapterStore = new StoreAdapter(
                SearchActivity.this,
                R.layout.viewholder_store_search,
                listStoreSearch
        );
        adapterStore.notifyDataSetChanged();
        listViewStore.setAdapter(adapterStore);

    }
}