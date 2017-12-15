package com.zuhlke.zecamp.containers

sealed class Loadable<OutputType> {
    class Loaded<OutputType>(val result: OutputType): Loadable<OutputType>()
    class Error<OutputType>(val error: Throwable): Loadable<OutputType>()
    class Loading<OutputType>: Loadable<OutputType>()

    companion object {
        fun <OutputType> of(result: OutputType): Loadable<OutputType> {
            return Loaded<OutputType>(result)
        }

        fun <OutputType> loading(): Loadable<OutputType> {
            return Loading<OutputType>()
        }

        fun <OutputType> error(throwable: Throwable): Loadable<OutputType> {
            return Error<OutputType>(throwable)
        }
    }
}
