package nl.hva.madlevel4.core.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import nl.hva.madlevel4.core.exception.Failure
import nl.hva.madlevel4.core.functional.Either

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
        val job = GlobalScope.async(Dispatchers.Default) {
            run(params)
        }
        GlobalScope.launch(Dispatchers.Main) {
            onResult(job.await())
        }
    }

    class None
}