package nl.avisi.structurizr.site.generatr.site.model

import assertk.assertThat
import assertk.assertions.containsAll
import kotlin.test.Test

class TableViewModelTest : ViewModelTest() {
    private val pageViewModel = object : PageViewModel(generatorContext()) {
        override val url = "/test-page"
        override val pageSubTitle = "Test page"
    }

    @Test
    fun `header rows`() {
        val viewModel = TableViewModel.create {
            headerRow(headerCell("1"), headerCell("2"), headerCell("3"))
        }

        assertThat(viewModel.headerRows).containsAll(
            TableViewModel.RowViewModel(
                listOf(
                    TableViewModel.TextCellViewModel("1", isHeader = true),
                    TableViewModel.TextCellViewModel("2", isHeader = true),
                    TableViewModel.TextCellViewModel("3", isHeader = true),
                )
            )
        )
    }

    @Test
    fun `header cell with grey text`() {
        val viewModel = TableViewModel.create {
            headerRow(headerCell("1", greyText = true))
        }

        assertThat(viewModel.headerRows).containsAll(
            TableViewModel.RowViewModel(
                listOf(
                    TableViewModel.TextCellViewModel("1", isHeader = true, greyText = true),
                )
            )
        )
    }

    @Test
    fun `body rows`() {
        val viewModel = TableViewModel.create {
            bodyRow(cell("1"), cell("2"), cell("3"))
        }

        assertThat(viewModel.bodyRows).containsAll(
            TableViewModel.RowViewModel(
                listOf(
                    TableViewModel.TextCellViewModel("1", isHeader = false),
                    TableViewModel.TextCellViewModel("2", isHeader = false),
                    TableViewModel.TextCellViewModel("3", isHeader = false),
                )
            )
        )
    }

    @Test
    fun `cell with link`() {
        val viewModel = TableViewModel.create {
            bodyRow(cellWithLink(pageViewModel, "click me", "/decisions"))
        }

        assertThat(viewModel.bodyRows).containsAll(
            TableViewModel.RowViewModel(
                listOf(
                    TableViewModel.LinkCellViewModel(
                        LinkViewModel(pageViewModel, "click me", "/decisions"),
                        isHeader = false
                    )
                )
            )
        )
    }

    @Test
    fun `header cell with link`() {
        val viewModel = TableViewModel.create {
            bodyRow(headerCellWithLink(pageViewModel, "click me", "/decisions"))
        }

        assertThat(viewModel.bodyRows).containsAll(
            TableViewModel.RowViewModel(
                listOf(
                    TableViewModel.LinkCellViewModel(
                        LinkViewModel(pageViewModel, "click me", "/decisions"),
                        isHeader = true
                    )
                )
            )
        )
    }

    @Test
    fun `cell with index`() {
        val viewModel = TableViewModel.create {
            bodyRow(cellWithIndex("1"))
        }

        assertThat(viewModel.bodyRows).containsAll(
            TableViewModel.RowViewModel(
                listOf(
                    TableViewModel.TextCellViewModel(
                        "1",
                        isHeader = false,
                        greyText = false,
                        boldText = true,
                        oneTenthWidth = true
                    )
                )
            )
        )
    }
}
