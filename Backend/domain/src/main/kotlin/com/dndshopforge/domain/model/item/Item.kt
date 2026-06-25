package com.dndshopforge.domain.model.item

import com.dndshopforge.domain.result.Result
import com.dndshopforge.domain.result.buildResult
import com.dndshopforge.domain.result.map
import kotlin.ConsistentCopyVisibility

@ConsistentCopyVisibility
data class Item private constructor(
    val id: ItemId,
    val name: ItemName,
) {
    companion object {
        fun of(
            id: String? = null,
            name: String,
        ): Result<Item> {
            val idResult = id?.let { ItemId.of(it) } ?: Result.Success(ItemId.random())

            return buildResult {
                val itemId = idResult.bind()
                val itemName = ItemName.of(name).bind()

                if (itemId == null || itemName == null) {
                    null
                } else {
                    Item(itemId, itemName)
                }
            }
        }
    }
}
