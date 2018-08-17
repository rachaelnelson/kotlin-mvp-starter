package io.rachaelnelson.android.kotlinmvpstarter

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

abstract class BasePresenter<V> : LifecycleObserver{

    var view: V? = null

    abstract fun fetchOnCreate()
    abstract fun fetchOnResume()

    // Call in Activity.onCreate
    fun attach(view: V) {
        this.view = view
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun detach() {
        view = null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun presentOnCreate() {
        showDefaultLoading()

        launch(UI) {
            fetchOnCreate()
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun presentOnResume() {
        showDefaultLoading()

        launch(UI) {
            fetchOnResume()
        }
    }

    private fun showDefaultLoading() {
        if (view is BaseView?) {
            val baseView: BaseView? = view as BaseView?
            baseView?.displayDefaultLoading()
        }
    }

    // Implement the BaseView
    interface BaseView {
        fun displayDefaultLoading() {}
        fun hideDefaultLoading() {}
        fun displayDefaultError() {}
    }
}




