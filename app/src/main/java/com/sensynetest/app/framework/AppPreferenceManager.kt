package com.sensynetest.app.framework

import android.content.Context
import android.content.SharedPreferences

object AppPreferenceManager {
    private const val NAME = "StarlingTest"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    private val CURRENT_FILTER = "current_filter"

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var currentFilter: Int
        get() = preferences.getInt(CURRENT_FILTER, 0)

        set(value) = preferences.edit {
            it.putInt(CURRENT_FILTER, value)
        }
}