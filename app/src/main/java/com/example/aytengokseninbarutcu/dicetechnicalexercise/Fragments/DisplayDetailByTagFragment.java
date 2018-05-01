package com.example.aytengokseninbarutcu.dicetechnicalexercise.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.aytengokseninbarutcu.dicetechnicalexercise.Adapters.GetDetailsRecyclerViewAdapter;
import com.example.aytengokseninbarutcu.dicetechnicalexercise.Network.NetworkBuilder;
import com.example.aytengokseninbarutcu.dicetechnicalexercise.R;
import com.example.aytengokseninbarutcu.dicetechnicalexercise.Responses.BaseResponse;
import com.example.aytengokseninbarutcu.dicetechnicalexercise.Responses.GetDetailsReponse;
import com.example.aytengokseninbarutcu.dicetechnicalexercise.Utils.CustomProgressBar;
import com.example.aytengokseninbarutcu.dicetechnicalexercise.Utils.DiceCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnDisplayDetailByTagFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DisplayDetailByTagFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayDetailByTagFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TAG = "tag";
    @BindView(R.id.recyclerViewGetDetails)
    RecyclerView recyclerViewGetDetails;
    @BindView(R.id.buttonShowMore)
    Button buttonShowMore;
    private List<GetDetailsReponse.Tag> mPersonDetailsFromAPI;
    private LinearLayoutManager mLinearLayoutManager;
    private GetDetailsRecyclerViewAdapter mGetDetailsRecyclerViewAdapter;
    private int totalNumOfPages = 0;
    private float chuncksize;
    private int count = 1;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mPersonTag;

    private OnDisplayDetailByTagFragmentInteractionListener mListener;

    public DisplayDetailByTagFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment DisplayDetailByTagFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DisplayDetailByTagFragment newInstance(String tag) {
        DisplayDetailByTagFragment fragment = new DisplayDetailByTagFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TAG, tag);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPersonTag = getArguments().getString(ARG_TAG);
        }
        mPersonDetailsFromAPI = new ArrayList<GetDetailsReponse.Tag>();
        CustomProgressBar.showPopup(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_display_detail_by_tag, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        getTagAPI(mPersonTag, count);
        buttonShowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("TOTAL NUM OF PAGES COUNT" , totalNumOfPages + "!!!!" + count);
                if(count < totalNumOfPages){
                    count ++;
                    CustomProgressBar.showPopup(getContext());
                    getTagAPI(mPersonTag,count);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDisplayDetailByTagFragmentInteractionListener) {
            mListener = (OnDisplayDetailByTagFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void getTagAPI(String tag, int pageNumber) {

        NetworkBuilder.getAppNetwork().getDetailByTag(tag,pageNumber).enqueue(new DiceCallback<GetDetailsReponse>() {

            @Override
            public void onSuccess(GetDetailsReponse response) {

                if(count == 1){
                    chuncksize = (response.total + response.count + 1) / response.count;
                    totalNumOfPages = (int) Math.ceil(chuncksize);
                }
                Log.e("getDetailByTag", "getDetailByTag ON SUCCESS");
                mPersonDetailsFromAPI.addAll(response.embedded.tags);
                recyclerViewGetDetails.setHasFixedSize(true);
                mLinearLayoutManager = new LinearLayoutManager(getContext());
                recyclerViewGetDetails.setLayoutManager(mLinearLayoutManager);
                mGetDetailsRecyclerViewAdapter = new GetDetailsRecyclerViewAdapter(mPersonDetailsFromAPI,getContext());
                recyclerViewGetDetails.setAdapter(mGetDetailsRecyclerViewAdapter);

                Log.e("Total Num of PAges", totalNumOfPages + "!!");
                CustomProgressBar.hidePopUp();

            }

            @Override
            public void onError(BaseResponse baseResponse) {

                CustomProgressBar.hidePopUp();
                Log.e("getDetailByTag", "getDetailByTag ON ERROR");
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnDisplayDetailByTagFragmentInteractionListener {
        // TODO: Update argument type and name
        void onDisplayDetailByTagFragmentInteraction();
    }
}
