package com.example.activitytransfer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class A : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setListeners()
        val pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        pref.edit().putString(SHARED_DATA_A, "Screen A").apply()
        Log.i("LifeCycle", "${this::class.simpleName} -> onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("LifeCycle", "${this::class.simpleName} -> onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("LifeCycle", "${this::class.simpleName} -> onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("LifeCycle", "${this::class.simpleName} -> onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("LifeCycle", "${this::class.simpleName} -> onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LifeCycle", "${this::class.simpleName} -> onDestroy")
    }

    private fun setListeners() {
        findViewById<TextView>(R.id.title).text = this::class.simpleName
        findViewById<Button>(R.id.next).setOnClickListener {
            val intent = Intent(this, B::class.java)
            startActivity(intent)
        }
        findViewById<TextView>(R.id.path).text = showStackInfo()
    }

    companion object {
        const val SHARED_DATA_A = "SHARED_DATA_A"
        const val PREFS_NAME = "TestPreferences"
    }
}