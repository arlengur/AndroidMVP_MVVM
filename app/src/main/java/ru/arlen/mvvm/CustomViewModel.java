package ru.arlen.mvvm;

import android.arch.lifecycle.ViewModel;

import java.util.List;

public class CustomViewModel extends ViewModel {
    private View mView;
    private Model mModel;

    public void attachViewModel(View view, Model model) {
        mView = view;
        mModel = model;
    }

    public void detachView() {
        mView = null;
    }

    public void viewIsReady() {
        loadImgs();
    }

    public void loadImgs() {
        mModel.loadImgs(new Model.LoadImgsCallback() {
            @Override
            public void onLoad(List<String> imgs) {
                mView.showImgsList(imgs);
            }
        });
    }
    public void onClick(String imgName) {
        mView.showImg(imgName);
    }
}
