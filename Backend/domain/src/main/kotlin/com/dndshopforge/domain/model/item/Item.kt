package com.dndshopforge.domain.model.item

import com.dndshopforge.domain.result.Result
import com.dndshopforge.domain.result.buildResult
import com.dndshopforge.domain.result.map
import kotlin.ConsistentCopyVisibility

@ConsistentCopyVisibility
data class Item private constructor(
    val id: ItemId,
    val name: ItemName,
    val category: ItemCategory,
    val rarity: ItemRarity,
    val sourceBook: SourceBook,
) {
    companion object {
        fun of(
            id: String? = null,
            name: String,
            category: ItemCategory,
            rarity: ItemRarity,
            sourceBook: SourceBook,
        ): Result<Item> {
            val idResult = id?.let { ItemId.of(it) } ?: Result.Success(ItemId.random())

            return buildResult {
                val itemId = idResult.bind()
                val itemName = ItemName.of(name).bind()

                if (itemId == null || itemName == null) {
                    null
                } else {
                    Item(
                        id = itemId,
                        name = itemName,
                        category = category,
                        rarity = rarity,
                        sourceBook = sourceBook,
                    )
                }
            }
        }
    }
}
