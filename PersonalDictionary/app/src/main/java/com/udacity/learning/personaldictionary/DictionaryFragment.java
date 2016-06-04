package com.udacity.learning.personaldictionary;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link DictionaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DictionaryFragment extends Fragment {


    private static final String TAG = DictionaryFragment.class.getSimpleName();

    public DictionaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DictionaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DictionaryFragment newInstance() {
        DictionaryFragment fragment = new DictionaryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragView = inflater.inflate(R.layout.fragment_dictionary, container, false);
        ListView wordsList = (ListView) fragView.findViewById(R.id.wordsList);

        // Get the ContentResolver which will send a message to the ContentProvider
        ContentResolver resolver = getActivity().getContentResolver();

        // Get a Cursor containing all of the rows in the Words table
        Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);

        if (cursor != null) {
                /*Log.d(TAG, "onCreate: No. of words: " + cursor.getCount());
                StringBuilder builder = new StringBuilder();
                int i = 0;
                builder.append("# of words: ").append(cursor.getCount()).append("\n");
                String str;
                while (cursor.moveToNext()) {
                    str = cursor.getString(cursor.getColumnIndex(UserDictionary.Words._ID)) + "-"
                            + cursor.getString(cursor.getColumnIndex(UserDictionary.Words.WORD)) + "-"
                            + cursor.getString(cursor.getColumnIndex(UserDictionary.Words.FREQUENCY)) + "\n";

                    builder.append(str);
                }
                dictonaryText.setText(builder.toString());*/

            String[] columnsArray = {UserDictionary.Words.WORD, UserDictionary.Words.FREQUENCY};
            int[] textViewsArray = {R.id.item_word, R.id.item_word_freq};
            SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.word_item, cursor, columnsArray, textViewsArray, 0);
            wordsList.setAdapter(cursorAdapter);
        }

        return fragView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
