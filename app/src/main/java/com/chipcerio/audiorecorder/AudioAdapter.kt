package com.chipcerio.audiorecorder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class AudioAdapter(private val filenames: MutableList<String>, private val listener: OnAudioClickListener) : RecyclerView.Adapter<AudioAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false))
    }

    override fun getItemCount(): Int {
        return filenames.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filenames[position])
    }

    fun add(filename: String, position: Int) {
        filenames.add(filename)
        notifyItemChanged(position)
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val textView: TextView = view.findViewById(android.R.id.text1)

        fun bind(filename: String) {
            textView.text = filename
            view.setOnClickListener {
                listener.onAudioClick(filename)
            }
        }
    }

    interface OnAudioClickListener {
        fun onAudioClick(filename: String)
    }
}