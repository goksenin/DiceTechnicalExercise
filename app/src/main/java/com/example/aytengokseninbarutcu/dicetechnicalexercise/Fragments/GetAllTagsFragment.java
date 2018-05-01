package com.example.aytengokseninbarutcu.dicetechnicalexercise.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aytengokseninbarutcu.dicetechnicalexercise.Adapters.GetAllTagsRecyclerViewAdapter;
import com.example.aytengokseninbarutcu.dicetechnicalexercise.Network.NetworkBuilder;
import com.example.aytengokseninbarutcu.dicetechnicalexercise.R;
import com.example.aytengokseninbarutcu.dicetechnicalexercise.Responses.BaseResponse;
import com.example.aytengokseninbarutcu.dicetechnicalexercise.Responses.GetTagsResponse;
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
 * {@link OnGetAllTagsFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GetAllTagsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GetAllTagsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.recyclerViewGetAllTags)
    RecyclerView recyclerViewGetAllTags;
    LinearLayoutManager mLinearLayoutManager;
    GetAllTagsRecyclerViewAdapter mGetAllTagsRecyclerViewAdapter;
    List<String> listOfPeopleFromAPI;

    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnGetAllTagsFragmentInteractionListener mListener;

    public GetAllTagsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GetAllTagsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GetAllTagsFragment newInstance(String param1, String param2) {
        GetAllTagsFragment fragment = new GetAllTagsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_get_all_tags, container, false);
        CustomProgressBar.showPopup(getContext());
        callGetAllTagsAPI();
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnGetAllTagsFragmentInteractionListener) {
            mListener = (OnGetAllTagsFragmentInteractionListener) context;
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

    public void callGetAllTagsAPI() {

        NetworkBuilder.getAppNetwork().getAllTags().enqueue(new DiceCallback<GetTagsResponse>() {
            @Override
            public void onSuccess(GetTagsResponse response) {

                listOfPeopleFromAPI = response.embeded;
                Log.e("getAllTags", "getAllTags ON SUCCESS");
                recyclerViewGetAllTags.setHasFixedSize(true);
                mLinearLayoutManager = new LinearLayoutManager(getContext());
                recyclerViewGetAllTags.setLayoutManager(mLinearLayoutManager);
                mGetAllTagsRecyclerViewAdapter = new GetAllTagsRecyclerViewAdapter(listOfPeopleFromAPI,mListener,getContext());
                recyclerViewGetAllTags.setAdapter(mGetAllTagsRecyclerViewAdapter);
                CustomProgressBar.hidePopUp();
            }

            @Override
            public void onError(BaseResponse baseResponse) {

                CustomProgressBar.hidePopUp();
                Log.e("getAllTags", "getAllTags ON FAIL");
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
    public interface OnGetAllTagsFragmentInteractionListener {
        // TODO: Update argument type and name
        void onGetAllTagsFragmentInteraction(String tag);
    }
}
