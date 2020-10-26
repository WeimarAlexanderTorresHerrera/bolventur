package bo.com.bolventur.ui.adapters;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import bo.com.bolventur.R;
import bo.com.bolventur.model.Event;
import bo.com.bolventur.ui.viewHolder.EventViewHolder;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private List<Event> events;
    private LayoutInflater layoutInflater;

    public EventAdapter(List<Event> events, Context context) {
        this.events = events;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = events.get(position);

        Picasso.get().load(event.getPhoto()).into(holder.photoImageView);
        holder.titleTextView.setText(event.getTitle());
        holder.locationTextView.setText(event.getLocation());
        holder.dateTextView.setText(getDate(event.getDate()));

        System.out.println(event.getTicket().get("price"));

        String price = "Bs. " + event.getTicket().get("price");
        holder.priceTextView.setText(price);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;
    }

    public void updateEvents(List<Event> events) {
        this.events = events;
        notifyDataSetChanged();
    }
}
