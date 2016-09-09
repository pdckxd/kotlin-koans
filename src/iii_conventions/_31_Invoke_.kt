package iii_conventions

import util.TODO


class Invokable {
    private var _numberOfInvocations: Int = 0
        private set

    operator fun invoke(): Invokable {
        _numberOfInvocations ++
        return this
    }

    fun getNumberOfInvocations(): Int {
        return _numberOfInvocations
    }
}

fun todoTask31(): Nothing = TODO(
    """
        Task 31.
        Change class Invokable to count the number of invocations (round brackets).
        Uncomment the commented code - it should return 4.
    """,
    references = { invokable: Invokable -> })

fun task31(invokable: Invokable): Int {
//    todoTask31()
    return invokable()()()().getNumberOfInvocations()
}
