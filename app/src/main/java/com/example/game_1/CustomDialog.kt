package com.example.game_1

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment


class CustomDialog(private val window: Window, private val title :String, private val message:String) : AppCompatDialogFragment()

{
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {



        val builder = AlertDialog.Builder(activity!!)
        val ocListener = DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
            hideSystemUI(window)
        }

        builder.setTitle(title)
        builder.setMessage(message)
            .setPositiveButton("OK", ocListener)


        return builder.create()
    }


}

