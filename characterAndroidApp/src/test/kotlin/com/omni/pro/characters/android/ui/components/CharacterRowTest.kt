package com.omni.pro.characters.android.ui.components

import android.os.Build
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.omni.pro.characters.android.CharactersApp
import com.omni.pro.characters.android.MainActivity
import com.omni.pro.characters.android.ui.theme.CharactersAppTheme
import com.omni.pro.characters.domain.CharacterEntity
import com.omni.pro.characters.domain.LocationEntity
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = CharactersApp::class, sdk = intArrayOf(Build.VERSION_CODES.TIRAMISU))
class CharacterRowTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    var onClick: (CharacterEntity) -> Unit = mockk(relaxed = true)

    @Test
    fun testCharacterRow() = runBlocking {
        val character = CharacterEntity(
            id = "1",
            name = "Rick Sanchez",
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            location = LocationEntity(name = "Earth"),
        )

        Robolectric.buildActivity(MainActivity::class.java).use { controller ->
            controller.setup()
            composeTestRule.setContent {
                CharactersAppTheme {
                    CharacterRow(character, onClick = onClick)
                }
            }
            composeTestRule.onNodeWithText("Rick Sanchez").assertIsDisplayed()
            composeTestRule.onNodeWithText("Earth").assertIsDisplayed()
            composeTestRule.onNodeWithText("Rick Sanchez").performClick()
            verify { onClick(character) }
        }

    }
}