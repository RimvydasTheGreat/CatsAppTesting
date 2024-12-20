package com.test.overview

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.test.overview.ui.screens.login.LoginViewModel
import org.junit.Rule
import org.junit.Test

class LoginWindowAutomatedTests {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun login_with_correct_credentials() {
        val fakeViewModel = LoginViewModel().apply {
            username = ""
            password = ""
            showUsernameError = false
            showPasswordError = false
        }

        composeTestRule.onNodeWithText("Username").performTextInput("user")
        composeTestRule.onNodeWithText("Password").performTextInput("password")

        // Wait for idle to ensure everything is processed before clicking the login button
        composeTestRule.waitForIdle()

        Thread.sleep(5000)  // Sleep for 5 seconds, not necessary for test

        // Find and click the Login button
        composeTestRule.onNode(
            hasText("Login") and hasClickAction()
        )
            .assertIsDisplayed().assertIsEnabled().performClick()

        // Wait for idle to ensure everything is processed before clicking the login button
        composeTestRule.waitForIdle()

        Thread.sleep(5000)  // Sleep for 5 seconds, not necessary for test

        // Ensure we found the text on BreedListScreen
        composeTestRule.onNode(
            hasText("Abyssinian")
        ).assertExists()
    }
}