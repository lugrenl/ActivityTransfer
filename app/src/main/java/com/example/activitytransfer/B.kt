package com.example.activitytransfer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class B : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val data = getSharedPreferences(A.PREFS_NAME, MODE_PRIVATE)
            .getString(A.SHARED_DATA_A, "") ?: ""
        setListeners(data)
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

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val resultData = data?.getStringExtra(C.EXTRA_DATA_C)
            findViewById<TextView>(R.id.title).text = "$resultData -> ${this::class.simpleName}"
        }
    }

    private fun setListeners(data: String) {
        findViewById<TextView>(R.id.title).text = "$data -> ${this::class.simpleName}"
        findViewById<Button>(R.id.next).setOnClickListener {
            val intent = Intent(this, C::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            intent.putExtra(EXTRA_DATA_B, "Screen B")
            startForResult.launch(intent)
            //startActivityForResult(intent, REQUEST_CODE_C)
        }
        findViewById<TextView>(R.id.path).text = showStackInfo()
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_CODE_C && resultCode == RESULT_OK) {
//            val result = data?.getStringExtra(C.EXTRA_DATA_C) ?: "Empty"
//            findViewById<TextView>(R.id.title).text = "$result -> ${this::class.simpleName}"
//        } else return
//    }


    companion object {
        const val EXTRA_DATA_B = "EXTRA_DATA_B"
        //const val REQUEST_CODE_C = 1
    }
}