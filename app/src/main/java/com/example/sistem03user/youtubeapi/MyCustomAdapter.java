package com.example.sistem03user.youtubeapi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter {

    private static final String TAG = "YOUTUBETAG";

    private Context context;
    //Activity activity;
    ArrayList<VideoDetails> videoDetailsArrayList;


    public MyCustomAdapter(Context context, ArrayList<VideoDetails> videoDetailsArrayList) {
        this.context = context;
        this.videoDetailsArrayList = videoDetailsArrayList;
    }


    @Override
    public Object getItem(int position) {
        return videoDetailsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_video_list, null);

        }

        ImageView imgfoto = convertView.findViewById(R.id.thumbnailView);
        final TextView title = convertView.findViewById(R.id.thumbnailTitle);
        final TextView descripcion = convertView.findViewById(R.id.textDescription);
        final TextView textURL = convertView.findViewById(R.id.textURL);
        final TextView textVideoID = convertView.findViewById(R.id.textVideoID);

//        YouTubeThumbnailView videoThumbnailImageView= convertView.findViewById(R.id.thumbnailView);
//
//        videoThumbnailImageView.initialize(Constants.DEVELOPER_KEY, new YouTubeThumbnailView.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
//                //when initialization is sucess, set the video id to thumbnail to load
//                youTubeThumbnailLoader.setVideo(videoDetailsArrayList.get(position).getVideoId());
//
//                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
//                    @Override
//                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
//                        //when thumbnail loaded successfully release the thumbnail loader as we are showing thumbnail in adapter
//                        youTubeThumbnailLoader.release();
//                    }
//
//                    @Override
//                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
//                        //print or show error when thumbnail load failed
//                        Log.e(TAG, "Youtube Thumbnail Error");
//                    }
//                });
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
//                //print or show error when initialization failed
//                Log.e(TAG, "Youtube Initialization Failure");
//
//            }
//        });

        Glide.with(context)
                .load(videoDetailsArrayList.get(position).getUrl())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgfoto);


        title.setText(videoDetailsArrayList.get(position).getTitle());
        textVideoID.setText(videoDetailsArrayList.get(position).getVideoId());
        descripcion.setText(videoDetailsArrayList.get(position).getDescription());
        textURL.setText(videoDetailsArrayList.get(position).getUrl());




        return convertView;
    }

    @Override
    public int getCount() {
        return videoDetailsArrayList.size();
    }


}

