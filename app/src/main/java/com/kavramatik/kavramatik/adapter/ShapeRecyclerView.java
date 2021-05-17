package com.kavramatik.kavramatik.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kavramatik.kavramatik.databinding.ShapeRowBinding;
import com.kavramatik.kavramatik.model.ShapeModel;
import com.kavramatik.kavramatik.util.ImageClickInterface;

import java.util.List;

public class ShapeRecyclerView extends RecyclerView.Adapter<ShapeRecyclerView.ViewHolder> {
    private final List<ShapeModel> shapeModels;
    private final ImageClickInterface imageClickInterface;

    public ShapeRecyclerView(List<ShapeModel> models, ImageClickInterface clickInterface) {
        this.shapeModels = models;
        this.imageClickInterface = clickInterface;
    }

    @NonNull
    @Override
    public ShapeRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ShapeRowBinding binding = ShapeRowBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShapeRecyclerView.ViewHolder holder, int position) {
        final ShapeModel model = shapeModels.get(position);
        holder.binding.setShape(model);
        holder.binding.executePendingBindings();
        holder.binding.shapeCircleImage.setOnClickListener(v -> imageClickInterface.onItemClick(model.getShapeText()));
    }

    @Override
    public int getItemCount() {
        return shapeModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ShapeRowBinding binding;

        public ViewHolder(@NonNull ShapeRowBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
