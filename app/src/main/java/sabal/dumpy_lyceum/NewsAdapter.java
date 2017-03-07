package sabal.dumpy_lyceum;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewViewHolder> {
    private static final String TAG = "CustomAdapter";

    private List<New> news = new ArrayList<>();

    public static class NewViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView newTitle;
        TextView newText;
        //TextView newDate;

        NewViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            newTitle = (TextView) itemView.findViewById(R.id.weather_title);
            newText = (TextView) itemView.findViewById(R.id.weather_text);
            //newDate = (TextView)itemView.findViewById(R.id.new_date);
        }
    }


    NewsAdapter(List<New> news) {
        this.news = news;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public NewViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        final NewViewHolder holder = new NewViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(NewViewHolder newViewHolder, int i) {
        newViewHolder.newTitle.setText(news.get(i).getTitle());
        newViewHolder.newText.setText(news.get(i).getIntrotext());
        //newViewHolder.newDate.setText("03.03.2017");
    }

    public void setData(List<New> data) {
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