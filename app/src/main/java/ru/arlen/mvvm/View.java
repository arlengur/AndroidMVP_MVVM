package ru.arlen.mvvm;

import java.util.List;

public interface View {
    void showImgsList(List<String> imgs);
    void showImg(String imgName);
}
