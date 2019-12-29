package kustura.superheroesdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import kustura.superheroesdatabase.apiServiceDto.Result;

public class MainActivity extends AppCompatActivity implements RecycleAdapter.ItemClickInterface {
    private static final String TAG = "MainActivity";

    private Resources res;
    private RecycleAdapter recycleAdapter;
    private HttpApiService httpApiService;
    private SearchView searchView;

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
        if (item.getItemId() == R.id.clear) {
            ClearSearch();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search Heroes");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                doMySearch(query);
                ClearSearchInput();

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
        ClearSearchInput();

    }

    private void ClearSearchInput() {
        searchView.clearFocus();
        searchView.setQuery("", false);

    }

}
