package com.geekbrains.tests

import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.view.details.ViewDetailsContract
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class DetailsPresenterTest {

    private lateinit var presenter: DetailsPresenter

    @Mock
    private lateinit var viewContract: ViewDetailsContract

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = DetailsPresenter()
        presenter.onAttach(viewContract)
    }

    @Test
    fun onAttach_Test() {
        presenter.onAttach(viewContract)
        val currentView = presenter.getView()

        Assert.assertEquals(currentView, viewContract)
    }

    @Test
    fun onDetach_Test() {
        presenter.onDetach()
        val currentView = presenter.getView()

        Assert.assertEquals(null, currentView)
    }

    @Test
    fun getView_Test() {
        val currentView = presenter.getView()

        Assert.assertEquals(viewContract, currentView)
    }

    @Test
    fun setCounter_Test() {
        val count = presenter.getCount()

        presenter.setCounter(count)

        verify(viewContract, times(1)).setCount(count)
    }

    @Test
    fun onIncrement_Count_Test() {
        val newCount = 1
        presenter.onIncrement()

        assertEquals(newCount, presenter.getCount())
    }

    @Test
    fun onIncrement_View_Test() {
        presenter.onIncrement()

        verify(viewContract, times(1)).setCount(presenter.getCount())
    }

    @Test
    fun onDecrement_Count_Test() {
        val newCount = -1
        presenter.onDecrement()

        assertEquals(newCount, presenter.getCount())
    }

    @Test
    fun onDecrement_View_Test() {
        presenter.onDecrement()

        verify(viewContract, times(1)).setCount(presenter.getCount())
    }

}