package amagi82.wodcharactersheet;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;


public class CharacterListFragment extends ListFragment {

    private OnFragmentInteractionListener mListener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new CharacterListAdapter(getActivity(), MainActivity.characterArray));
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //Add footer
        //getListView().addFooterView(getActivity().getLayoutInflater().inflate(R.layout.list_footer_character, null));
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(position);
        }
    }

    //Interface for communicating through MainActivity
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(int id);
    }
}
