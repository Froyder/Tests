package com.geekbrains.tests

import android.content.Context
import android.os.Build
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.view.search.MainActivity
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>
    private lateinit var context: Context

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun activity_AssertNotNull() {
        scenario.onActivity {
            TestCase.assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed() {
        TestCase.assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun activityTextView_NotNull() {
        scenario.onActivity {
            val searchEditText = it.findViewById<TextView>(R.id.searchEditText)
            TestCase.assertNotNull(searchEditText)
        }
    }

    @Test
    fun activityElements_AreVisible() {
        scenario.onActivity {
            val searchEditText = it.findViewById<TextView>(R.id.searchEditText)
            TestCase.assertEquals(View.VISIBLE, searchEditText.visibility)

            val toDetailsActivityButton = it.findViewById<Button>(R.id.toDetailsActivityButton)
            TestCase.assertEquals(View.VISIBLE, toDetailsActivityButton.visibility)
        }
    }

    @Test
    fun activityTextView_SetText() {
        scenario.onActivity {
            val searchEditText = it.findViewById<TextView>(R.id.searchEditText)
            searchEditText.setText("text", TextView.BufferType.EDITABLE)

            TestCase.assertEquals("text", searchEditText.text.toString())
        }
    }

    @Test
    fun activityEditText_IsWorking() {
        scenario.onActivity {
            val searchEditText = it.findViewById<TextView>(R.id.searchEditText)
            val testTV = it.findViewById<TextView>(R.id.testTV)

            searchEditText.setText("text", TextView.BufferType.EDITABLE)
            searchEditText.onEditorAction(EditorInfo.IME_ACTION_SEARCH)

            TestCase.assertEquals(testTV.text, searchEditText.text.toString())
        }
    }

    @Test
    fun activityDetailsButton_IsWorking() {
        scenario.onActivity {
            val toDetailsActivityButton = it.findViewById<Button>(R.id.toDetailsActivityButton)
            val testTV = it.findViewById<TextView>(R.id.testTV)

            toDetailsActivityButton.performClick()

            TestCase.assertEquals("Button clicked!", testTV.text)
        }
    }

    @After
    fun close() {
        scenario.close()
    }
}