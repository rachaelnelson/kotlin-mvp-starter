package io.rachaelnelson.android.kotlinmvpstarter

import android.support.v7.app.AppCompatActivity

open class BaseActivity<V> : AppCompatActivity() {
    var view: V? = null

    private lateinit var presenter: BasePresenter<V>

    fun setPresenter(presenter: BasePresenter<V>) {
        this.presenter = presenter

//        // establish presenter as lifecycle observer, attach presenter
//        lifecycle.addObserver(presenter)
//        presenter.attach(view)

    }
}