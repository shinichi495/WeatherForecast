package com.line.nab.excutors

import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class AppExcutors (
    private val networkIO : Executor,
    private val mainThread : MainThreadExcutor,
    private val diskIO: Executor
) {

    @Inject
    constructor() : this (
        Executors.newFixedThreadPool(AppExcutors.NUMBER_CORE_THREAD_POOL),
        MainThreadExcutor(),
        Executors.newSingleThreadExecutor()
    )
    companion object {
        val NUMBER_CORE_THREAD_POOL = Runtime.getRuntime().availableProcessors()
    }

    fun diskIO(): Executor {
        return diskIO
    }

    fun networkIO(): Executor {
        return networkIO
    }

    fun mainThread(): Executor {
        return mainThread
    }
}

