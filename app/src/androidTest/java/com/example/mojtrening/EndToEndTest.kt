package com.example.mojtrening

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EndToEndTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun korisnikDodajeTrening() {
        // 1. Unos emaila
        composeTestRule.onNodeWithText("Email").performTextInput("Korisnik")

        // 2. Unos lozinke
        composeTestRule.onNodeWithText("Lozinka").performTextInput("lozinka123")

        // 3. Klik na "Prijava"
        composeTestRule.onNodeWithText("Prijava").performClick()

        // 4. Klik na FAB za dodavanje treninga
        composeTestRule.onNodeWithTag("dodajTreningButton").performClick()

        // 5. Unos vježbe
        composeTestRule.onNodeWithText("Naziv vježbe").performTextInput("Cucnjevi")
        composeTestRule.onNodeWithText("Kilaža (kg)").performTextInput("50")
        composeTestRule.onNodeWithText("Ponavljanja").performTextInput("15")

        // 6. Klik na "Dodaj vježbu"
        composeTestRule.onNodeWithText("Dodaj vježbu").performClick()

        // 7. Klik na "Spremi trening"
        composeTestRule.onNodeWithText("Spremi trening").performClick()

    }
}
