package application.sample.coffeehouse;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class Font_MontserratBoldTextView extends TextView {

    public Font_MontserratBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "Montserrat-Bold.otf"));
    }
}
