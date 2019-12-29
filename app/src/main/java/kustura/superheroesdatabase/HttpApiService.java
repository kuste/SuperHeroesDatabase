package kustura.superheroesdatabase;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import kustura.superheroesdatabase.apiServiceDto.ApiResponse;
import kustura.superheroesdatabase.apiServiceDto.Result;

public class HttpApiService extends AsyncTask<String, Void, List<Result>> {

    private static final String REQUEST_METHOD = "GET";
    private static final int READ_TIMEOUT = 15000;
    private List<Result> result;
    private RecycleAdapter recycleAdapter;

    HttpApiService(RecycleAdapter recycleAdapter) {
        this.recycleAdapter = recycleAdapter;
    }

    @Override
    protected List<Result> doInBackground(String... strings) {
        String stringUrl = strings[0];
        try {

            URL url = new URL(stringUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            int CONNECTION_TIMEOUT = 15000;
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            connection.connect();

            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());

            BufferedReader reader = new BufferedReader(streamReader);

            ApiResponse response = new Gson().fromJson(reader, ApiResponse.class);

            reader.close();
            streamReader.close();

            return response.getResults();

        } catch (Exception e) {
            if (e.getMessage() != null) {
                Log.d("Error", e.getMessage());
            }
            e.getStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(List<Result> apiResponse) {

        recycleAdapter.setResults(apiResponse);
        recycleAdapter.notifyDataSetChanged();
    }

    public List<Result> getResult() {
        return result;
    }

    public void Clear() {
        if(recycleAdapter.getItemCount() > 0 || recycleAdapter != null) {
            recycleAdapter.setResults(null);
        }else{
            return;
        }
    }

        public void setResult (List < Result > result) {
            this.result = result;
        }
    }
