package com.example.aytengokseninbarutcu.dicetechnicalexercise.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.aytengokseninbarutcu.dicetechnicalexercise.Fragments.GetAllTagsFragment;
import com.example.aytengokseninbarutcu.dicetechnicalexercise.R;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aytengokseninbarutcu on 1.05.2018.
 */

public class GetAllTagsRecyclerViewAdapter extends RecyclerView.Adapter<GetAllTagsRecyclerViewAdapter.GetAllTagsViewHolder> {


    private List<String> mListOfPeople;
    private GetAllTagsFragment.OnGetAllTagsFragmentInteractionListener mGetAllTagsFragmentListener;
    private Context mContext;

    public GetAllTagsRecyclerViewAdapter(List<String> listOfPeople, GetAllTagsFragment.OnGetAllTagsFragmentInteractionListener listener, Context context) {

        mListOfPeople = listOfPeople;
        mGetAllTagsFragmentListener = listener;
        mContext = context;

    }

    @Override
    public GetAllTagsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
        return new GetAllTagsRecyclerViewAdapter.GetAllTagsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GetAllTagsViewHolder holder, final int position) {

        holder.textViewGetAllTags.setText(mListOfPeople.get(position));
        holder.linearLayoutGetAllTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetAllTagsFragmentListener.onGetAllTagsFragmentInteraction(mListOfPeople.get(position));
            }
        });
    }


    @Override
    public int getItemCount() {
        return mListOfPeople.size();
    }

    public class GetAllTagsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewGetAllTags)
        TextView textViewGetAllTags;
        @BindView(R.id.imageViewGetAllTags)
        ImageView imageViewGetAllTags;
        @BindView(R.id.linearLayoutGetAllTags)
        LinearLayout linearLayoutGetAllTags;

        public GetAllTagsViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

}
