package academy.learnprogramming.flickrbrowser

import academy.learnprogramming.flickrbrowser.databinding.ActivityPhotoDetailsBinding
import android.os.Bundle
import android.util.Log
import com.squareup.picasso.Picasso

private val TAG = "PhotoDetailsActivity"

class PhotoDetailsActivity : BaseActivity() {
    private lateinit var binding: ActivityPhotoDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, ".onCreate")
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activateToolbar(true)

        val photo = intent.getSerializableExtra(PHOTO_TRANSFER) as Photo

        binding.photoDetailsLayout.photoTitle.text = resources.getString(R.string.photo_title_text, photo.title)
        binding.photoDetailsLayout.photoTags.text = resources.getString(R.string.photo_tags_text,photo.title)
        binding.photoDetailsLayout.photoAuthor.text = resources.getString(R.string.photo_author_text,photo.author)

        Picasso.with(this).load(photo.link)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder).into(binding.photoDetailsLayout.photoImage)


    }
}