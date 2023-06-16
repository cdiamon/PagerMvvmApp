package com.task1

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MenuRepositoryTest {

    private val mocks = MenuMocks()
    private lateinit var repository: MenuRepository

    @Before
    fun setUp() {
        repository = MenuRepository(mocks.menuModelMock)
    }

    @Test
    fun `select recipe`() {
        val recipe = 2
        val result = repository.selectRecipe(recipe)
        Assert.assertTrue(result.toString(), result.isSuccess)
    }

    @Test
    fun `select multiple recipes`() {
        val recipeIds = listOf(2, 3)
        val result = repository.selectRecipes(recipeIds)
        Assert.assertTrue(result.toString(), result.isSuccess)
    }

    @Test
    fun `select too many recipes`() {
        val recipeIds = listOf(2, 3, 4, 5)
        val result = repository.selectRecipes(recipeIds)
        Assert.assertTrue(result.toString(), result.isFailure)
    }

    @Test
    fun `count selected recipes`() {
        val recipeIds = listOf(2, 3)
        Assert.assertEquals(0, repository.selectedRecipesCount())
        repository.selectRecipes(recipeIds)
        Assert.assertEquals(2, repository.selectedRecipesCount())
    }

    @Test
    fun `unselect recipe`() {
        val recipeIds = listOf(2, 3)
        Assert.assertEquals(0, repository.selectedRecipesCount())
        repository.selectRecipes(recipeIds)
        Assert.assertEquals(2, repository.selectedRecipesCount())
        repository.unselectRecipe(2)
        Assert.assertEquals(1, repository.selectedRecipesCount())
    }

    @Test
    fun `unselect multiple recipes`() {
        val recipeIds = listOf(2, 3)
        Assert.assertEquals(0, repository.selectedRecipesCount())
        repository.selectRecipes(recipeIds)
        Assert.assertEquals(2, repository.selectedRecipesCount())
        repository.unselectRecipes(recipeIds)
        Assert.assertEquals(0, repository.selectedRecipesCount())
    }

    @Test
    fun `unselect non-existing recipes`() {
        val recipeIds = listOf(-2, -3)
        val result = repository.unselectRecipes(recipeIds)
        Assert.assertTrue(result.toString(), result.isFailure)
    }

    @Test
    fun selectedRecipes() {
        val recipeIds = listOf(2, 3)
        repository.selectRecipes(recipeIds)
        val recipes = repository.selectedRecipes().getOrNull()
        val expected = mocks.menuModelMock.recipes.filter { recipeIds.contains(it.id) }

        Assert.assertEquals(expected, recipes)
    }

    @Test
    fun recipesWithTag() {
        val tag = "spicy"
        val recipes = repository.recipesWithTag(tag).getOrNull()
        val expected = mocks.menuModelMock.recipes.filter { it.tags.contains(tag) }

        Assert.assertEquals(expected, recipes)
    }
}