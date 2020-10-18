package bo.com.bolventur.ui.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import bo.com.bolventur.R;

public class EventViewHolder extends RecyclerView.ViewHolder {

    public ImageView photoImageView;
    public TextView titleTextView;
    public TextView locationTextView;
    public TextView dateTextView;
    public TextView priceTextView;

    public EventViewHolder(@NonNull View itemView) {
        super(itemView);

        photoImageView = itemView.findViewById(R.id.photoImageView);
        titleTextView = itemView.findViewById(R.id.titleTextView);
        locationTextView = itemView.findViewById(R.id.locationTextView);
        dateTextView = itemView.findViewById(R.id.dateTextView);
        priceTextView = itemView.findViewById(R.id.priceTextView);
    }
}
