package com.app.schoolapplication.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.schoolapplication.MainCoRoutineRule
import com.app.schoolapplication.domain.model.School
import com.app.schoolapplication.repository.FakeRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SchoolViewModelTest {

    @get:Rule
    val coroutineRule = MainCoRoutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var schoolViewModel : SchoolViewModel
    private lateinit var fakeRepository: FakeRepository
    private lateinit var schoolsList : List<School>

    @Before
    fun setUp() {
        schoolsList = (1..20).map {
            School(
                schoolName = "schoolName$it",
                dbn = "dbn$it",
                readingScore = it*10,
                writingScore = it*20,
                mathScore = it*30,
            )
        }.shuffled()

        fakeRepository = FakeRepository()
        fakeRepository.schoolsToInsert = schoolsList
        schoolViewModel = SchoolViewModel(fakeRepository)

        runBlocking {
            fakeRepository.getSchoolsList(false)
        }
    }

    @Test
    fun `Order schools by name ascending, correct order`() {
        schoolViewModel.sortByNameAscending()

        for(i in 0..schoolViewModel.viewState.value.schools.size - 2) {
            assertThat(schoolViewModel.viewState.value.schools[i].schoolName)
                .isLessThan(schoolViewModel.viewState.value.schools[i+1].schoolName)
        }
    }

    @Test
    fun `Order schools by name descending, correct order`() {
        schoolViewModel.sortByNameDescending()

        for(i in 0..schoolViewModel.viewState.value.schools.size - 2) {
            assertThat(schoolViewModel.viewState.value.schools[i].schoolName)
                .isGreaterThan(schoolViewModel.viewState.value.schools[i+1].schoolName)
        }
    }

    @Test
    fun `List of Schools is properly mapped to the state`() {
        schoolViewModel.getSchoolsList(false)
        coroutineRule.dispatcher.scheduler.advanceUntilIdle()
        assertThat(schoolViewModel.viewState.value.schools.size)
            .isEqualTo(20)
    }
}