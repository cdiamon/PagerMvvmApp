package com.example.companyApp.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.companyApp.R
import com.example.companyApp.view.ScreenSlidePagerAdapter.Companion.KEY_PAGE
import com.example.companyApp.view.ScreenSlidePagerAdapter.Companion.ScreenType
import com.example.companyApp.view.list.BackgroundItemDecoration
import com.example.companyApp.view.list.ScrollToEndListener
import com.example.companyApp.view.list.DataAdapter
import com.example.companyApp.view.util.NextPageListener
import com.example.companyApp.data.models.ResultStatus
import com.example.companyApp.data.models.ResultStatus.Status.ERROR
import com.example.companyApp.data.models.ResultStatus.Status.LOADING
import com.example.companyApp.data.models.ResultStatus.Status.SUCCESS
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*


@AndroidEntryPoint
class ScreenSlidePageFragment : Fragment() {

    private val carSearchViewModel: CarSearchViewModel by activityViewModels()
    private lateinit var nextPageListener: NextPageListener
    private var screenType = -1
    private lateinit var dataAdapter: DataAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_list, container, false).apply {

        arguments?.let { screenType = it.getInt(KEY_PAGE) }

        searchField.apply {
            visibility = if (screenType == ScreenType.MAIN_TYPES.ordinal) VISIBLE else GONE
        }

        with(carsRv) {
            dataAdapter = DataAdapter(::onPositionClicked)
            adapter = dataAdapter

            addOnScrollListener(ScrollToEndListener {
                if (screenType == ScreenType.MANUFACTURERS.ordinal) onReachedEnd()
            })
            addItemDecoration(BackgroundItemDecoration())

        }
    }

    override fun onResume() {
        super.onResume()
        subscribeUi(dataAdapter)
    }

    private fun subscribeUi(adapter: DataAdapter) {
        when (screenType) {
            ScreenType.MANUFACTURERS.ordinal -> {
                carSearchViewModel.manufacturers.observe(viewLifecycleOwner) { result ->
                    handleStatus(result)
                    if (result?.status == SUCCESS) {
                        result.data?.let {
                            carSearchViewModel.addToCache(it.data)
                        }
                    }
                }
                carSearchViewModel.mansList.observe(viewLifecycleOwner) { list ->
                    adapter.submitList(list.toMutableList())
                }
            }
            ScreenType.MAIN_TYPES.ordinal -> {
                carSearchViewModel.mainTypes.observe(viewLifecycleOwner) { result ->
                    handleStatus(result)
                    if (result?.status == SUCCESS) {
                        result.data?.let {
                            adapter.submitList(it.data)
                        }
                    }
                }
                carSearchViewModel.typesList.observe(viewLifecycleOwner) { list ->
                    adapter.submitList(list.toMutableList())
                }
                carSearchViewModel.fetchMainTypes()
                searchEt.doAfterTextChanged {
                    val query = it.toString().trim()
                    carSearchViewModel.setFilterState(query.lowercase())
                }
            }
            ScreenType.BUILD_DATES.ordinal -> {
                carSearchViewModel.buildDates.observe(viewLifecycleOwner) { result ->
                    handleStatus(result)
                    if (result?.status == SUCCESS) {
                        result.data?.let {
                            adapter.submitList(it.data)
                        }
                    }
                }
                carSearchViewModel.fetchBuildDates()
            }
        }

    }

    private fun handleStatus(result: ResultStatus<Any>?) {
        when (result?.status) {
            LOADING -> {
                progressContainer.visibility = VISIBLE
            }
            SUCCESS -> {
                progressContainer.visibility = GONE
            }
            ERROR -> {
                progressContainer.visibility = GONE
                Toast.makeText(requireContext(), "ERROR: ${result.error}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun onPositionClicked(item: Pair<String, String>) {
        when (screenType) {
            ScreenType.MANUFACTURERS.ordinal -> {
                carSearchViewModel.manufacturer = item
                nextPageListener.onNextPage()
            }
            ScreenType.MAIN_TYPES.ordinal -> {
                carSearchViewModel.mainType = item
                nextPageListener.onNextPage()
            }
            ScreenType.BUILD_DATES.ordinal -> {
                carSearchViewModel.buildDate = item
                showSelectedInfo()
            }
        }
    }

    private fun showSelectedInfo() {
        AlertDialog.Builder(requireContext())
            .apply {
                setTitle("Your car")
                setMessage(
                    "Manufactorer: ${carSearchViewModel.manufacturer?.second}\n" +
                            "Main type: ${carSearchViewModel.mainType?.second}\n" +
                            "Build date: ${carSearchViewModel.buildDate?.second}\n"
                )
                setPositiveButton("Dismiss") { dialog, _ -> dialog.dismiss() }
                setNegativeButton("Exit") { _, _ -> activity?.finish() }
            }
            .create()
            .show()
    }

    private fun onReachedEnd() {
        carSearchViewModel.fetchNextManufacturers()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        nextPageListener = context as NextPageListener
    }
}
