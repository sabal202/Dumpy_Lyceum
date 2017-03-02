package sabal.dumpy_lyceum;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherHolder> {
    private static final String TAG = "CustomAdapter";

    private List<GetDTO> news = new ArrayList<GetDTO>();

    public static class WeatherHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView newTitle;
        TextView newText;
        //TextView newDate;

        WeatherHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.weather_cv);
            newTitle = (TextView) itemView.findViewById(R.id.weather_title);
            newText = (TextView) itemView.findViewById(R.id.weather_text);
            //newDate = (TextView)itemView.findViewById(R.id.new_date);
        }
    }


    WeatherAdapter(List<GetDTO> news) {
        this.news = news;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public WeatherAdapter.WeatherHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.weather_card, viewGroup, false);
        final WeatherAdapter.WeatherHolder holder = new WeatherAdapter.WeatherHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(WeatherAdapter.WeatherHolder newViewHolder, int i) {
        newViewHolder.newTitle.setText("Weather");
        newViewHolder.newText.setText("Humidity:   "+String.valueOf((int) news.get(i).humidity) + "\nTemperature:   " + String.valueOf(news.get(i).weather));
        //newViewHolder.newDate.setText("03.03.2017");
    }

    public void setData(List<GetDTO> data) {
        this.news = data;
    }

    @Override
    public int getItemCount() {
        if (news != null) {
            return news.size();
        } else {
            return 0;
        }
    }
}
