package by.it_academy.hw_011_contentprovider_zviagov;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.rohit.recycleritemclicksupport.RecyclerItemClickSupport;

/**
 * Created by dissa on 03.10.2016.
 */

public class ContactFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, RecyclerItemClickSupport.OnItemClickListener {
    private MyCursorAdapter mAdapter;
    public static final String FIRST_NAME_EXTRA = "firstName";
    public static final String LAST_NAME_EXTRA = "lastName";
    public static final String ID_EXTRA = "id";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fr_main, container, false);
        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_recycler_view);
        mAdapter = new MyCursorAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        getLoaderManager().initLoader(0, null, this);
        RecyclerItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(this);
        setHasOptionsMenu(true);
        return rootView;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(), ContactContract.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        Cursor cursor = mAdapter.getCursor();
        int firstNameColumnIndex = cursor.getColumnIndex(ContactContract.FIRST_NAME);
        int lastNameColumnIndex = cursor.getColumnIndex(ContactContract.LAST_NAME);
        int idColumnIndex = cursor.getColumnIndex(ContactContract._ID);

        if (cursor.moveToPosition(position)) {
            EditContactFragment editContactFragment = new EditContactFragment();
            Bundle bundle = new Bundle();
            bundle.putString(FIRST_NAME_EXTRA, cursor.getString(firstNameColumnIndex));
            bundle.putString(LAST_NAME_EXTRA, cursor.getString(lastNameColumnIndex));
            bundle.putInt(ID_EXTRA, cursor.getInt(idColumnIndex));
            editContactFragment.setArguments(bundle);

            getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.anim_out2, R.anim.anim_out, R.anim.anim_in2, R.anim.anim_in)
                    .replace(R.id.fragment_container, editContactFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_contact, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.anim_out2, R.anim.anim_out, R.anim.anim_in2, R.anim.anim_in)
                    .replace(R.id.fragment_container, new AddContactFragment())
                    .addToBackStack(null)
                    .commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
