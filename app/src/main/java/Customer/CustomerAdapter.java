package Customer;

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

import Presales.Presale;
import Presales.PresaleAdapter;

/**
 * Created by PRIMA on 10/31/2017.
 */

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {
    private List<CustomerGetSet> customer;
    private int rowLayout;
    private Context context;
    public CustomerAdapter(String[] strings) {
    }

    public CustomerAdapter() {

    }
    public CustomerAdapter(List<CustomerGetSet> customer, int rowLayout, Context context) {
        this.customer = customer;
        this.rowLayout = rowLayout;
        this.context = context;
    }
    @Override
    public CustomerAdapter.CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new CustomerAdapter.CustomerViewHolder(view);
    }


    @Override
    public void onBindViewHolder(CustomerAdapter.CustomerViewHolder holder, int position) {
        holder.movieTitle.setText(customer.get(position).getId().toString());
        holder.data.setText(customer.get(position).getName().toString());
    }

    @Override
    public int getItemCount() {
        return customer.size();
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;

        public CustomerViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.searching_customer_layout);
            movieTitle = (TextView) v.findViewById(R.id.buyeridentificationsearchingTextviewTitle);
            data = (TextView) v.findViewById(R.id.buyeridentificationsearchingTextviewSubtittle);
            movieDescription = (TextView) v.findViewById(R.id.buyeridentificationsearchingTextviewDescription);
        }
    }
}
