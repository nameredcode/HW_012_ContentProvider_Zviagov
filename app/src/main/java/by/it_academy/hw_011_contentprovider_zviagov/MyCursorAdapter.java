package by.it_academy.hw_011_contentprovider_zviagov;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;

/**
 * Created by dissa on 28.09.2016.
 */

public class MyCursorAdapter extends CursorRecyclerViewAdapter<MyCursorAdapter.ContactViewHolder> {

    private final ArrayList<ContactItem> mData = new ArrayList<>();

    public MyCursorAdapter(Context context) {
        super(context, null);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, Cursor cursor) {
        ColorGenerator generator = ColorGenerator.MATERIAL;
        String letter = String.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(ContactContract.FIRST_NAME)).charAt(0));
        TextDrawable drawable = TextDrawable.builder().buildRect(letter, generator.getRandomColor());
        holder.imageView.setImageDrawable(drawable);
        String firstName = cursor.getString(cursor.getColumnIndexOrThrow(ContactContract.FIRST_NAME));
        String lastName = cursor.getString(cursor.getColumnIndexOrThrow(ContactContract.LAST_NAME));
        holder.firstName.setText(firstName);
        holder.lastName.setText(lastName);

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public Cursor getCursor() {
        return super.getCursor();
    }


    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        final TextView firstName;
        final TextView lastName;
        final ImageView imageView;

        public ContactViewHolder(View itemView) {
            super(itemView);
            firstName = (TextView) itemView.findViewById(R.id.first_name);
            lastName = (TextView) itemView.findViewById(R.id.last_name);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
        }
    }
}
