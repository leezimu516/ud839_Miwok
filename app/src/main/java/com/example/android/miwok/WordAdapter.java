package com.example.android.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kaili on 2017-12-11.
 */

public class WordAdapter extends ArrayAdapter<Word> {

	//define color resource id for this list of word
	private int mColorResourceId;

	public WordAdapter(Activity context, ArrayList<Word> wordAdater, int colorResourceId) {
		super(context, 0, wordAdater);
		mColorResourceId = colorResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View listItemView = convertView;
		if (listItemView == null) {
			listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
		}

		// Get the {@link AndroidFlavor} object located at this position in the list
		Word currentWord = getItem(position);

		// Find the TextView in the list_item.xml layout with the ID miwork_text_view
		TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwork_text_view);
		// Get the miwok tanslation from the current Word object and
		// set this text on the TextView
		miwokTextView.setText(currentWord.getMiwokTranslation());

		// Find the TextView in the list_item.xml layout with the ID default_text_view
		TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
		// Get the default translation from the current Word object and
		// set this text on the TextView
		defaultTextView.setText(currentWord.getDefaultTranslation());

		// Find the ImageView in the list_item.xml
		ImageView imageView = (ImageView) listItemView.findViewById(R.id.listView_image);
		// get the imageRecourceId from the current Word object and
		// set this imageResource on the ImageView
		//Log.d("mytag", currentWord.imageResourceId() + "");
		if (currentWord.hasImage()) {
			imageView.setImageResource(currentWord.imageResourceId());
			imageView.setVisibility(View.VISIBLE);
		} else {
			imageView.setVisibility(View.GONE);
		}

		//set background color
		View textContainer = listItemView.findViewById(R.id.text_container);
		//find the color that the resource id match to
		int color = ContextCompat.getColor(getContext(), mColorResourceId);
		//set the background color of the text container View
		textContainer.setBackgroundColor(color);


		// Return the whole list item layout (containing 2 TextViews and an ImageView)
		// so that it can be shown in the ListView
		return listItemView;
	}
}
