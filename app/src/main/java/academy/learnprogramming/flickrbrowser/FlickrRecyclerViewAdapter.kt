package academy.learnprogramming.flickrbrowser


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class FlickrImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var thumbnail: ImageView = view.findViewById(R.id.thumbnail)
    var title: TextView = view.findViewById(R.id.title1)
}

class FlickrRecyclerViewAdapter(private var photoList: List<Photo>) :
    RecyclerView.Adapter<FlickrImageViewHolder>() {
    private val TAG = "FlickrViewAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrImageViewHolder {
        Log.d(TAG, ".onCreateViewHolder new view requested")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.browse, parent, false)
        return FlickrImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlickrImageViewHolder, position: Int) {
        Log.d(TAG, ".onBindViewHolder")

        if(photoList.isEmpty()){
            holder.thumbnail.setImageResource(R.drawable.placeholder)
            holder.title.setText(R.string.empty_photo)
        }else {

            // Called by the layout manager when it wants new data in an existing view
            val photoItem = photoList[position]

            // Picasso downloads the image from the url on a background thread and puts it into the image view once its downloaded
            Log.d(TAG, ".Picasso downloads the image from the url on a background thread and puts it into the image view once its downloaded")
            Picasso.with(holder.thumbnail.context)
                .load(photoItem.image)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.thumbnail)

            holder.title.text = photoItem.title
        }
    }

    override fun getItemCount(): Int {
        Log.d(TAG, ".getItemCount")
        return if (photoList.isNotEmpty()) photoList.size else 1
    }

    fun loadNewData(newPhotos: List<Photo>) {
        Log.d(TAG, ".loadnewData")
        photoList = newPhotos
        notifyDataSetChanged()
    }

    fun getPhoto(position: Int): Photo? {
        Log.d(TAG, ".getPhoto")
        return if (photoList.isNotEmpty()) photoList[position] else null
    }

}