package com.task1

import com.task1.models.MenuModel
import com.task1.models.Recipe
import com.task1.models.Subscription

class MenuMocks {

    private val regularSubscriptionMock = Subscription(
        id = 1,
        deliveryDay = "Monday",
        isForFamily = false,
    )

    private val familySubscriptionMock = Subscription(
        id = 2,
        deliveryDay = "Monday",
        isForFamily = true,
    )

    private val noSelectedMock = arrayListOf<Int>()

    private val multipleSelectedMock = arrayListOf(1, 3)

    val menuModelMock = MenuModel(
        subscription = regularSubscriptionMock,
        recipes = listOf(
            Recipe(
                id = 1,
                title = "Spicy Steak",
                tags = listOf("meat", "spicy"),
            ),
            Recipe(
                id = 2,
                title = "Spicy Chicken",
                tags = listOf("meat", "spicy"),
            ),
            Recipe(
                id = 3,
                title = "Spicy Salmon",
                tags = listOf("fish", "spicy"),
            ),
            Recipe(
                id = 4,
                title = "Tofu",
                tags = listOf("tofu", "vegetarian"),
            ),
            Recipe(
                id = 5,
                title = "Bitter Spinach",
                tags = listOf("spinach", "vegetarian"),
            ),
            Recipe(
                id = 6,
                title = "Tender Lamb",
                tags = listOf("meat"),
            ),
            Recipe(
                id = 7,
                title = "Spicy Duck",
                tags = listOf("meat", "spicy"),
            ),
            Recipe(
                id = 8,
                title = "Rabbit",
                tags = listOf("meat"),
            ),
            Recipe(
                id = 9,
                title = "Spicy Deer",
                tags = listOf("meat", "spicy"),
            ),
            Recipe(
                id = 10,
                title = "Kangaroo",
                tags = listOf("meat", "exotic"),
            ),
            Recipe(
                id = 11,
                title = "Crusty Pigeon",
                tags = listOf("meat", "fried"),
            ),
            Recipe(
                id = 12,
                title = "Spicy Potato",
                tags = listOf("potato", "spicy", "vegetarian"),
            ),
            Recipe(
                id = 13,
                title = "Mild Pasta",
                tags = listOf("pasta", "mild", "vegetarian"),
            ),
            Recipe(
                id = 14,
                title = "Sweet Pizza",
                tags = listOf("pizza", "sweet", "vegetarian"),
            )
        ),
        selectedRecipesIds = noSelectedMock,
    )
}