package ru.arlen.mvp;

import android.content.Intent;
import android.graphics.Bitmap;

import static ru.arlen.mvvm.MainActivity.IMG;

public class Presenter {
    private View mView;
    private Model mModel;

    public Presenter(Model model) {
        mModel = model;
    }

    public void attachView(View view) {
        mView = view;
    }

    public void detachView() {
        mView = null;
    }

    public void viewIsReady() {
        Intent intent = mView.getInIntent();
        String imgName = intent.getStringExtra(IMG);
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
