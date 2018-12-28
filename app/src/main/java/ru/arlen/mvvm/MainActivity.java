package ru.arlen.mvvm;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import ru.arlen.mvp.ImageActivity;
import ru.arlen.mvp_mvvm.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View {
    public static final String IMG = "IMG";
    private RecyclerImgsAdapter adapter;
    private CustomViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(CustomViewModel.class);

        adapter = new RecyclerImgsAdapter(viewModel);
        final RecyclerView imgList = findViewById(R.id.recycler);
        imgList.setAdapter(adapter);

        viewModel.attachViewModel(this, new Model(this));
        viewModel.viewIsReady();
    }

    @Override
    public void showImgsList(List<String> imgs) {
        adapter.setData(imgs);
    }

    @Override
    public void showImg(String imgName) {
        Intent intent = new Intent(this, ImageActivity.class);
        intent.putExtra(IMG, imgName);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.detachView();
    }
}
