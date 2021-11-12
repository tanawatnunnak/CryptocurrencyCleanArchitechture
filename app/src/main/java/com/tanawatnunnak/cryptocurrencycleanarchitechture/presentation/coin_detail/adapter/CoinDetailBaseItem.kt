package com.tanawatnunnak.cryptocurrencycleanarchitechture.presentation.coin_detail.adapter

import com.tanawatnunnak.cryptocurrencycleanarchitechture.data.model.Tag

object CoinDetailType {
    const val TYPE_HEADER = 1
    const val TYPE_DESCRIPTION = 2
    const val TYPE_SECTION_TITLE = 3
    const val TYPE_TAG = 4
    const val TYPE_TEAM = 5
}

sealed class CoinDetailBaseItem(open var id: Int, var type: Int) {
    data class HeaderItem(
        override var id: Int,
        var name: String,
        var isActive: Boolean,
        var rank: Int
    ) : CoinDetailBaseItem(id, CoinDetailType.TYPE_HEADER)

    data class DescriptionItem(
        override var id: Int,
        var description: String
    ) : CoinDetailBaseItem(id, CoinDetailType.TYPE_DESCRIPTION)

    data class SectionTitleItem(
        override var id: Int,
        var title: String
    ) : CoinDetailBaseItem(id, CoinDetailType.TYPE_SECTION_TITLE)

    data class TagItem(
        override var id: Int,
        var tagList: List<Tag>
    ) : CoinDetailBaseItem(id, CoinDetailType.TYPE_TAG)

    data class TeamItem(override var id: Int, var name: String, var jobPosition: String) : CoinDetailBaseItem(id, CoinDetailType.TYPE_TEAM)

}