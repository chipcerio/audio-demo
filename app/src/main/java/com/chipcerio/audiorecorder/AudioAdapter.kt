package com.chipcerio.audiorecorder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class AudioAdapter() : RecyclerView.Adapter<AudioAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false))
    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private var src: TextView = view.findViewById(android.R.id.text1)

        fun bind() {

        }
    }
}