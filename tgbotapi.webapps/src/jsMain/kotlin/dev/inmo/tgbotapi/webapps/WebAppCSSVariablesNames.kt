package dev.inmo.tgbotapi.webapps

/**
 * Contains constant definitions for Telegram Web App CSS variable names.
 * These variables are used to style and layout Telegram Web Apps.
 *
 * Note: **These are the raw variable names without the CSS '--' prefix.**
 */
object WebAppCSSVariablesNames {
    /**
     * Contains general Web App related CSS variables.
     * These variables control basic theming and viewport properties.
     */
    object WebApp {
        /** Defines the color scheme of the web app ('dark' or 'light') */
        const val colorScheme = "tg-color-scheme"
        /** Specifies the height of the visible area of the web app */
        const val viewportHeight = "tg-viewport-height"
        /** Represents the height of the visible area that remains constant when the device keyboard is shown */
        const val viewportStableHeight = "tg-viewport-stable-height"
    }

    /**
     * Contains theme-related CSS variables.
     * These variables define the color scheme and visual appearance of various UI elements.
     */
    object ThemeParams {
        /** Background color of the web app */
        const val bgColor = "tg-theme-bg-color"
        /** Main text color */
        const val textColor = "tg-theme-text-color"
        /** Color used for hint text */
        const val hintColor = "tg-theme-hint-color"
        /** Color used for links */
        const val linkColor = "tg-theme-link-color"
        /** Background color for buttons */
        const val buttonColor = "tg-theme-button-color"
        /** Text color for buttons */
        const val buttonTextColor = "tg-theme-button-text-color"
        /** Secondary background color */
        const val secondaryBgColor = "tg-theme-secondary-bg-color"
        /** Background color for headers */
        const val headerBgColor = "tg-theme-header-bg-color"
        /** Background color for the bottom bar */
        const val bottomBarBgColor = "tg-theme-bottom-bar-bg-color"
        /** Color for accent text */
        const val accentTextColor = "tg-theme-accent-text-color"
        /** Background color for sections */
        const val sectionBgColor = "tg-theme-section-bg-color"
        /** Text color for section headers */
        const val sectionHeaderTextColor = "tg-theme-section-header-text-color"
        /** Color for section separators */
        const val sectionSeparatorColor = "tg-theme-section-separator-color"
        /** Color for subtitle text */
        const val subtitleTextColor = "tg-theme-subtitle-text-color"
        /** Color for destructive actions text */
        const val destructiveTextColor = "tg-theme-destructive-text-color"
    }

    /**
     * Contains CSS variables for safe area insets.
     * These variables define the padding needed to avoid system UI elements
     * (like notches, rounded corners, or system bars) on different devices.
     */
    object SafeAreaInset {
        /** Safe area inset for the top of the screen */
        const val top = "tg-safe-area-inset-top"
        /** Safe area inset for the bottom of the screen */
        const val bottom = "tg-safe-area-inset-bottom"
        /** Safe area inset for the left side of the screen */
        const val left = "tg-safe-area-inset-left"
        /** Safe area inset for the right side of the screen */
        const val right = "tg-safe-area-inset-right"
    }

    /**
     * Contains CSS variables for content-specific safe area insets.
     * These variables define the padding needed for the main content area
     * to avoid system UI elements while maintaining proper layout.
     */
    object ContentSafeAreaInset {
        /** Content safe area inset for the top */
        const val top = "tg-content-safe-area-inset-top"
        /** Content safe area inset for the bottom */
        const val bottom = "tg-content-safe-area-inset-bottom"
        /** Content safe area inset for the left side */
        const val left = "tg-content-safe-area-inset-left"
        /** Content safe area inset for the right side */
        const val right = "tg-content-safe-area-inset-right"
    }
}
