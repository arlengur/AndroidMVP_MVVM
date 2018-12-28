package ru.arlen.mvp;

import android.content.Intent;
import android.graphics.Bitmap;

public interface View {
    void showBitmap(Bitmap bitmap);
    String getInImgName();
}
