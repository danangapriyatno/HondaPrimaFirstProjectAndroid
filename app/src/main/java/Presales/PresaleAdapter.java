package Presales;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.prima.projectlaravelpresales.R;

import java.util.List;

/**
 * Created by PRIMA on 10/20/2017.
 */

public class PresaleAdapter extends RecyclerView.Adapter<PresaleAdapter.PresalaViewHolder> {
    private List<Presale> presales;
    private int rowLayout;
    private Context context;

    public PresaleAdapter(String[] strings) {
    }

    public PresaleAdapter() {

    }
    public PresaleAdapter(List<Presale> presales, int rowLayout, Context context) {
        this.presales = presales;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public PresaleAdapter.PresalaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new PresalaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PresaleAdapter.PresalaViewHolder holder, int position) {
        holder.movieTitle.setText(presales.get(position).getId().toString());
        holder.data.setText(presales.get(position).getPayment().toString());
    }

    @Override
    public int getItemCount() {
        return presales.size();
    }

    public class PresalaViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;
        ImageView backbg;


        public PresalaViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            backbg = (ImageView) v.findViewById(R.id.backbg);
        }
    }


}