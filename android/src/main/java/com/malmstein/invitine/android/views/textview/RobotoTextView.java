package com.malmstein.invitine.android.views.textview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.malmstein.invitine.android.R;


public class RobotoTextView extends TextView {

    public RobotoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        readAndSetFont(attrs);
    }

    public RobotoTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        readAndSetFont(attrs);
    }

    private void readAndSetFont(AttributeSet attrs) {
        if (!this.isInEditMode()) {
            FontWriter.apply(this, attrs, R.styleable.RobotoTextView, R.styleable.RobotoTextView_font);
        }
    }

}
