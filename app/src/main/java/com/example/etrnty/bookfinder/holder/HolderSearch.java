package com.example.etrnty.bookfinder.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.etrnty.bookfinder.R;
import com.example.etrnty.bookfinder.custom.PositionedCropTransformation;
import com.example.etrnty.bookfinder.model.books.ModelItem;

import junit.framework.Assert;

import java.util.List;

public class HolderSearch extends RecyclerView.ViewHolder {

    TextView textViewJudul, textViewAuthor, textViewPage;
    ImageView imageView;
    private String authors;
    private String title;
    private String page = "";
    private String date = "";
    private String image = "";

    public HolderSearch(View itemView) {
        super(itemView);

        textViewJudul   = (TextView) itemView.findViewById(R.id.textTitle);
        textViewAuthor  = (TextView) itemView.findViewById(R.id.textAuthor);
        textViewPage    = (TextView) itemView.findViewById(R.id.textPage);
        imageView       = (ImageView) itemView.findViewById(R.id.foto);
    }

    public void initLayout(ModelItem model, Context context) {
        Log.e("LoadHolder", "Size : " + model);
        if (model != null) {
/*            if (model.getVolumeInfo().getAuthors().size() == 1) {
                authors = model.getVolumeInfo().getAuthors().get(0);
            } else if (model.getVolumeInfo().getAuthors().size() > 1) {
                authors = model.getVolumeInfo().getAuthors().get(0);
                for (int j = 1; j < model.getVolumeInfo().getAuthors().size(); j++) {
                    authors = authors + ", " + model.getVolumeInfo().getAuthors().get(j);
                }
            } else if (model.getVolumeInfo().getAuthors().size() < 1 || model.getVolumeInfo().getAuthors().size() == 0) {
                authors = "";
            }*/

            if (model.getVolumeInfo() != null) {
                if (model.getVolumeInfo().getTitle() != null) {
                    title = model.getVolumeInfo().getTitle();
                    textViewJudul.setText(title);
                } else {
                    textViewJudul.setText("");
                }
            }

            if (model.getVolumeInfo() != null) {
                if (model.getVolumeInfo().getAuthors() != null) {
                    authors = model.getVolumeInfo().getAuthors().get(0);
                    textViewAuthor.setText(authors);
                } else {
                    textViewAuthor.setText("");
                }
            }

            if (model.getVolumeInfo().getImageLinks() != null) {
                if (model.getVolumeInfo().getImageLinks().getSmallThumbnail() != null) {
                    image = model.getVolumeInfo().getImageLinks().getSmallThumbnail();
                    Glide
                            .with(context)
                            .load(image)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .centerCrop()
                            .dontAnimate()
                            .transform(new PositionedCropTransformation(context, 0.5f, 0))
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder)
                            .into(imageView);
                } else {
                    Glide
                            .with(context)
                            .load(R.drawable.placeholder)
                            .dontAnimate()
                            .dontTransform()
                            .into(imageView);
                }
            }

            if (model.getVolumeInfo().getPageCount() != null && model.getVolumeInfo().getPublishedDate() != null) {
                page = model.getVolumeInfo().getPageCount().toString();
                date = model.getVolumeInfo().getPublishedDate();
                textViewPage.setText(page);
                textViewPage.append(" | ");
                textViewPage.append(date);
            } else {
                textViewPage.setText("");
            }


        /*if (model.getVolumeInfo().getAverageRating() == 0f || model.getVolumeInfo().getAverageRating() == null ) {
            ratingBar.setRating(0.0f);
        } else {
            rating = model.getVolumeInfo().getAverageRating();
            ratingBar.setRating(rating);
        }*/

        }
    }
}
