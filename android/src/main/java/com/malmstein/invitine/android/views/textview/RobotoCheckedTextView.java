package com.malmstein.invitine.android.views.textview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

import com.novoda.dch.R;

public class RobotoCheckedTextView extends CheckedTextView {

    public RobotoCheckedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        readAndSetFont(attrs);
    }

    public RobotoCheckedTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        readAndSetFont(attrs);
    }

    private void readAndSetFont(AttributeSet attrs) {
        if (!this.isInEditMode()) {
            FontWriter.apply(this, attrs, R.styleable.RobotoCheckedTextView, R.styleable.RobotoCheckedTextView_font);
        }
    }

}
