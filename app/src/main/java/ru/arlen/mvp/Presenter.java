package ru.arlen.mvp;

import android.graphics.Bitmap;

public class Presenter {
    private View mView;
    private Model mModel;

    public void attachView(ImageActivity activity) {
        mView = activity;
        mModel = new Model(activity);
    }

    public void detachView() {
        mView = null;
    }

    public void viewIsReady() {
        String imgName = mView.getInImgName();
        if (imgName != null) {
            loadBitmap(imgName);
        }
    }

    public void loadBitmap(String imgName) {
        mModel.loadBitmap(imgName, new Model.BitmapCallback() {
            @Override
            public void onReady(Bitmap bitmap) {
                mView.showBitmap(bitmap);
            }
        });
    }
}
