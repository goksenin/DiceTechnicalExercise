package com.example.aytengokseninbarutcu.dicetechnicalexercise.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aytengokseninbarutcu.dicetechnicalexercise.R;
import com.example.aytengokseninbarutcu.dicetechnicalexercise.Responses.GetDetailsReponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by aytengokseninbarutcu on 1.05.2018.
 */

public class GetDetailsRecyclerViewAdapter extends RecyclerView.Adapter<GetDetailsRecyclerViewAdapter.GetDetailsViewHolder> {

    private List<GetDetailsReponse.Tag> mDetails;
    private Context mContext;

    public GetDetailsRecyclerViewAdapter(List<GetDetailsReponse.Tag> listOfPeople, Context context) {

        mDetails = listOfPeople;
        mContext = context;

    }

    @Override
    public GetDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person_details, parent, false);
        return new GetDetailsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GetDetailsViewHolder holder, final int position) {

        holder.textViewDetails.setText(mDetails.get(position).Value);
        holder.textViewAuthorName.setText(mDetails.get(position).embeddedDetails.authors.get(0).Name);
        String inputString = mDetails.get(position).embeddedDetails.authors.get(0).CreatedAt;
        SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
        SimpleDateFormat myFormat = new SimpleDateFormat("EEE, MMM d, ''yy");

        try {
            String reformattedStr = myFormat.format(fromUser.parse(inputString));
            holder.textViewCreated.setText("Created: " + reformattedStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.textViewSourceUrl.setText(mDetails.get(position).embeddedDetails.sources.get(0).Url);
        holder.textViewSourceUrl.setMovementMethod(LinkMovementMethod.getInstance());

    }


    @Override
    public int getItemCount() {
        return mDetails.size();
    }

    @OnClick(R.id.textViewSourceUrl)
    public void onViewClicked() {
    }

    public class GetDetailsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewDetails)
        TextView textViewDetails;
        @BindView(R.id.textViewAuthorName)
        TextView textViewAuthorName;
        @BindView(R.id.textViewCreated)
        TextView textViewCreated;
        @BindView(R.id.textViewSourceUrl)
        TextView textViewSourceUrl;

        public GetDetailsViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

}
