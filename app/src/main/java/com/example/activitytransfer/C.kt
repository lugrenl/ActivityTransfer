package com.example.activitytransfer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class C : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val data = intent.getStringExtra(B.EXTRA_DATA_B) ?: ""
        setListeners(data)
        Log.i("LifeCycle", "${this::class.simpleName} -> onCreate")
    }

    override fun onStart() {
        super.onStart()
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
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
        onBackPressedCallback.remove()
        Log.i("LifeCycle", "${this::class.simpleName} -> onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LifeCycle", "${this::class.simpleName} -> onDestroy")
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            setResult(
                RESULT_OK,
                Intent().putExtra(EXTRA_DATA_C, "Screen C")
            )
            finish()
        }
    }

    private fun setListeners(data: String) {
        findViewById<TextView>(R.id.title).text = "$data -> ${this::class.simpleName}"
        findViewById<Button>(R.id.next).setOnClickListener {
            val intent = Intent(this, A::class.java)
            startActivity(intent)
        }
        findViewById<TextView>(R.id.path).text = showStackInfo()
    }

    companion object {
        const val EXTRA_DATA_C = "EXTRA_DATA_C"
    }
}