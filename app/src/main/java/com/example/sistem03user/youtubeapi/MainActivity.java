package com.example.sistem03user.youtubeapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.Response;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, YouTubePlayer.PlaybackEventListener, AdapterView.OnItemClickListener {


    YouTubePlayerView youTubePlayerView;

    String claveyoutube= "AIzaSyD9GZLfzFIdowg9dIJyb6jfgac3P6mRp1U";


    ListView listView;

    String videoselect;

    String API_KEY= "AIzaSyD9GZLfzFIdowg9dIJyb6jfgac3P6mRp1U";
    ArrayList<VideoDetails> videoDetailsArrayList;
    MyCustomAdapter myCustomAdapter;
    //String url="https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=PLq_MGynXklvnlaed3VruS0dp1a7Qgpg4i&key=AIzaSyD9GZLfzFIdowg9dIJyb6jfgac3P6mRp1U";
    String url="https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=PLq_MGynXklvnlaed3VruS0dp1a7Qgpg4i&key="+API_KEY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView=findViewById(R.id.listView);
        videoDetailsArrayList=new ArrayList<>();
        myCustomAdapter=  new MyCustomAdapter(MainActivity.this, videoDetailsArrayList);
        displayVideos();

        youTubePlayerView= findViewById(R.id.youtube_view);

        youTubePlayerView.initialize(claveyoutube, this);

        listView.setOnItemClickListener(this);


    }

    private void displayVideos() {

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {

                try {
                    JSONObject jsonObject= new JSONObject((response));
                    JSONArray jsonArray= jsonObject.getJSONArray("items");


                    for(int i=0; i<jsonArray.length(); i++)
                    {
                        JSONObject jsonObject1= jsonArray.getJSONObject(i);
                        JSONObject jsonObjectSnippet=jsonObject1.getJSONObject("snippet");
                        JSONObject jsonObjectDefault= jsonObjectSnippet.getJSONObject("resourceId");
                        JSONObject thumbnails= jsonObjectSnippet.getJSONObject("thumbnails");
                        JSONObject medium= thumbnails.getJSONObject("medium");
                        String datourl= medium.getString("url");

                        String video_id= jsonObjectDefault.getString("videoId");
                        String title= jsonObjectSnippet.getString("title");
                        String description=jsonObjectSnippet.getString("description");


                        VideoDetails vd= new VideoDetails();
                        vd.setVideoId(video_id);
                        vd.setTitle(title);
                        vd.setDescription(description);
                        vd.setUrl(datourl);

                        videoDetailsArrayList.add(vd);


                    }

                    listView.setAdapter(myCustomAdapter);
                    myCustomAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }

        );

        requestQueue.add(stringRequest);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean fueRestaurado) {

        if(!fueRestaurado)
        {



            //youTubePlayer.cueVideo("RHcUU085kZc");
            // youTubePlayer.loadVideo("RHcUU085kZc");
            //youTubePlayer.loadVideo("2OiSO1NaJoI");

            //Usa una lista de videos de  youtube x el id
//            List<String> videoList= new ArrayList<>();
//            videoList.add("RHcUU085kZc");
//            videoList.add("AK-BL5g6ETk");
//            videoList.add("x5fAvIsN1UA");
//            youTubePlayer.loadVideos(videoList);


            //youTubePlayer.loadPlaylist("PLlEW_8OcYYBQBg5gqGlKcb4Ztp1QvUG_t");

            //  youTubePlayer.loadVideo(videoselect);


          youTubePlayer.loadPlaylist("PLq_MGynXklvnlaed3VruS0dp1a7Qgpg4i");


        }




    }




    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        if(youTubeInitializationResult.isUserRecoverableError())
        {
            youTubeInitializationResult.getErrorDialog(this,1).show();
        }else
        {
            String  error="Error al  iniciar Youtube"+youTubeInitializationResult.toString() ;

            Toast.makeText(getApplicationContext(), error,Toast.LENGTH_LONG).show();
        }

    }

   // @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       // super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==1)
            getYoutubePlayerProvider().initialize(claveyoutube, this);
        {

        }

    }

        protected YouTubePlayer.Provider getYoutubePlayerProvider()
        {
            return youTubePlayerView;
        }


    @Override
    public void onPlaying() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onSeekTo(int i) {

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {



        {

            int  cantidadItemList= myCustomAdapter.getCount();


            VideoDetails videoDetails= (VideoDetails)myCustomAdapter.getItem(position);

            switch (position)
            {
                case 0:

                    videoselect= videoDetails.getVideoId();

                    Toast.makeText(MainActivity.this,position+" ID: " + videoDetails.getVideoId(),  Toast.LENGTH_SHORT).show();
                    break;

                case 1:

                    Toast.makeText(MainActivity.this,position+" ID: "+ videoDetails.getVideoId(),  Toast.LENGTH_SHORT).show();
                    break;

                case 2:

                    Toast.makeText(MainActivity.this,position+" ID: "+ videoDetails.getVideoId(),  Toast.LENGTH_SHORT).show();
                    break;

            }

        }
    }
}
