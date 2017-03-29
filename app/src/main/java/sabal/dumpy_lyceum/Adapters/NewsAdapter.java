package sabal.dumpy_lyceum.Adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sabal.dumpy_lyceum.DTOs.New;
import sabal.dumpy_lyceum.Fragments.ShowNewFragment;
import sabal.dumpy_lyceum.R;

import static sabal.dumpy_lyceum.Constants.URL.HOST;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewViewHolder> {

    private List<New> news = new ArrayList<>();
    private AppCompatActivity activity;

    public NewsAdapter(List<New> news, AppCompatActivity activity) {
        this.news = news;
        this.activity = activity;
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
        New item = news.get(i);
        try {
            newViewHolder.bind(item);
            newViewHolder.itemView.setOnClickListener(view -> {
                ShowNewFragment fragment = new ShowNewFragment(item.getLink());
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, fragment).addToBackStack(null).commit();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    static class NewViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView newTitle;
        TextView newText;
        ImageView newImage;
        ProgressBar pg;
        AppCompatTextView timeView;

        NewViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            newTitle = (TextView) itemView.findViewById(R.id.weather_title);
            newText = (TextView) itemView.findViewById(R.id.weather_text);
            newImage = (ImageView) itemView.findViewById(R.id.new_imageView);
            pg = (ProgressBar) itemView.findViewById(R.id.new_progressBar);
            timeView = (AppCompatTextView) itemView.findViewById(R.id.timestamp);
        }

        void bind(final New newItem) throws IOException {
            newImage.setImageDrawable(null);

            newTitle.setText(newItem.getTitle());
            newText.setText(newItem.getIntroText());

            if (newItem.getMainImage() != null) {
                pg.setVisibility(View.VISIBLE);
                Picasso.with(newImage.getContext())
                        .load(HOST + newItem.getMainImage())
                        .into(newImage, new Callback() {
                            @Override
                            public void onSuccess() {
                                pg.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError() {
                                pg.setVisibility(View.GONE);
                                Toast.makeText(newImage.getContext(), "Error", Toast.LENGTH_LONG).show();
                            }
                        });
            }

            timeView.setText(newItem.getDate());
        }
    }
}
