package com.malmstein.invitine.android.views.textview;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.novoda.notils.caster.Collections;

import java.lang.ref.SoftReference;
import java.util.Hashtable;

public final class FontWriter {

    public enum DCHFonts {

        Roboto("Roboto-Regular.ttf"),
        Roboto_Light("Roboto-Light.ttf"),
        Roboto_Medium("Roboto-Medium.ttf"),
        Roboto_Condensed("RobotoCondensed-Regular.ttf");

        private final String fontsFolder = "fonts/";
        private final String fontName;

        DCHFonts(String fontName) {
            this.fontName = fontName;
        }

        public String getFontName() {
            return fontName;
        }
    }

    private static final Hashtable<String, SoftReference<Typeface>> FONT_CACHE = Collections.newHashTable();

    private final TextView view;
    private final AssetManager assets;
    private TypedArray typedArray;

    private static FontWriter create(TextView view, AttributeSet attributeSet, int[] attributes) {
        Context context = view.getContext();
        AssetManager assets = context.getAssets();
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, attributes);
        return new FontWriter(view, assets, typedArray);
    }

    public static void apply(TextView view, AttributeSet attributeSet, int[] attributes, int styleable) {
        create(view, attributeSet,attributes).setCustomFont(styleable);
    }

    public static FontWriter create(TextView view) {
        Context context = view.getContext();
        AssetManager assets = context.getAssets();
        return new FontWriter(view, assets);
    }

    private FontWriter(TextView view, AssetManager assets, TypedArray typedArray) {
        this.view = view;
        this.assets = assets;
        this.typedArray = typedArray;
    }

    private FontWriter(TextView view, AssetManager assets) {
        this.view = view;
        this.assets = assets;
    }

    public void setCustomFont(int fontId) {
        String customFont = typedArray.getString(fontId);
        setCustomFont(customFont);
        typedArray.recycle();
    }

    private void setCustomFont(String fontName) {
        if (TextUtils.isEmpty(fontName)) {
            return;
        }
        view.setTypeface(getFont(fontName));
    }

    public void setCustomFont(DCHFonts font) {
        view.setTypeface(getFont(font.getFontName()));
    }

    private Typeface getFont(String name) {
        synchronized (FONT_CACHE) {
            if (fontExistsInCache(name)) {
                return getCachedTypeFace(name);
            }

            Typeface typeface = createTypeFace(name);
            saveFontToCache(name, typeface);

            return typeface;
        }
    }

    private boolean fontExistsInCache(String name) {
        return FONT_CACHE.get(name) != null && getCachedTypeFace(name) != null;
    }

    private Typeface getCachedTypeFace(String name) {
        return FONT_CACHE.get(name).get();
    }

    private Typeface createTypeFace(String name) {
        return Typeface.createFromAsset(assets, "fonts/" + name);
    }

    private void saveFontToCache(String name, Typeface typeface) {
        FONT_CACHE.put(name, new SoftReference<Typeface>(typeface));
    }

}
