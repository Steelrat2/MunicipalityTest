package com.tamunicipality.task;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by Dmitriy on 21.06.2017.
 */

public class AlertDialog extends DialogFragment {

    public final static String MESSAGE = "error_message";
    private String error_message = "";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            error_message = bundle.getString(MESSAGE);
        }
        android.app.AlertDialog.Builder adb = new android.app.AlertDialog.Builder(getActivity())
                .setTitle("Error")
                .setCancelable(false)
                .setMessage(error_message)
                .setNeutralButton(getString(android.R.string.ok), (d,w)->System.exit(1));
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black);
        setCancelable(false);
        return adb.create();
    }
}
