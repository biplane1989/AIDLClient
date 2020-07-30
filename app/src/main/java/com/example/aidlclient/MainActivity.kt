package com.example.aidlclient

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import com.example.aidlserver.Candy
import com.example.aidlserver.RemoteService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "abc"
        val SERVER_URI = "com.example.aidlserver"
        val SERVER_ACTION = "aidl.service"
    }

    var iRemoteService: RemoteService? = null

    val mConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {

            Log.e(TAG, "Service has unexpectedly disconnected")

            iRemoteService = RemoteService.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(className: ComponentName) {
            Log.e(TAG, "Service has unexpectedly disconnected")
            iRemoteService = null
        }
    }

    fun initConection() {

        val intent = Intent(RemoteService::class.java.name)
        intent.action = SERVER_ACTION
        intent.setPackage(SERVER_URI)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)

    }

    override fun onStart() {
        super.onStart()
        initConection()
    }

    override fun onStop() {
        super.onStop()
        unbindService(mConnection)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sum(view: View) {
        iRemoteService?.let {
            if (edt_number_one.length() > 0 && edt_number_tow.length() > 0) {
                tv_result.setText(
                    it.sum(
                        edt_number_one.text.toString().toInt(),
                        edt_number_tow.text.toString().toInt()
                    ).toString()
                )
            }
        }
    }

    fun getdata(view: View) {
        iRemoteService?.let {
            val listData: List<String> = it.data
            val data = StringBuffer()
            for (s in listData) {
                data.append(s + "\n")
            }
            tv_data.text = data
        }
    }

    fun getCandy(view: View) {
        iRemoteService?.let {
            val candys: List<Candy> = it.candy
            val data = StringBuffer()
            for (s in candys) {
                data.append(s.name + "\n")
            }
            tv_candy.text = data
        }
    }
}