package com.antoniunix.domain.usescase

import io.reactivex.disposables.Disposable

abstract class UseCase<Params, R : Any> {

    // TODO this must be change by io.reactivex.disposables.CompositeDisposable
    private var disposable: Disposable? = null

    /**
     * Builds an reactive which will be used when executing the current
     * [com.citibanamex.banamexmobile.commons.domain.usecases.UseCase].
     *
     * @param params the parameters (optionals) used to build/execute the use case
     * @return an reactive type
     */
    protected abstract fun buildUseCase(params: Params? = null): R

    /**
     * Dispose from current subscription
     */
    fun dispose() {
        disposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    /**
     * Sets the current subscription
     *
     * @param subscription
     */
    protected fun subscribe(subscription: Disposable? = null) {
        disposable = subscription
    }
}