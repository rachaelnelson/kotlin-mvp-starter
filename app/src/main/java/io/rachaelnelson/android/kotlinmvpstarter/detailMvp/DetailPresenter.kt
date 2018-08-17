package io.rachaelnelson.android.kotlinmvpstarter.detailMvp

import io.rachaelnelson.android.kotlinmvpstarter.BasePresenter

internal class DetailPresenter(private val interactionModel: DetailInteractor, private val coverId: String?) : BasePresenter<DetailPresenter.DetailView>() {

    // region BasePresenter overrides
    override fun fetchOnCreate() {
        val imageUrl = interactionModel.fetchImageUrl(coverId)
            view?.showImage(imageUrl)
    }

    override fun fetchOnResume() {
        // Ex: Refresh data on resume
    }
    // endregion

    // region DetailPresenter methods
    fun fetchOnSwipeRefresh() {
        // check the db to see if any data has changed
    }
    // endregion

    interface DetailView : BaseView {
        fun showImage(imageUrl: String?)
    }
}
