package sabal.dumpy_lyceum;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


public class WeatherFragment extends Fragment {
    private static final int LAYOUT = R.layout.fragment_weather;
    private List<WeatherDTO> data;
    private String title;
    protected Context context;
    protected View view;
    protected RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new WeatherFragment.GetJSON().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //CardView cv = (RecyclerView) view.findViewById(R.id.recycleView);
        //cv.setLayoutManager(new LinearLayoutManager(context));

        //adapter = new WeatherAdapter(data);
        //rv.setAdapter(adapter);
        view = inflater.inflate(LAYOUT, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycleView);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.

        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        WeatherAdapter adapter = new WeatherAdapter(data);
        mRecyclerView.setAdapter(adapter);
        return view;

    }


    private class GetJSON extends AsyncTask<String, String, WeatherDTO> {
        ProgressDialog pdLoading = new ProgressDialog(getActivity());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected WeatherDTO doInBackground(String... strings) {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            WeatherDTO response = template.getForObject(Constants.URL.CURRENT_WEATHER, WeatherDTO.class);
            
            return response;
        }

        @Override
        protected void onPostExecute(WeatherDTO result) {
            List<WeatherDTO> list = new ArrayList<>();

            list.add(result);
            WeatherAdapter adapter = new WeatherAdapter(list);
            adapter.setData(list);
            adapter.notifyDataSetChanged();
            mRecyclerView.setAdapter(adapter);
            pdLoading.dismiss();
        }
    }
}
