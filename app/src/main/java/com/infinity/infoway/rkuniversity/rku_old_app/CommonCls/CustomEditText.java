package com.infinity.infoway.rkuniversity.rku_old_app.CommonCls;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by ADMIN on 30-04-2018.
 */

public class CustomEditText extends EditText {

    public CustomEditText(Context context) {
        super(context);

        applyCustomFont(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        //  Typeface customFont = FontCache.getTypeface("SourceSansPro-Regular.ttf", context);
        Typeface font = Typeface.createFromAsset(
                context.getAssets(),
                "fonts/OpenSans-Regular.ttf");
        setTypeface(font);
    }
}