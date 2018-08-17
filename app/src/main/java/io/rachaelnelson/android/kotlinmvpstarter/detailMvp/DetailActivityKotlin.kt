package io.rachaelnelson.android.kotlinmvpstarter.detailMvp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.view.Menu
import android.widget.ImageView
import android.widget.ShareActionProvider
import android.widget.Toast
import io.rachaelnelson.android.kotlinmvpstarter.BaseActivity
import com.rachaelnelson.android.kotlinmvpstarter.R
import com.squareup.picasso.Picasso

class DetailActivityKotlin : BaseActivity<DetailActivityKotlin>(), DetailPresenter.DetailView {
    private var shareActionProvider: ShareActionProvider? = null
    private var coverId: String? = null
    private var imageUrl: String? = null

    private var imageView: ImageView? = null

    private lateinit var presenter : DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupView()

        val interactionModel = DetailInteractor()
        presenter = DetailPresenter(interactionModel, coverId)

        // establish presenter as lifecycle observer, attach presenter
        lifecycle.addObserver(presenter)
        presenter.attach(this)
    }


    // region DetailPresenter.DetailView implementation
    @SuppressLint("ShowToast")
    override fun displayDefaultLoading() {
        Toast.makeText(this, "Loading!", Toast.LENGTH_LONG)
    }

    override fun showImage(imageUrl: String?) {
        this.imageUrl = imageUrl
        Picasso.with(this).load(imageUrl).placeholder(R.drawable.img_books_loading).into(imageView)
    }
    // endregion

    private fun setupView() {
        // Enable the "Up" button for more navigation options
        actionBar?.setDisplayHomeAsUpEnabled(true)

        // Access the imageView from XML
        imageView = findViewById<ImageView>(R.id.img_cover)

        // 13. unpack the coverId from its trip inside your Intent
        coverId = this.intent.extras.getString("coverID")
    }

    private fun setShareIntent() {

        // Create an Intent with the contents of the TextView
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Book Recommendation!")
        shareIntent.putExtra(Intent.EXTRA_TEXT, imageUrl)

        // Make sure the provider knows
        // it should work with that Intent
        shareActionProvider?.setShareIntent(shareIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        //Inflate the menu
        // this adds items to the action bar if it is presentOnCreate.
        menuInflater.inflate(R.menu.main, menu)

        // Access the Share Item defined in menu XML
        val shareItem = menu!!.findItem(R.id.menu_item_share)

        // Access the object responsible for
        // putting together sharing submenu
        val shareActionProvider = MenuItemCompat.getActionProvider(shareItem)

        setShareIntent()

        return true
    }


}