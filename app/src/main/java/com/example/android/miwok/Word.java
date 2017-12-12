/**
 * Created by kaili on 2017-12-11.
 */
package com.example.android.miwok;
public class Word {

	//define translation
	private String mDefaultTranslation;

	//define miwok
	private String mMiwokTranslation;

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

}
