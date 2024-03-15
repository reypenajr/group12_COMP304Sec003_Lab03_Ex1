/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.group12_comp304sec003_lab03_ex1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.group12_comp304sec003_lab03_ex1.database.schedule.AirlineSchedule
import com.example.group12_comp304sec003_lab03_ex1.databinding.AirlineItemBinding
import java.text.SimpleDateFormat
import java.util.Date

class AirlineAdapter(
    private val onItemClicked: (AirlineSchedule) -> Unit
) : ListAdapter<AirlineSchedule, AirlineAdapter.AirlineViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<AirlineSchedule>() {
            override fun areItemsTheSame(oldItem: AirlineSchedule, newItem: AirlineSchedule): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AirlineSchedule, newItem: AirlineSchedule): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirlineViewHolder {
        val viewHolder = AirlineViewHolder(
            AirlineItemBinding.inflate(
                LayoutInflater.from( parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: AirlineViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AirlineViewHolder(
        private var binding: AirlineItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(schedule: AirlineSchedule) {
            binding.airlineNameTextView.text = schedule.airlineName
            binding.arrivalTimeTextView.text = SimpleDateFormat(
                "h:mm a").format(Date(schedule.arrivalTime.toLong() * 1000)
            )
            binding.terminalNumberTextView.text = schedule.terminalNumber
        }
    }
}
