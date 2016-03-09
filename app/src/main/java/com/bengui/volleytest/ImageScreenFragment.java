/* (c) Disney. All rights reserved. */
package com.bengui.volleytest;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.bengui.volleytest.managers.VolleyManager;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * @author benjamin.massello.
 */
public class ImageScreenFragment extends Fragment {


    public static final String IMAGE_URL = "http://impact89fm.org/wp-content/uploads/2016/01/Radiohead_head_logo2.png";
    private Button requestButton;
    private ImageView myImage;
    private VolleyManager volleyManager;
    private Button requestButtonPicasso;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        volleyManager = VolleyManager.getInstance(getActivity().getApplicationContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_screen, null);

        myImage = (ImageView) view.findViewById(R.id.image_container);
        requestButton = (Button) view.findViewById(R.id.request_btn);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage();
            }
        });
        requestButtonPicasso = (Button) view.findViewById(R.id.picasso_request_btn);
        requestButtonPicasso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageWithPicasso();
            }
        });
        Button cleanButton = (Button) view.findViewById(R.id.clean_btn);
        cleanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myImage.setImageResource(android.R.color.transparent);
            }
        });
        return view;
    }

    private void getImage(){
        ImageLoader imageLoader = volleyManager.getImageLoader();

        imageLoader.get(IMAGE_URL, ImageLoader.getImageListener(myImage, 0, 0));

        /*
        imageLoader.get(IMAGE_URL, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {

            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        */
    }

    private void getImageWithPicasso(){
        Picasso.with(getActivity()).load(IMAGE_URL).into(myImage);

        /*
        Picasso.with(getActivity()).load(IMAGE_URL).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                myImage.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
        */
    }

}
