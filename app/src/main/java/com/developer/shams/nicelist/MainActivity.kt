package com.developer.shams.nicelist

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val shuffleSection = Section()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val boringFancyItems = generateFancyItems(6)
        val shuffleFancyItems = generateFancyItems(12)

        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            spanCount = 3
        }

        recycler_view.apply {
            layoutManager = GridLayoutManager(this@MainActivity, groupAdapter.spanCount).apply {
                spanSizeLookup = groupAdapter.spanSizeLookup
            }
            adapter = groupAdapter
        }

        ExpandableGroup(ExpandableHeaderItem("Boring Items"), true).apply {
            add(Section(boringFancyItems))
            groupAdapter.add(this)
        }
        ExpandableGroup(ExpandableHeaderItem("shuffle Items"), false).apply {
            shuffleSection.addAll(shuffleFancyItems)
            add(shuffleSection)
            groupAdapter.add(this)
        }

        sync.setOnClickListener {
            shuffleFancyItems.shuffle()
            shuffleSection.update(shuffleFancyItems)
        }
    }

    private fun generateFancyItems(count: Int): MutableList<FancyItem> {
        val rnd = Random()
        return MutableList(count) {
            val color = Color.argb(
                255
                , rnd.nextInt(256)
                , rnd.nextInt(256)
                , rnd.nextInt(256)
            )
            return@MutableList FancyItem(color, rnd.nextInt(100))
        }
    }
}
