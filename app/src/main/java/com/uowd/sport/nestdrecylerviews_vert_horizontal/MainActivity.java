package com.uowd.sport.nestdrecylerviews_vert_horizontal;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter verticalAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView myRecyclerView;
    private ArrayList<String> data;
    private Toolbar mToolbar;
    private ProgressBar mProgBar;

    private ArrayList<String> mImageArray=new ArrayList<>();
    private ArrayList<GetMixCatalougeData> mImageArrayData=new ArrayList<>();//

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_catalouge);
        myRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mProgBar= (ProgressBar) findViewById(R.id.progressBarMain);

        this.setSupportActionBar(mToolbar);

        final Drawable upArrow= ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        if (upArrow != null) {
            upArrow.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.tabsScrollColor),PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mProgBar.setVisibility(View.VISIBLE);
        mProgBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.ColorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
        data = new ArrayList<>();



        GetCatalougeImaesNames();


    }

    private void GetCatalougeImaesNames() {
        Map<String, String> params = new HashMap<>();
        params.put("BrandID","1155");
        params.put("Collection","2");

        Custom_Volly_Request jsonObjReq = new Custom_Volly_Request(Request.Method.POST, "https://gizbo.ae/GizboApp/gizbo_php_files/get_catalogue_images_new.php", params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        int json_status,json_statusData ;
                        try {
                            JSONArray catalouge,catalougeData;
                            json_status = response.getInt("SuccessImages");
                            Log.d("Response",response.toString());
                            if (json_status == 1) {
                                catalouge = response.getJSONArray("Images");
                                catalougeData = response.getJSONArray("ImagesData");
                                for (int i = 0; i < catalouge.length(); i++) {
                                    JSONObject jobj = catalouge.getJSONObject(i);
                                    mImageArray.add(jobj.getString("ImageName"));

                                }
                                json_statusData = response.getInt("SuccessData");
                                if(json_statusData==1) {
                                    for (int i = 0; i < catalougeData.length(); i++) {
                                        JSONObject jobj2 = catalougeData.getJSONObject(i);
                                        GetMixCatalougeData mCatDetails = new GetMixCatalougeData(
                                                jobj2.getString("Item1Value"),
                                                jobj2.getString("Item2Value"),
                                                jobj2.getString("Item3Value"),
                                                jobj2.getString("Item4Value"),
                                                jobj2.getString("Item5Value"),
                                                jobj2.getString("Item6Value"),
                                                jobj2.getString("Item7Value"),
                                                jobj2.getString("FooterDetails")
                                        );
                                        mImageArrayData.add(mCatDetails);
                                    }
                                }

                                for (int i = 0; i < 3; i++) {
                                    data.add(String.valueOf(i));
                                }
                                mLayoutManager = new LinearLayoutManager(MainActivity.this);
                                myRecyclerView.addItemDecoration(new SpacesItemDecoration(10));
                                myRecyclerView.setLayoutManager(mLayoutManager);
                                verticalAdapter = new MyVerticalAdapter(MainActivity.this, data,mImageArray,mImageArrayData);
                                myRecyclerView.setAdapter(verticalAdapter);
                                myRecyclerView.setVisibility(View.VISIBLE);
                                mProgBar.setVisibility(View.INVISIBLE);

                            }else{
                                mProgBar.setVisibility(View.INVISIBLE);
                            }

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("ErrorJson",e.getMessage());
                            mProgBar.setVisibility(View.INVISIBLE);
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("ErrorVolly: " + error.getMessage());
                mProgBar.setVisibility(View.INVISIBLE);
            }});
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
}
