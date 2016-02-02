package com.uowd.sport.nestdrecylerviews_vert_horizontal;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.toolbox.ImageLoader;

public class LruBitmapCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {

    //Getting cache size    
    public static int getDefaultLruCacheSize() {
        Log.d("X", "Cache size provided");
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;

        return cacheSize;
    }

    //No parm Constructor
    public LruBitmapCache()
    {
        //Calling second constructor
        this(getDefaultLruCacheSize());
    }

    //Second Constructor
    public LruBitmapCache(int maxSize) {
        super(maxSize);
    }


    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight() / 1024;
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }


}