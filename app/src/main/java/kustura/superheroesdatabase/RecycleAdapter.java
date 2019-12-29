package kustura.superheroesdatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import kustura.superheroesdatabase.apiServiceDto.Result;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.Row> {


    private List<Result> results;
    private LayoutInflater layoutInflater;
    private ItemClickInterface itemClickInterface;

    public RecycleAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Result getResult(int position) {
        return results.get(position);
    }

    @NonNull
    @Override
    public Row onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(
                R.layout.list_row, parent, false
        );
        return new Row(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Row holder, int position) {
        Result result = results.get(position);
        holder.name.setText(result.getName());
        Glide.with(holder.image).load(result.getImage().getUrl()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return results == null ? 0 : results.size();
    }


    public class Row extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name;
        private CircleImageView image;

        public Row(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.profile_image);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (itemClickInterface != null) {
                itemClickInterface.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public void setClickListener(RecycleAdapter.ItemClickInterface clickListener) {
        this.itemClickInterface = clickListener;
    }

    public interface ItemClickInterface {
        void onItemClick(View view, int position);
    }
}

