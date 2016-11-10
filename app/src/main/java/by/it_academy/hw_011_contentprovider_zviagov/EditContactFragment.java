package by.it_academy.hw_011_contentprovider_zviagov;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static by.it_academy.hw_011_contentprovider_zviagov.ContactFragment.FIRST_NAME_EXTRA;
import static by.it_academy.hw_011_contentprovider_zviagov.ContactFragment.ID_EXTRA;
import static by.it_academy.hw_011_contentprovider_zviagov.ContactFragment.LAST_NAME_EXTRA;

/**
 * Created by dissa on 03.10.2016.
 */

public class EditContactFragment extends Fragment implements View.OnClickListener {
    private EditText editFirstName, editLastName;
    private int mContactId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fr_edit_contact, container, false);
        editFirstName = (EditText) rootView.findViewById(R.id.add_firstName);
        editLastName = (EditText) rootView.findViewById(R.id.add_lastName);
        Bundle args = getArguments();
        if (args != null) {
            String mEditFirstName = args.getString(FIRST_NAME_EXTRA);
            String mEditLastNAme = args.getString(LAST_NAME_EXTRA);
            mContactId = args.getInt(ID_EXTRA, -1);
            editFirstName.setText(mEditFirstName);
            editLastName.setText(mEditLastNAme);
        }

        Button createContactBtn = (Button) rootView.findViewById(R.id.button);
        createContactBtn.setText(R.string.create_contact);
        createContactBtn.setOnClickListener(this);
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        String firstName = editFirstName.getText().toString();
        String lastName = editLastName.getText().toString();
        if (firstName.equals("") || lastName.equals("")) {
            Toast.makeText(getActivity(), R.string.warning, Toast.LENGTH_SHORT).show();
        } else {
            ContentValues values = new ContentValues();
            values.put(ContactContract.FIRST_NAME, firstName);
            values.put(ContactContract.LAST_NAME, lastName);
            getActivity().getContentResolver().update(ContactContract.CONTENT_URI, values, ContactContract._ID + "=?", new String[]{String.valueOf(mContactId)});
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.delete_contact, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            getActivity().getContentResolver().delete(ContactContract.CONTENT_URI,
                    ContactContract._ID + "=?",
                    new String[]{String.valueOf(mContactId)});
            getFragmentManager().popBackStack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
