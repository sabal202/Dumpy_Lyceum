package sabal.dumpy_lyceum;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.NewViewHolder> {
    private static final String TAG = "CustomAdapter";
    CardView cv;
    private List<New> news;


    public static class NewViewHolder extends RecyclerView.ViewHolder {


        TextView newTitle;
        TextView newText;
        TextView newDate;

        NewViewHolder(View itemView) {
            super(itemView);
            //cv = (CardView)itemView.findViewById(R.id.cv);
            newTitle = (TextView)itemView.findViewById(R.id.weather_title);
            newText = (TextView)itemView.findViewById(R.id.weather_text);
            newDate = (TextView)itemView.findViewById(R.id.new_date);
        }
    }


    RVAdapter(List<New> news){
        this.news = news;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public NewViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        cv = (CardView) viewGroup.findViewById(R.id.cv);
        return new NewViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewViewHolder newViewHolder, int i) {
        newViewHolder.newTitle.setText(news.get(i).title);
        newViewHolder.newText.setText(news.get(i).text);
        newViewHolder.newDate.setText(news.get(i).date);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }
}