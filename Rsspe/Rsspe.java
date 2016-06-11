package com.tomercon.myrssfeed.Rsspe;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.einmalfel.earl.EarlParser;
import com.einmalfel.earl.Feed;
import com.einmalfel.earl.Item;
import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.zip.DataFormatException;

/**
 * Created by user on 11-Jun-16.
 */
public class Rsspe {
    int mode = Activity.MODE_PRIVATE;
    SharedPreferences data,desc,datex;
    ProgressDialog progressDialog;
    String dbg="#F6F6F6";
    int max_item=0;
    boolean max_item_on = false;

    public void ex(final LinearLayout parent,final Context c, final String url, final Boolean Description, final Boolean pdate,Boolean imageLink)
    {

        init(c,url);
        Tasks.executeInBackground(c, new BackgroundWork<String>() {
            @Override
            public String doInBackground() throws Exception {
                InputStream inputStream = null;
                try {
                    inputStream = new URL(url).openConnection().getInputStream();
                    Feed feed = null;
                    try {
                        feed = EarlParser.parseOrThrow(inputStream, 0);
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    } catch (IOException e) {

                        e.printStackTrace();
                    } catch (DataFormatException e) {
                        e.printStackTrace();
                    }
                    Log.i("TAG", "Processing feed: " + feed.getTitle());

                    Integer p = 1;
                    Integer jutter = feed.getItems().size();
                    for (Item item : feed.getItems()) {

                        String title = item.getTitle();
                        Log.i("TAG", "Item title: " + (title == null ? "N/A" : title));
                        SharedPreferences.Editor e1 = data.edit();
                        e1.putString(p.toString(), title);
                        e1.commit();

                        if(Description)
                        {
                            String description = item.getDescription();
                            SharedPreferences.Editor e4 = desc.edit();
                            e4.putString(p.toString(), description);
                            e4.commit();
                        }
                        if(pdate)
                        {
                            Date datew = item.getPublicationDate();
                            //String ds = datew.toString();
                            //SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy");
                            //String ds = format.format(datew);
                            SharedPreferences.Editor e33 = datex.edit();
                            e33.putString(p.toString(), datew.toString());
                            e33.commit();
                        }
                        if(max_item_on && max_item<=jutter && p==max_item)
                        {
                                break;
                        }

                        p++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null; // expensive operation
            }
        }, new Completion<String>() {
            @Override
            public void onSuccess(Context context, String result) {
                //display(result);
                progressDialog.cancel();
                lg(parent,c,Description,pdate,dbg);
            }

            @Override
            public void onError(Context context, Exception e) {
                //showError(e);
            }
        });
    }
    private String urltoname(Context b,String vrl)
    {
        String a[] = vrl.split("/");
        Integer k = a.length;
        //Toast.makeText(b, k.toString(), Toast.LENGTH_SHORT).show();
        String f = a[k-1];
        /*
        java.lang.String.split splits on regular expressions, and . in a regular expression means "any character".

        Try temp.split("\\.")

        http://stackoverflow.com/questions/7935858/the-split-method-in-java-does-not-work-on-a-dot
         */
        //String df[] = f.split("\\.");
        //String fin = df[0];
        return f;
    }
    private void init(Context c,String hurl)
    {
        String base_name = urltoname(c,hurl);
        data = c.getSharedPreferences(base_name+"#@#data", mode);
        desc = c.getSharedPreferences(base_name+"#@#desc", mode);
        datex = c.getSharedPreferences(base_name+"#@#date", mode);
        SharedPreferences.Editor e1 = data.edit();
        e1.clear();
        e1.commit();
        SharedPreferences.Editor e2 = desc.edit();
        e2.clear();
        e2.commit();
        SharedPreferences.Editor e6 = datex.edit();
        e6.clear();
        e6.commit();



        //new feeder().execute();
        progressDialog = new ProgressDialog(c);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(true);


        c.getResources();
    }

    private void lg(LinearLayout p,final Context f,boolean ds,boolean dt, final String gb)
    {
        Integer hm = data.getAll().size();
        //Toast.makeText(f, hm.toString(), Toast.LENGTH_SHORT).show();
        int cutter;
        if(max_item_on && max_item<=hm)
        {
            cutter = max_item;
        }
        else {
            cutter = hm;
        }

        for(Integer l = 1;l<=cutter;l++)
        {
            ////////////////////
            LinearLayout hikes = new LinearLayout(f);
            hikes.setBackgroundColor(Color.argb(200, 204, 204, 204));
            hikes.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams hikesParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            hikesParams.setMargins(0, 0, 0, 1);
            ////////////////////
            final String j = data.getString(l.toString(),"error");
            TextView titlev = new TextView(f);
            titlev.setText(j);
            //titlev.setBackgroundColor(Color.argb(200, 204, 204, 204));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 0, 2);
            titlev.setPadding(5, 5, 5, 5);
            titlev.setTextColor(Color.BLACK);
            titlev.setTextSize(20.0f);
            hikes.addView(titlev, layoutParams);
            ///////////////



            if(dt)
            {
                final String v = datex.getString(l.toString(),"error");
                TextView vv = new TextView(f);
                vv.setTextColor(Color.parseColor("#333333"));
                vv.setText(v);
                hikes.addView(vv,layoutParams);
            }
            if(ds)
            {
                final String k = desc.getString(l.toString(),"error");
                titlev.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View view) {
                        Intent newActivity = new Intent(f, Rsspe_description.class);
                        newActivity.putExtra("titla", j);
                        newActivity.putExtra("descripta", k);
                        newActivity.putExtra("descripta_bgcolor", gb);
                        f.startActivity(newActivity);
                    }
                });
            }
            p.addView(hikes,hikesParams);

        }

    }
    public String setDbg(String j)
    {
        dbg=j;
        return j;
    }
    public void setMaxItems(int max)
    {
        max_item = max;
        max_item_on = true;
    }


}
