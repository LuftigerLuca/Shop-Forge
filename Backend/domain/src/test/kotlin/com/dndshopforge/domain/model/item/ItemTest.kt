package com.dndshopforge.domain.model.item

import com.dndshopforge.domain.result.Problem
import com.dndshopforge.domain.result.Result
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import java.util.UUID

class ItemTest :
    StringSpec({

        "without id should generate random id" {
            val result = Item.of(name = "Sword", category = ItemCategory.WEAPON, rarity = ItemRarity.COMMON, sourceBook = SourceBook.PHB)
            result.shouldBeInstanceOf<Result.Success<Item>>()
            result.data.name.value shouldBe "Sword"
        }

        "with valid id and name should succeed" {
            val id = UUID.randomUUID().toString()
            val result =
                Item.of(
                    id = id,
                    name = "Shield",
                    category = ItemCategory.ARMOR,
                    rarity = ItemRarity.COMMON,
                    sourceBook = SourceBook.PHB,
                )
            result.shouldBeInstanceOf<Result.Success<Item>>()
            result.data.id.value shouldBe id
            result.data.name.value shouldBe "Shield"
        }

        "with blank name should fail" {
            val result = Item.of(name = "", category = ItemCategory.WEAPON, rarity = ItemRarity.COMMON, sourceBook = SourceBook.PHB)
            result shouldBe
                Result.Failure(
                    listOf(Problem("must not be blank", Problem.ProblemType.VALIDATION)),
                )
        }

        "with blank id and blank name should accumulate both problems" {
            val result =
                Item.of(
                    id = "",
                    name = "",
                    category = ItemCategory.WEAPON,
                    rarity = ItemRarity.COMMON,
                    sourceBook = SourceBook.PHB,
                )
            result.shouldBeInstanceOf<Result.Failure>()
            result.problems shouldBe
                listOf(
                    Problem("must not be blank", Problem.ProblemType.VALIDATION),
                    Problem("must not be blank", Problem.ProblemType.VALIDATION),
                )
        }
    })
