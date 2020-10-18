package bo.com.bolventur.ui.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import bo.com.bolventur.R;

public class EventViewHolder extends RecyclerView.ViewHolder {

    public ImageView coverImageView;
    public TextView nameTextView;
    public TextView addressTextView;

    public EventViewHolder(@NonNull View itemView) {
        super(itemView);

        coverImageView = itemView.findViewById(R.id.coverImageView);
        nameTextView = itemView.findViewById(R.id.nameTextView);
        addressTextView = itemView.findViewById(R.id.addressTextView);
    }
}
