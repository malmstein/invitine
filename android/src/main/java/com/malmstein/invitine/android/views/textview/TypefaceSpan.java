package com.malmstein.invitine.android.views.textview;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.util.LruCache;

/**
 * Style a {@link Spannable} with a custom {@link android.graphics.Typeface}.
 *
 * @author Tristan Waddington
 */
public class TypefaceSpan extends MetricAffectingSpan {
      /** An <code>LruCache</code> for previously loaded typefaces. */
    private static LruCache<String, Typeface> sTypefaceCache =
            new LruCache<String, Typeface>(12);

    private Typeface mTypeface;

    /**
     * Load the {@link android.graphics.Typeface} and apply to a {@link Spannable}.
     */
    public TypefaceSpan(Context context, String typefaceName) {
        mTypeface = sTypefaceCache.get(typefaceName);
 
        if (mTypeface == null) {
            mTypeface = Typeface.createFromAsset(context.getApplicationContext()
                    .getAssets(), String.format("fonts/%s", typefaceName));
 
            // Cache the loaded Typeface
            sTypefaceCache.put(typefaceName, mTypeface);
        }
    }
 
    @Override
    public void updateMeasureState(TextPaint p) {
        p.setTypeface(mTypeface);
        
        // Note: This flag is required for proper typeface rendering
        p.setFlags(p.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }
 
    @Override
    public void updateDrawState(TextPaint tp) {
        tp.setTypeface(mTypeface);
        
        // Note: This flag is required for proper typeface rendering
        tp.setFlags(tp.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }
}