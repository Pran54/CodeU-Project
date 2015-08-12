package com.codeu.android.codeuproject;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeu.android.codeuproject.data.GameDataContract.GameEntry;

/**
 * Created by Pran on 8/12/15.
 */
public class DetailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    static final String DETAIL_URI = "URI";
    // Info from mUri originally comes from DetailActivity put parcelable
    private Uri mUri;

    private static final int DETAIL_LOADER = 0;

    private static final String[] DETAIL_COLUMNS = {
            GameEntry.TABLE_NAME + "." + GameEntry._ID,
            GameEntry.COLUMN_GAME_ID,       // 1
            GameEntry.COLUMN_GAME_NAME,     // 2
            GameEntry.COLUMN_DECK,          // 3
            GameEntry.COLUMN_RELEASE_DATE,  // 4
            GameEntry.COLUMN_PLATFORMS,     // 5
            GameEntry.COLUMN_IMAGE,         // 6
            GameEntry.COLUMN_GENRES,        // 7
            GameEntry.COLUMN_DEVELOPERS,    // 8
            GameEntry.COLUMN_PUBLISHERS,    // 9
            GameEntry.COLUMN_SIMILAR_GAMES  // 10
    };



    private TextView mNameView;
    private ImageView mGameIconView;
    private TextView mDateView;
    private TextView mGenreView;
    private TextView mDeckView;

    public DetailFragment() {
        //setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(DetailFragment.DETAIL_URI);
        }

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        mNameView = (TextView) rootView.findViewById(R.id.game_name_textview);
        mGameIconView = (ImageView) rootView.findViewById(R.id.game_icon);
        mDateView = (TextView) rootView.findViewById(R.id.game_date_textview);
        mGenreView = (TextView) rootView.findViewById(R.id.game_genre_textview);
        mDeckView = (TextView) rootView.findViewById(R.id.game_deck_textview);
        return rootView;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        if ( null != mUri ) {
            Log.v("TAGG", "here");
            // Now create and return a CursorLoader that will take care of
            // creating a Cursor for the data being displayed.
            return new CursorLoader(
                    getActivity(),
                    mUri,
                    DETAIL_COLUMNS,
                    null,
                    null,
                    null
            );
        }
        Log.v("TAGG", "here2");
        return null;
    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // As if the cursor were already set with the appropriate data

        if (data != null && data.moveToFirst()) {
            // Read weather condition ID from cursor
            int gameId = data.getInt(1);

            String name = data.getString(2);
            mNameView.setText(name);

            // TODO: Figure out how to download the image with the url
            // mGameIconView.setImageResource(data.getString(6));

            // Read date from cursor and update views for day of week and date
            String date = data.getString(4);
            mDateView.setText(date);

            String genre = data.getString(7);
            mGenreView.setText(genre);

            String deck = data.getString(3);
            mDeckView.setText(deck);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) { }


}
