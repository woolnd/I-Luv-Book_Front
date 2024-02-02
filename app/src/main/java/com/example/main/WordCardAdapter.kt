import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.main.WordCard
import com.example.main.databinding.ItemWordcardBinding

class WordCardAdapter(private val wordCards: List<WordCard>) : RecyclerView.Adapter<WordCardAdapter.ViewHolder>() {

    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener) {
        this.itemClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemWordcardBinding = ItemWordcardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wordCard = wordCards[position]
        holder.binding.wordWordcardIv.setImageResource(wordCard.imageRes)
        holder.binding.wordBooktitleIv.text = wordCard.title
        holder.binding.wordSuccTv.text = wordCard.successCount.toString()
        holder.binding.wordTotalTv.text = "/${wordCard.totalCount}"
        holder.binding.wordBookStartbtnIv.setImageResource(wordCard.btnimageRes)
    }

    override fun getItemCount(): Int = wordCards.size

    inner class ViewHolder(val binding: ItemWordcardBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.wordBookStartbtnIv.setOnClickListener {
                itemClickListener.onItemClick(adapterPosition)
            }
        }
    }
}
