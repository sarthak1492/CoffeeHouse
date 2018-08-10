package application.sample.coffeehouse;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

public class Font_OpenSansSemiBoldTextView extends TextView {

    public Font_OpenSansSemiBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "OpenSans-Semibold.ttf"));
    }
}
