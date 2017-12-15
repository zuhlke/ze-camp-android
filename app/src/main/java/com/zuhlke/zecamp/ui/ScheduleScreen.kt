package com.zuhlke.zecamp.ui

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ContextThemeWrapper
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.zuhlke.zecamp.R
import com.zuhlke.zecamp.model.EventModel
import com.zuhlke.zecamp.model.ScheduleModel
import com.zuhlke.zecamp.ui.abstractions.Screen

class ScheduleAdapter(val data: List<EventModel>): RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.text = data.get(position).name
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(val view: TextView): RecyclerView.ViewHolder(view)
}

class ScheduleScreen(val scheduleModel: ScheduleModel): Screen {
    override fun createView(context: Context): View {
        val recyclerView = RecyclerView(ContextThemeWrapper(context, R.style.ScrollbarRecyclerView))
        recyclerView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayout.VERTICAL

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ScheduleAdapter(scheduleModel.events)
        
        return recyclerView
    }
}