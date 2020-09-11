package dot.ga.gov.customdialog;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.FruitViewHolder> implements Filterable {
    private List<Fruits> mDataset;
    private List<Fruits> filterlist;
    RecyclerViewItemClickListener recyclerViewItemClickListener;

    public DataAdapter(List<Fruits> myDataset, RecyclerViewItemClickListener listener) {
        this.mDataset = myDataset;
        this.filterlist = mDataset;
        this.recyclerViewItemClickListener = listener;
    }

    @NonNull
    @Override
    public FruitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
        FruitViewHolder vh = new FruitViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull FruitViewHolder fruitViewHolder, int position) {
        fruitViewHolder.mTextView.setText(filterlist.get(position).getFruitName());
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (filterlist == null) {
            return 0;
        } else {
            return filterlist.size();
        }
    }

    public class FruitViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextView;
        public FruitViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.textView);
            v.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            recyclerViewItemClickListener.clickOnItem(filterlist.get(this.getAdapterPosition()).getFruitName());

        }
    }

    public interface RecyclerViewItemClickListener {
        void clickOnItem(String data);
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Fruits> filter = new ArrayList<>();
                if (constraint.length() == 0) {
                    filter = mDataset;
                } else {
                    for (Fruits res : mDataset) {
                        if (res.getFruitName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            filter.add(res);
                        }
                    }
                }
                FilterResults result = new FilterResults();
                result.count = filter.size();
                result.values = filter;
                return result;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filterlist = (List<Fruits>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}


