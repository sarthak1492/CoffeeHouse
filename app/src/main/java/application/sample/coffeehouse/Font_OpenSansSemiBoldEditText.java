package application.sample.coffeehouse;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class Font_OpenSansSemiBoldEditText extends EditText {

    public Font_OpenSansSemiBoldEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "OpenSans-Semibold.ttf"));
    }

}
