package com.malmstein.invitine.android.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.malmstein.invitine.android.R;
import com.novoda.notils.caster.Views;

public class PictureFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ImageView image = Views.findById(inflater.inflate(R.layout.fragment_invitation_picture, container, false), R.id.fragment_invitation_picure);

        image.setImageResource(R.drawable.photo_01_antelope);

        return image;
    }

}
