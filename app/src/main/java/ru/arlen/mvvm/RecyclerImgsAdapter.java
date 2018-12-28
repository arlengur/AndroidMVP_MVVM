package ru.arlen.mvvm;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import ru.arlen.mvp_mvvm.R;
import ru.arlen.mvp_mvvm.databinding.ImgItemBinding;

import java.util.LinkedList;
import java.util.List;

public class RecyclerImgsAdapter extends RecyclerView.Adapter<RecyclerImgsAdapter.Holder> {
    private List<String> items = new LinkedList<>();
    private CustomViewModel mViewModel;

    public RecyclerImgsAdapter(CustomViewModel viewModel) {
        mViewModel = viewModel;
    }

    public void setData(List<String> data) {
        items.clear();
        items.addAll(data);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ImgItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.img_item, parent, false);
        binding.setViewModel(mViewModel);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        ImgItemBinding mBinding;

        public Holder(ImgItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(String imgName) {
            mBinding.setImgName(imgName);
            mBinding.executePendingBindings();
        }
    }
}