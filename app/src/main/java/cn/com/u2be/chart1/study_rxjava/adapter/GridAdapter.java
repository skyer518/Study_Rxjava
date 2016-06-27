package cn.com.u2be.chart1.study_rxjava.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.com.u2be.chart1.study_rxjava.R;
import cn.com.u2be.chart1.study_rxjava.entity.ImageFile;

/**
 * Created by alek on 2016/6/27.
 */
public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ImageViewHolder> {

    private List<ImageFile> imageFiles;

    public GridAdapter() {
        imageFiles = new ArrayList<>(0);
    }


    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_image, null);
        GridLayoutManager.LayoutParams params = new GridLayoutManager.LayoutParams(GridLayoutManager.LayoutParams.MATCH_PARENT, GridLayoutManager.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        final ImageFile imageFile = imageFiles.get(position);
        holder.fileName.setText(imageFile.getFileName());
        holder.imageView.setImageBitmap(imageFile.getBitmap());
    }

    @Override
    public int getItemCount() {
        return imageFiles.size();
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView fileName;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_image);
            fileName = (TextView) itemView.findViewById(R.id.tv_FileName);
        }
    }


    public void addItem(ImageFile imageFile) {
        imageFiles.add(imageFile);
        notifyDataSetChanged();

    }


}
