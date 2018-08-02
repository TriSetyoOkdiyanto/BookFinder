package com.example.etrnty.bookfinder.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.etrnty.bookfinder.R;
import com.example.etrnty.bookfinder.custom.PositionedCropTransformation;
import com.example.etrnty.bookfinder.model.books.ModelItem;

public class HolderViewBestSelling extends RecyclerView.ViewHolder {

    TextView textViewJudul, textViewAuthor;
    ImageView imageView;
    RatingBar ratingBar;
    private String authors;
    private String title;
    private String image;
    private Float rating;

    public HolderViewBestSelling(View itemView) {
        super(itemView);

        textViewJudul   = (TextView) itemView.findViewById(R.id.textJudul);
        textViewAuthor  = (TextView) itemView.findViewById(R.id.textAuthor);
        imageView       = (ImageView) itemView.findViewById(R.id.imageBook);
        ratingBar       = (RatingBar) itemView.findViewById(R.id.ratingBar);
    }

    public void initLayout(ModelItem model, Context context) {

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
                if (model.getVolumeInfo().getAuthors().size() == 1) {
                    authors = model.getVolumeInfo().getAuthors().get(0);
                }else if(model.getVolumeInfo().getAuthors().size() > 1){
                    authors = model.getVolumeInfo().getAuthors().get(0);
                    for(int j=1;j<model.getVolumeInfo().getAuthors().size();j++){
                        authors = authors + ", " + model.getVolumeInfo().getAuthors().get(j);
                    }
                }
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

        if (model.getVolumeInfo() != null) {
            if (model.getVolumeInfo().getAverageRating() != null) {
                rating = model.getVolumeInfo().getAverageRating();
                ratingBar.setRating(rating);
            } else {
                ratingBar.setRating(0.0f);
            }
        }

        /*if (model.getVolumeInfo().getAverageRating() == 0f || model.getVolumeInfo().getAverageRating() == null ) {
            ratingBar.setRating(0.0f);
        } else {

        }*/

    }
}
