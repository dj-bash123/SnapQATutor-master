package in.co.snapqa.clientapp0903.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import in.co.snapqa.clientapp0903.models.UserHistoryResponses;
import in.co.snapqa.clientapp0903.R;


/**
 * Created by OLA on 28/03/17.
 */

public class UserHistoryRecyclerViewAdapter extends RecyclerView.Adapter<UserHistoryRecyclerViewAdapter.MyViewHolder> {


    UserHistoryResponses userHistoryResponsess;

    public UserHistoryRecyclerViewAdapter(UserHistoryResponses userHistoryResponses){
        userHistoryResponsess = userHistoryResponses;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_history_rv, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.status.setText(userHistoryResponsess.getResponse().get(position).getStatus());

        holder.dealsid.setText(userHistoryResponsess.getResponse().get(position).getDealsId());

        holder.createdat.setText(userHistoryResponsess.getResponse().get(position).getCreatedAt());

        Log.d("jkhdjksahda  ","" + userHistoryResponsess.getResponse().get(position).getCreatedAt() );

        holder.linearLayout.setVisibility(LinearLayout.VISIBLE);

        final LinearLayout layout = holder.linearLayout;
    }

    @Override
    public int getItemCount() {
        if(userHistoryResponsess != null) {
            return userHistoryResponsess.getResponse().size();
        }else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView status, dealsid, createdat;
        LinearLayout linearLayout;

        public MyViewHolder(final View itemView) {
            super(itemView);

            status = (TextView) itemView.findViewById(R.id.tutor_profile_phone_number);
            dealsid = (TextView) itemView.findViewById(R.id.tutor_profile_phone_number2);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.id1);
            createdat = (TextView) itemView.findViewById(R.id.tutor_profile_phone_number1);
        }
    }
}
