package ru.arlen.mvp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import ru.arlen.mvp_mvvm.R;

public class ImageActivity extends Activity implements ru.arlen.mvp.View {
    private Presenter presenter;
    private ImageView bitmapView;
    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        bitmapView = findViewById(R.id.bitmap_view);
        progressBar = findViewById(R.id.progress);

        presenter = new Presenter(new Model(this));
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    @Override
    public void showBitmap(Bitmap bitmap) {
        if (bitmap != null && bitmapView != null) {
            progressBar.setVisibility(View.GONE);
            bitmapView.setImageBitmap(bitmap);
        }
    }

    @Override
    public Intent getInIntent() {
        return this.getIntent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
