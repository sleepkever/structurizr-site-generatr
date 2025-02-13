package nl.avisi.structurizr.site.generatr.site.views

import kotlinx.html.HTML
import nl.avisi.structurizr.site.generatr.site.model.SoftwareSystemHomePageViewModel

fun HTML.softwareSystemHomePage(viewModel: SoftwareSystemHomePageViewModel) {
    softwareSystemPage(viewModel) {
        markdown(viewModel, viewModel.content)
    }
}
