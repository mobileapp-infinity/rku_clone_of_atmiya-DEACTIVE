package com.infinity.infoway.rkuniversity.custom_class;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;

import com.infinity.infoway.rkuniversity.R;

public class TextViewRegularFont extends AppCompatTextView {

    public TextViewRegularFont(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TextViewRegularFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewRegularFont(Context context) {
        super(context);
        init();
    }


    private void init() {
        setTypeface(ResourcesCompat.getFont(getContext(), R.font.ubuntu_r), 1);
    }

}
