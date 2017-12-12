/**
 * Created by kaili on 2017-12-11.
 */
package com.example.android.miwok;
public class Word {

	//define translation
	private String mDefaultTranslation;

	//define miwok
	private String mMiwokTranslation;

	//define image resourse
	private int mImageResourceId;

	//constructor
	public Word(String DefaultTranslation, String MiwokTranslation, int imageResourceId) {
		mDefaultTranslation = DefaultTranslation;
		mMiwokTranslation = MiwokTranslation;
		mImageResourceId = imageResourceId;
	}

	public Word(String DefaultTranslation, String MiwokTranslation) {
		mDefaultTranslation = DefaultTranslation;
		mMiwokTranslation = MiwokTranslation;
	}


	//get method
	public String getDefaultTranslation() {
		return mDefaultTranslation;
	}

	public String getMiwokTranslation() {
		return mMiwokTranslation;
	}

	public int imageResourceId() {
		return mImageResourceId;
	}

}
