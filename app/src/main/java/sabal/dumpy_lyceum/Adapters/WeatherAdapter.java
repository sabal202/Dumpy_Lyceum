package sabal.dumpy_lyceum.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sabal.dumpy_lyceum.DTOs.WeatherDTO;
import sabal.dumpy_lyceum.NewDetailActivity;
import sabal.dumpy_lyceum.R;

import static sabal.dumpy_lyceum.Constants.EXTRA_TEXT;
import static sabal.dumpy_lyceum.Constants.EXTRA_TITLE;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherHolder> {

    private List<WeatherDTO> news = new ArrayList<>();

    static class WeatherHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView newTitle;
        TextView newText;
        View mView;
        WeatherHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.weather_cv);
            newTitle = (TextView) itemView.findViewById(R.id.weather_title);
            newText = (TextView) itemView.findViewById(R.id.weather_text);
            mView = itemView;
        }
    }


    public WeatherAdapter(List<WeatherDTO> news) {
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
        newViewHolder.newText.setText("Humidity:   "+ news.get(i).getMain().getHumidity() + "%" +
                                      "\nTemperature:   " + news.get(i).getMain().getTemp());
        newViewHolder.mView.setOnClickListener(view -> {
            Context context = view.getContext();
            Intent intent = new Intent(context, NewDetailActivity.class);
            intent.putExtra(EXTRA_TITLE, newViewHolder.newTitle.getText());
            intent.putExtra(EXTRA_TEXT, newViewHolder.newText.getText());
            context.startActivity(intent);

        });
    }

    public void setData(List<WeatherDTO> data) {
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
