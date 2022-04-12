package com.faiq.smartalarm

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.nfc.Tag
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.sql.Time
import java.time.MonthDay
import java.util.*

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    private var dialogListener : TimeDialogListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dialogListener= context as TimeDialogListener
    }

    override fun onDetach() {
        super.onDetach()
        if (dialogListener != null) dialogListener = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return TimePickerDialog(activity as Context, this, hourOfDay, minute, false)
    }

    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        dialogListener?.onDialogTimeSet("tag",p1,p2)
    }

    interface TimeDialogListener {
        fun onDialogTimeSet(tag: String, hourOfDay: Int, minute: Int)
    }
}