package com.developer.shams.nicelist

import androidx.annotation.ColorInt
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.row_item.*

class FancyItem(
    @ColorInt private val color: Int
    , private val number: Int
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.item_fancy.setCardBackgroundColor(color)
        viewHolder.item_fancy_number.text = number.toString()
    }

    override fun getLayout() = R.layout.row_item

    override fun getSpanSize(spanCount: Int, position: Int) = spanCount / 3
}