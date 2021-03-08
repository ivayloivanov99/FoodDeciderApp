package fmi.ivaylo.fooddeciderapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import fmi.ivaylo.fooddeciderapp.R
import fmi.ivaylo.fooddeciderapp.data.Food
import fmi.ivaylo.fooddeciderapp.databinding.ListItemBinding
import fmi.ivaylo.fooddeciderapp.generated.callback.OnClickListener

class MyRecyclerViewAdapter(private val foodList: List<Food>, private val clickListener:(Food) -> Unit) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ListItemBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.list_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(foodList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}

class MyViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(food: Food, clickListener:(Food) -> Unit){
        binding.nameTextView.text = food.name
        binding.listItemLayout.setOnClickListener{
            clickListener(food)
        }
    }
}