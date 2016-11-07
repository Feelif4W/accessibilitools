package com.novoda.accessibility;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

public class ActionsAlertDialogCreator {

    private static final int NO_TITLE = 0;

    private final Context context;

    @StringRes
    private final int title;

    private final AlertDialog.Builder dialogBuilder;

    public ActionsAlertDialogCreator(Context context) {
        this(context, NO_TITLE);
    }

    public ActionsAlertDialogCreator(Context context, @StringRes int title) {
        this.context = context;
        this.title = title;
        this.dialogBuilder = new AlertDialog.Builder(context);
    }

    public AlertDialog create(final Actions actions) {
        dialogBuilder.setItems(
                collateActionLabels(actions),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Action action = actions.getAction(which);
                        action.run();
                        dialog.dismiss();
                    }

                }
        );

        if (title != NO_TITLE) {
            dialogBuilder.setTitle(title);
        }

        return dialogBuilder.create();
    }

    private CharSequence[] collateActionLabels(Actions actions) {
        CharSequence[] itemLabels = new CharSequence[actions.getCount()];
        for (int i = 0; i < actions.getCount(); i++) {
            itemLabels[i] = context.getResources().getString(actions.getAction(i).getLabel());
        }
        return itemLabels;
    }

}
