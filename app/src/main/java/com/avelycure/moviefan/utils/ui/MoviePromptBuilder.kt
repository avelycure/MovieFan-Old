package com.avelycure.moviefan.utils.ui

import android.app.Activity
import uk.co.samuelwall.materialtaptargetprompt.ActivityResourceFinder
import uk.co.samuelwall.materialtaptargetprompt.ResourceFinder
import uk.co.samuelwall.materialtaptargetprompt.extras.PromptOptions
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt

/**
 * Class for showing prompts only on first launch
 */
class MoviePromptBuilder (resourceFinder: ResourceFinder) :
    PromptOptions<MoviePromptBuilder>(resourceFinder) {

    private var key: String? = null

    constructor(activity: Activity) : this(ActivityResourceFinder(activity)) {}

    fun setPreferenceKey(key: String?): MoviePromptBuilder {
        this.key = key
        return this
    }

    override fun create(): MaterialTapTargetPrompt? {
        val sharedPreferences = this.resourceFinder
            .context
            .getSharedPreferences("preferences", 0)
        var prompt: MaterialTapTargetPrompt? = null
        // Create the prompt if key is not set or prompt hasn't already been shown
        if (key == null || !sharedPreferences.getBoolean(key, false)) {
            prompt = super.create()
            // Set the prompt as shown if the prompt has been created and key has been set
            if (prompt != null && key != null) {
                sharedPreferences.edit().putBoolean(key, true).apply()
            }
        }
        return prompt
    }
}