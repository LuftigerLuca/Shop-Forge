package com.dndshopforge.domain.model

import com.dndshopforge.domain.result.Result
import com.dndshopforge.domain.result.map
import com.dndshopforge.domain.result.zip
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
            return idResult.zip(ItemName.of(name)).map { (itemId, itemName) ->
                Item(itemId, itemName)
            }
        }
    }
}
