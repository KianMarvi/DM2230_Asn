package com.example.dm2230_assn;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;


public class PauseConfirmDialog extends DialogFragment
{
   public static boolean IsShown = false;

    @Override
    public Dialog onCreateDialog(Bundle savedInstance)
    {
        IsShown = true;

        // Use the builder class to create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Confirm Pause?").
                setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which)
                    {
                        // Trigger Pause
                        GameSystem.Instance.SetIsPaused(!GameSystem.Instance.GetIsPaused());
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                // No pause to be triggered
                IsShown = false;
                    }
                });

        return builder.create();
    }

    @Override
    public void onCancel(DialogInterface dialog)
    {
        IsShown = false;
    }
    @Override
    public void onDismiss(DialogInterface dialog)
    {
        IsShown = false;
    }

}
