package com.yahya.receiptapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.yahya.receiptapp.models.Product
import java.util.*

class AddItemDialogFragment:DialogFragment() {
    internal lateinit var listener: AddItemDialogListener
    interface AddItemDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment, itemAdded: Product)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog{
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;
            val v = inflater.inflate(R.layout.add_item_dialog, null)

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(v)
                // Add action buttons
                .setPositiveButton("Add item"
                ) { dialog, id ->
                    var textInput = v.findViewById<TextInputEditText>(R.id.textInput)
                    val datePicker = v.findViewById<DatePicker>(R.id.datePicker)
                    val timePicker = v.findViewById<TimePicker>(R.id.timePicker)

                    val minute = timePicker.minute
                    val hour = timePicker.hour
                    val day = datePicker.dayOfMonth
                    val month = datePicker.month
                    val year = datePicker.year
                    val c = Calendar.getInstance()
                    c.set(year,month,day,hour,minute)

                    val itemAdded = Product(textInput.text.toString(), Date(),c.time)
                    listener.onDialogPositiveClick(this,itemAdded)
                }
                .setNegativeButton("Cancel"
                ) { dialog, id ->
                    dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            listener = context as AddItemDialogListener
        }catch (e:ClassCastException){
            throw ClassCastException((context.toString() +
                    " must implement AddItemDialogListener"))
        }
    }
}