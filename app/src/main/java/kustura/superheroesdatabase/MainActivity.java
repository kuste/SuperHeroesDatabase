package kustura.superheroesdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import kustura.superheroesdatabase.apiServiceDto.Result;

public class MainActivity extends AppCompatActivity implements RecycleAdapter.ItemClickInterface {
    private static final String TAG = "MainActivity";

    private Resources res;
    private RecycleAdapter recycleAdapter;
    private HttpApiService httpApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_layout);

    }


    private HttpApiService CreateHttpService(RecycleAdapter recycleAdapter) {
        if (httpApiService == null) {
            return httpApiService = new HttpApiService(recycleAdapter);
        }
        httpApiService.cancel(true);
        return httpApiService = new HttpApiService(recycleAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear:
                ClearSearch();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search Heroes");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                doMySearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    private void ClearSearch() {
        if (httpApiService != null) {
            httpApiService.Clear();
            setContentView(R.layout.title_layout);
        }

    }

    private void doMySearch(String query) {
        setContentView(R.layout.activity_main);
        res = getResources();
        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recycleAdapter = new RecycleAdapter(this);
        recycleAdapter.setClickListener(this);
        recyclerView.setAdapter(recycleAdapter);
        CreateHttpService(recycleAdapter);
        httpApiService.execute(res.getString(R.string.apiUrl) + query);
    }


    @Override
    public void onItemClick(View view, int position) {
        Result item = recycleAdapter.getResult(position);
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("Result", item);
        startActivity(intent);

    }


}
