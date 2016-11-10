package by.it_academy.hw_011_contentprovider_zviagov;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by dissa on 03.10.2016.
 */

public class AddContactFragment extends Fragment implements View.OnClickListener {

    private EditText editFirstName, editLastName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fr_edit_contact, container, false);
        editFirstName = (EditText) rootView.findViewById(R.id.add_firstName);
        editLastName = (EditText) rootView.findViewById(R.id.add_lastName);
        Button addContactBtn = (Button) rootView.findViewById(R.id.button);
        addContactBtn.setText(R.string.create_contact);
        addContactBtn.setOnClickListener(this);
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
            getActivity().getContentResolver().insert(ContactContract.CONTENT_URI, values);
            getFragmentManager().popBackStack();
        }
    }


}
