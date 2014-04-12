package com.malmstein.invitine.android.activities;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.malmstein.invitine.android.R;
import com.malmstein.invitine.android.views.SlidingUpPanelLayout;

public class Invitation extends Activity{

    public static final String SAVED_STATE_ACTION_BAR_HIDDEN = "saved_state_action_bar_hidden";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_invitine);

        final SlidingUpPanelLayout layout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        layout.setPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                setActionBarTranslation(layout.getCurrentParalaxOffset());
            }

            @Override
            public void onPanelExpanded(View panel) {

            }

            @Override
            public void onPanelCollapsed(View panel) {

            }

            @Override
            public void onPanelAnchored(View panel) {

            }
        });

        restoreActionBar(savedInstanceState);
    }

    private void restoreActionBar(Bundle savedInstanceState) {
        boolean actionBarHidden = savedInstanceState != null ?
                savedInstanceState.getBoolean(SAVED_STATE_ACTION_BAR_HIDDEN, false): false;
        if (actionBarHidden) {
            getActionBar().hide();
        }
    }

    public void setActionBarTranslation(float y) {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }
        ViewGroup content = ((ViewGroup) findViewById(android.R.id.content).getParent());
        int children = content.getChildCount();
        for (int i = 0; i < children; i++) {
            View child = content.getChildAt(i);
            if (child.getId() != android.R.id.content) {
                if (y <= -actionBarHeight) {
                    child.setVisibility(View.GONE);
                } else {
                    child.setVisibility(View.VISIBLE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        child.setTranslationY(y);
                    } else {
//                        AnimatorProxy.wrap(child).setTranslationY(y);
                    }
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_STATE_ACTION_BAR_HIDDEN, !getActionBar().isShowing());
    }
}
