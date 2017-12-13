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
	private int mImageResourceId = NO_IMAGE_PROVIDED;

	private static final int NO_IMAGE_PROVIDED = -1;

	//define audio resource id
	private int mAudioResourceId;

	//constructor
	public Word(String DefaultTranslation, String MiwokTranslation, int imageResourceId) {
		mDefaultTranslation = DefaultTranslation;
		mMiwokTranslation = MiwokTranslation;
		mImageResourceId = imageResourceId;
	}

	public Word(String DefaultTranslation, String MiwokTranslation, int imageResourceId, int AudioResourceId) {
		mDefaultTranslation = DefaultTranslation;
		mMiwokTranslation = MiwokTranslation;
		mImageResourceId = imageResourceId;
		mAudioResourceId = AudioResourceId;
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

	public int getImageResourceId() {
		return mImageResourceId;
	}

	public int getAudioResourceId() {
		return mAudioResourceId;
	}

	/*
	* return whether or not there is an image for this word
	*/
	public boolean hasImage() {
		if (mImageResourceId == NO_IMAGE_PROVIDED) {
			return false;
		}
		return true;
	}

}
