package ru.arlen.mvp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.DisplayMetrics;

import java.io.IOException;

public class Model {
    private static final String IMG_FOLDER = "img/";
    private Context mContext;
    private LoadingTask loadingTask;


    public Model(Context context) {
        mContext = context;
    }

    public void loadBitmap(String imgName, BitmapCallback callback) {
        loadingTask = new LoadingTask(mContext, callback);
        loadingTask.execute(IMG_FOLDER + imgName);
    }

    private class LoadingTask extends AsyncTask<String, Void, Bitmap> {

        private Context context;
        private BitmapCallback callback;

        public LoadingTask(Context context, BitmapCallback callback) {
            this.context = context.getApplicationContext();
            this.callback = callback;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap;

            try {
                BitmapFactory.Options sizeOptions = new BitmapFactory.Options();
                sizeOptions.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(context.getAssets().open(strings[0]), null, sizeOptions);

                DisplayMetrics metrics = context.getResources().getDisplayMetrics();

                int screenSize = Math.max(metrics.widthPixels, metrics.heightPixels);
                int bitmapSize = Math.max(sizeOptions.outWidth, sizeOptions.outHeight);

                int ratio = 1;
                while (bitmapSize > screenSize) {
                    bitmapSize /= 2;
                    ratio *= 2;
                }

                BitmapFactory.Options readOptions = new BitmapFactory.Options();
                readOptions.inSampleSize = ratio;

                bitmap = BitmapFactory.decodeStream(context.getAssets().open(strings[0]), null, readOptions);
            } catch (IOException e) {
                e.printStackTrace();
                bitmap = null;
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            callback.onReady(bitmap);
        }
    }

    interface BitmapCallback {
        void onReady(Bitmap bitmap);
    }
}
