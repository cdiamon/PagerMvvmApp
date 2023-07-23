import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.task2.R
import com.task2.view.MainActivity
import com.task2.view.utils.waitForView
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainFlowTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun showListAndDateListNoMocks() {
        onView(isRoot()).perform(waitForView(R.id.dateTextView)).check(
            matches(
                allOf(
                    isDisplayed()
                )
            )
        )

        val textView2 = onView(
            allOf(
                withId(R.id.recipe_title), withText("Crispy Fish Goujons "),
                withParent(withParent(IsInstanceOf.instanceOf(androidx.cardview.widget.CardView::class.java))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Crispy Fish Goujons ")))

        val textView3 = onView(
            allOf(
                withId(R.id.recipe_description),
                withText("with Sweet Potato Wedges and Minted Snap Peas"),
                withParent(withParent(IsInstanceOf.instanceOf(androidx.cardview.widget.CardView::class.java))),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("with Sweet Potato Wedges and Minted Snap Peas")))
    }
}
