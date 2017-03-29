package sabal.dumpy_lyceum.Fragments;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import sabal.dumpy_lyceum.Constants;
import sabal.dumpy_lyceum.DTOs.New;
import sabal.dumpy_lyceum.R;

import static sabal.dumpy_lyceum.Constants.URL.HOST;
import static sabal.dumpy_lyceum.Utils.parseFullNewFromHtml;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowNewFragment extends Fragment {
    private SliderLayout mDemoSlider;
    private String link;
    private TextView titleView, introView, fullTextView;


    public ShowNewFragment(String link) {
        this.link = link;
    }

    public ShowNewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_new, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDemoSlider = (SliderLayout) view.findViewById(R.id.show_slider);

        titleView = (TextView) view.findViewById(R.id.show_title);

        introView = (TextView) view.findViewById(R.id.show_intro);

        fullTextView = (TextView) view.findViewById(R.id.show_full_text);

        new GetNewTask().execute();
    }

    @Override
    public void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }


    private void initializeData(New item) {
        titleView.setText(item.getTitle());
        introView.setText(item.getIntroText());
        fullTextView.setText(item.getFullText());

        for (String image : item.getImages()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView
                    .image(HOST + image)
                    .setScaleType(BaseSliderView.ScaleType.FitCenterCrop);

            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
    }

    private class GetNewTask extends AsyncTask<String, String, New> {
        ProgressDialog pdLoading = new ProgressDialog(getActivity());

        New response = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected New doInBackground(String... strings) {

            try {
                Document doc = Jsoup.connect(Constants.URL.HOST + link).get();
                response = parseFullNewFromHtml(doc);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(pdLoading.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            return response;
        }

        @Override
        protected void onPostExecute(New result) {
            initializeData(result);

            pdLoading.dismiss();
        }

    }
}
