package com.example.amin.fordp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amin.fordp.R;
import com.example.amin.fordp.models.ElementoRealm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.realm.RealmResults;

public class ElementAdapter extends RecyclerView.Adapter<ElementAdapter.ViewHolder> {
    private final RealmResults<ElementoRealm> elementResults;
    private final List<ElementoRealm> originalList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(ElementoRealm element);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public ElementAdapter(RealmResults<ElementoRealm> elementResults) {
        this.elementResults = elementResults;
        this.originalList = new ArrayList<>(elementResults);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ElementoRealm element = elementResults.get(position);

        int randomColorForImageView = getRandomColor();
        int randomColorForTitleTextView = getRandomColor();

        holder.imageView.setBackgroundColor(randomColorForImageView);
        holder.titleTextView.setBackgroundColor(randomColorForTitleTextView);

        assert element != null;
        holder.titleTextView.setText(element.getTitle() != null ? element.getTitle() : "");
        holder.imageView.setImageResource(element.getImageResource());

        holder.itemView.setOnClickListener(view -> {
            if (mListener != null) {
                mListener.onItemClick(element);
            }
        });
    }

    @Override
    public int getItemCount() {
        return elementResults.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public ImageView imageView;
        public TextView descriptionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            imageView = itemView.findViewById(R.id.imageView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }

    private int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public void updateData(RealmResults<ElementoRealm> filteredResults) {
        originalList.clear();
        originalList.addAll(filteredResults);
        notifyDataSetChanged();
    }
}
