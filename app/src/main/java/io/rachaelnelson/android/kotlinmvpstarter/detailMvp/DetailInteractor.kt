package io.rachaelnelson.android.kotlinmvpstarter.detailMvp

class DetailInteractor {

    private val imageUrlBase = "http://covers.openlibrary.org/b/id/"

    fun fetchImageUrl(coverId: String?) : String? {
        var imageUrl: String? = null
        if (coverId?.length ?: 0 > 0) {
            imageUrl = "$imageUrlBase$coverId-L.jpg"

        }
        return imageUrl
    }
}
