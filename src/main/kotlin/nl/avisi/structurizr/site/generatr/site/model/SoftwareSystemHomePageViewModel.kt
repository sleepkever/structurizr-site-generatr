package nl.avisi.structurizr.site.generatr.site.model

import com.structurizr.model.SoftwareSystem
import nl.avisi.structurizr.site.generatr.site.GeneratorContext

class SoftwareSystemHomePageViewModel(generatorContext: GeneratorContext, softwareSystem: SoftwareSystem) :
    SoftwareSystemPageViewModel(generatorContext, softwareSystem, Tab.HOME) {
    val content = softwareSystem.documentation.sections
        .minByOrNull { it.order }
        ?.let { MarkdownViewModel(it.content) }
        ?: MarkdownViewModel("# Description${System.lineSeparator()}${softwareSystem.description}")
}
