package nl.hva.madlevel4.core.exception

sealed class Failure {

    class DbInsertError(val exception: Exception) : BaseFailure()
    class DbQueryError(val exception: Exception) : BaseFailure()
    class ItemNotFoundError() : BaseFailure()

    // Extend this for specific exceptions
    abstract class BaseFailure : Failure()
}