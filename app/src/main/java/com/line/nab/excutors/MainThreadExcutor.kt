package com.line.nab.excutors

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

class MainThreadExcutor : Executor {
    val mainThreadhandler = Handler(Looper.getMainLooper())
    override fun execute(p0: Runnable) {
        mainThreadhandler.post(p0)
    }

}