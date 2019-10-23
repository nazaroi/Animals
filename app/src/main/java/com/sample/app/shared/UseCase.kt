package com.sample.app.shared

import androidx.lifecycle.MutableLiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

private const val NUMBER_OF_THREADS = 4

abstract class UseCase<P, R> {

    private var taskScheduler: Scheduler =
        AsyncScheduler

    fun invoke(parameters: P, result: MutableLiveData<Result<R>>) {

        taskScheduler.execute {
            execute(parameters).let { useCaseResult ->
                val success = Result.success(useCaseResult)
                result.postValue(success)}
        }
    }

    protected abstract fun execute(parameters: P): R
}


interface Scheduler {
    fun execute(task: () -> Unit)
}

object AsyncScheduler : Scheduler {
    private val executorService: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

    override fun execute(task: () -> Unit) {
        executorService.execute(task)
    }
}
