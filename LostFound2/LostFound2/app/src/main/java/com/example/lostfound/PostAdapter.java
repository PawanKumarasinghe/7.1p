package com.example.lostfound;

import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder> {
    private List<Post> entries;
    private ClickListener clickListener;

    public interface ClickListener {
        void onItemSelected(Post post);
    }

    public PostAdapter(List<Post> entries, ClickListener clickListener) {
        this.entries = entries;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Post post = entries.get(position);
        holder.title.setText(post.toString());
        holder.itemView.setOnClickListener(v -> clickListener.onItemSelected(post));
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        TextView title;
        public Holder(View itemView) {
            super(itemView);
            title = itemView.findViewById(android.R.id.text1);
        }
    }
}
