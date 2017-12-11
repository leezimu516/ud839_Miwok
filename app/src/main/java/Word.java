import com.example.android.miwok.MainActivity;

/**
 * Created by kaili on 2017-12-11.
 */

public class Word {

	//define translation
	private String mDefaultTranslation;

	//define miwok
	private String mMewokTranslation;

	public Word(String DefaultTranslation, String MewokTranslation) {
		mDefaultTranslation = DefaultTranslation;
		mMewokTranslation = MewokTranslation;
	}


	//get method
	public String getDefaultTranslation() {
		return mDefaultTranslation;
	}

	public String getMewokTranslation() {
		return mMewokTranslation
	}

}
