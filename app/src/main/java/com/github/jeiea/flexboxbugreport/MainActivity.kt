package com.github.jeiea.flexboxbugreport

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }
}

class CrashFlexView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
  RecyclerView(context, attrs, defStyle) {

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

  init {
    layoutManager = FlexboxLayoutManager(context).apply {
      flexWrap = FlexWrap.WRAP
      flexDirection = FlexDirection.COLUMN
    }

    adapter = object : RecyclerView.Adapter<ViewHolder>() {

      override fun getItemCount() = 4

      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(View(parent.context).apply {
          val metrics = parent.resources.displayMetrics
          val mw = metrics.widthPixels / 3
          val mh = metrics.heightPixels / 4
          layoutParams = FlexboxLayoutManager.LayoutParams(mw, mh)
          background = context.getDrawable(R.drawable.border)
        })
      }

      override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
          // this makes trouble
          holder.itemView.visibility = View.GONE
        }
      }
    }

  }
}
