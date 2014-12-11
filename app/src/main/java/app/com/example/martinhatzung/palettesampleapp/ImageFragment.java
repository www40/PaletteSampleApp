package app.com.example.martinhatzung.palettesampleapp;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {

    Data data;
    TextView mutedColor_textview;
    TextView lightMutedColor_textview;
    TextView lightVibrantColor_textView;
    TextView darkMutedColor_textView;
    TextView darkVibrantColor_textview;
    TextView vibrantColor_textview;
    ImageView imageView;

    public ImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        mutedColor_textview = (TextView) view.findViewById(R.id.mutedColor_textview);
        lightMutedColor_textview = (TextView) view.findViewById(R.id.lightMutedColor_textview);
        lightVibrantColor_textView = (TextView) view.findViewById(R.id.lightVibrantColor_textView);
        darkMutedColor_textView = (TextView) view.findViewById(R.id.darkMutedColor_textView);
        darkVibrantColor_textview = (TextView) view.findViewById(R.id.darkVibrantColor_textview);
        vibrantColor_textview = (TextView) view.findViewById(R.id.vibrantColor_textview);
        imageView = (ImageView) view.findViewById(R.id.image);
        data = new Gson().fromJson(getArguments().getString("data"), Data.class);
        new ImageRequestTask().execute();
        return view;
    }

    public static final ImageFragment newInstance(Data data)
    {
        ImageFragment fragment = new ImageFragment();
        Bundle localBundle = new Bundle();
        localBundle.putString("data", new Gson().toJson(data));
        fragment.setArguments(localBundle);
        return fragment;
    }

    private class ImageRequestTask extends AsyncTask<Void, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... params) {
            Bitmap bitmap = null;
            URL url;
            try {
                url = new URL(data.link);
                bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (result != null) {
                imageView.setImageBitmap(result);
                Palette palette = Palette.generate(result);
                mutedColor_textview.setBackgroundColor(palette.getMutedColor(0));
                lightMutedColor_textview.setBackgroundColor(palette.getLightMutedColor(0));
                lightVibrantColor_textView.setBackgroundColor(palette.getLightVibrantColor(0));
                darkMutedColor_textView.setBackgroundColor(palette.getDarkMutedColor(0));
                darkVibrantColor_textview.setBackgroundColor(palette.getDarkVibrantColor(0));
                vibrantColor_textview.setBackgroundColor(palette.getVibrantColor(0));

                Palette.Swatch muted = palette.getMutedSwatch();
                if (muted != null)
                    mutedColor_textview.setTextColor(muted.getTitleTextColor());

                Palette.Swatch lightMuted = palette.getMutedSwatch();
                if (lightMuted != null)
                    lightMutedColor_textview.setTextColor(lightMuted.getTitleTextColor());

                Palette.Swatch lightVibrant = palette.getMutedSwatch();
                if (lightVibrant != null)
                    lightVibrantColor_textView.setTextColor(lightVibrant.getTitleTextColor());

                Palette.Swatch darkMuted = palette.getMutedSwatch();
                if (darkMuted != null)
                    darkMutedColor_textView.setTextColor(darkMuted.getTitleTextColor());

                Palette.Swatch darkVibrant = palette.getMutedSwatch();
                if (darkVibrant != null)
                    darkVibrantColor_textview.setTextColor(darkVibrant.getTitleTextColor());

                Palette.Swatch vibrant = palette.getMutedSwatch();
                if (vibrant != null)
                    vibrantColor_textview.setTextColor(vibrant.getTitleTextColor());

            }
        }
    }

}
