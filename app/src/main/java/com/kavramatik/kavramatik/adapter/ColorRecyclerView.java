package com.kavramatik.kavramatik.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kavramatik.kavramatik.databinding.ColorRowBinding;
import com.kavramatik.kavramatik.model.ColorModel;
import com.kavramatik.kavramatik.util.ImageClickInterface;

import java.util.List;

public class ColorRecyclerView extends RecyclerView.Adapter<ColorRecyclerView.ViewHolder> {

    private final List<ColorModel> models;
    private final ImageClickInterface imageClickInterface;

    public ColorRecyclerView(List<ColorModel> colorModels, ImageClickInterface clickInterface) {
        this.models = colorModels;
        this.imageClickInterface = clickInterface;
    }

    @NonNull
    @Override
    public ColorRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ColorRowBinding binding = ColorRowBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ColorRecyclerView.ViewHolder holder, int position) {
        final ColorModel colorModel = models.get(position);
        holder.binding.setColor(colorModel);
        holder.binding.executePendingBindings();
        holder.binding.colorCircleImage.setOnClickListener(v -> imageClickInterface.onItemClick(colorModel.getColorText()));

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ColorRowBinding binding;

        public ViewHolder(@NonNull ColorRowBinding colorRowBinding) {
            super(colorRowBinding.getRoot());
            this.binding = colorRowBinding;
        }
    }
}
