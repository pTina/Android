package com.example.copy_instagram

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class RvPhotoAdapter(val context: MainActivity, val rvPhotoList:ArrayList<RvPhoto>):
        RecyclerView.Adapter<RvPhotoAdapter.Holder>(){

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val photoImage = itemView.findViewById<ImageView>(R.id.ivPhoto)

        fun bind(rvPhoto: RvPhoto, Context: Context){

            if(rvPhoto.image != ""){
                val resourceId = context.resources.getIdentifier(rvPhoto.image,"drawable",context.packageName)
                photoImage.setImageResource(resourceId)
            }else{
                photoImage.setImageResource(R.mipmap.ic_launcher)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val view = LayoutInflater.from(context).inflate(
            R.layout.item_photo,parent,false
        )

        return Holder(view)
    }

    override fun getItemCount(): Int {
        return rvPhotoList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(rvPhotoList[position], context)
    }




}