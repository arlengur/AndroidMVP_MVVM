package ru.arlen.mvvm;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Model {
    private static final String IMG_PATH = "img";
    private Context mContext;
    public Model(Context context) {
        this.mContext = context;
    }

    public void loadImgs(LoadImgsCallback callback) {
        try {
            String[] images = mContext.getAssets().list(IMG_PATH);
            if (images != null) {
                callback.onLoad(new ArrayList<>(Arrays.asList(images)));
            }

        } catch (IOException e) {
            Log.e("MVP ", e.getMessage());
        }
    }

    interface LoadImgsCallback {
        void onLoad(List<String> imgs);
    }
}
