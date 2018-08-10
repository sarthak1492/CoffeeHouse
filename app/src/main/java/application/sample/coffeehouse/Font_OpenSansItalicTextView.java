package application.sample.coffeehouse;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class Font_OpenSansItalicTextView extends TextView {

    public Font_OpenSansItalicTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "OpenSans-Italic.ttf"));
    }

}
