package sabal.dumpy_lyceum.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import sabal.dumpy_lyceum.Constants;
import sabal.dumpy_lyceum.DTOs.New;
import sabal.dumpy_lyceum.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewViewHolder> {
    private static final String TAG = "CustomAdapter";

    private List<New> news = new ArrayList<>();

    public static class NewViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView newTitle;
        TextView newText;
        ImageView newImage;
        ProgressBar pg;
        //TextView newDate;

        NewViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            newTitle = (TextView) itemView.findViewById(R.id.weather_title);
            newText = (TextView) itemView.findViewById(R.id.weather_text);
            newImage = (ImageView) itemView.findViewById(R.id.new_imageView);
            pg = (ProgressBar) itemView.findViewById(R.id.new_progressBar);
            //newDate = (TextView)itemView.findViewById(R.id.new_date);
        }
    }


    public NewsAdapter(List<New> news) {
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
        String URL = news.get(i).getImage();
        if (!TextUtils.isEmpty(URL))
            setImage(newViewHolder.newImage, news.get(i).getImage(), newViewHolder.pg);
        else {
            newViewHolder.newImage.setVisibility(View.GONE);
        }
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

    private void setImage(ImageView imageView, String imageURL, ProgressBar progressBar) {
//        Picasso.with(imageView.getContext()).cancelRequest(imageView);
        progressBar.setVisibility(View.VISIBLE);
        Picasso.with(imageView.getContext())
                .load(Constants.URL.HOST + imageURL)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                        imageView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError() {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(imageView.getContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
    }
}