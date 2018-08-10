package application.sample.coffeehouse;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Sarthak.Sharma on 19-07-2018.
 */

public class Font_OpenSansRegularTextView extends TextView {

    public Font_OpenSansRegularTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf"));
    }
}
