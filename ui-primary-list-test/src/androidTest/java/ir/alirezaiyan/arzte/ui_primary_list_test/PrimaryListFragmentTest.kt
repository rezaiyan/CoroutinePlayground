package ir.alirezaiyan.arzte.ui_primary_list_test

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import ir.alirezaiyan.arzte.androidtest_sdk.FragmentTestRule
import ir.alirezaiyan.arzte.androidtest_sdk.TestApplication
import ir.alirezaiyan.arzte.core.entity.Doctor
import ir.alirezaiyan.arzte.core.utils.State
import ir.alirezaiyan.arzte.core.utils.ViewStateStore
import ir.alirezaiyan.arzte.testdata.TestData.DOCTOR_1
import ir.alirezaiyan.arzte.testdata.TestData.DOCTOR_2
import ir.alirezaiyan.arzte.testdata.TestData.TEST_DISPATCHER
import ir.alirezaiyan.arzte.ui_primary_list.*
import it.cosenonjaviste.daggermock.DaggerMock
import it.cosenonjaviste.daggermock.interceptor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author Ali (alirezaiyann@gmail.com)
 * @since 3/30/2020 9:28 PM.
 */
class PrimaryListFragmentTest {

    @get:Rule
    val fragmentRule = FragmentTestRule<Unit>(R.navigation.primary_list_nav_graph, R.id.primaryList) { Bundle() }

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val viewStateStore by lazy {
        ViewStateStore(PrimaryListResponse(), CoroutineScope(Dispatchers.Main), TEST_DISPATCHER)
    }

    private val viewModel = mock<PrimaryListViewModel> {
        on(it.state) doAnswer { viewStateStore }
    }

    @Before
    fun setUp() {
        val app = ApplicationProvider.getApplicationContext<TestApplication>()
        app.init(DaggerMock.interceptor(this))
    }

    @Test
    fun testLoading() {
        fragmentRule.launchFragment(Unit)

        viewModel.state.dispatchState(PrimaryListResponse(state = State.Loading))

        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.retry)).check(matches(not(isDisplayed())))
    }

    @Test
    fun testValueWhileLoading() {
        fragmentRule.launchFragment(Unit)

        viewModel.state.dispatchState(PrimaryListResponse(state = State.Loading))

        viewModel.state.dispatchState(PrimaryListResponse(state = State.Success(DoctorsViewState(listOf(
            DOCTOR_1, DOCTOR_2)))))

        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
    }

    @Test
    fun testLoadMoreList() {
        fragmentRule.launchFragment(Unit)
        val list = mutableListOf<Doctor>()
        repeat(20) { list.add(DOCTOR_1) }

        viewModel.state.dispatchState(PrimaryListResponse(state = State.Success(DoctorsViewState(list))))

        onView(withId(R.id.componentList))
            .perform(RecyclerViewActions.scrollToPosition<VerticalDoctorViewHolder>(20))

        verify(viewModel).loadDoctors()
    }
}