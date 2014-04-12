package com.malmstein.invitine.android.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.malmstein.invitine.android.R;

public class FormFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_form, container, false);
        WebView webView = (WebView) rootView.findViewById(R.id.webView);

        WebSettings webViewSettings = webView.getSettings();
        webViewSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webViewSettings.setJavaScriptEnabled(true);
        webViewSettings.setBuiltInZoomControls(true);
        webViewSettings.setPluginState(WebSettings.PluginState.ON);

        webView.loadData("<iframe src=\"https://docs.google.com/spreadsheet/embeddedform?formkey=dGw3VVpBaFF0bDFXTWc1Y244all5Z2c6MA\" width=\"760\" height=\"1141\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\">Cargando...</iframe>",
                "text/html",
                "utf-8");

        return rootView;
    }

}
