package dependencies

import de.fayard.refreshVersions.core.internal.ArtifactVersionKeyRule
import org.gradle.kotlin.dsl.IsNotADependency

open class DependencyGroup(
    val group: String,
    val rule: ArtifactVersionKeyRule? = null,
    val usePlatformConstraints: Boolean = false
) : IsNotADependency {
    companion object {
        val ALL_RULES = mutableListOf<ArtifactVersionKeyRule>() // FIXME: use it
    }

    init {
        if (rule != null) ALL_RULES.add(rule)
    }

    fun module(module: String): String {
        assert(module.trim() == module)
        assert(module.contains(":").not())
        return "$group:$module" + if (usePlatformConstraints) "" else ":_"
    }
}
