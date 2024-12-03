# Custom Android Lint Rules

## Overview

This lint rule module enforces two critical code quality standards for Android development:
1. Prevention of hardcoded strings
2. Elimination of deprecated method usage

## Rule 1: No Hardcoded Strings

### Rationale
Hardcoded strings make internationalization and maintenance difficult. By enforcing string resource usage, we ensure:
- Easy localization
- Centralized string management
- Consistent string references across the application

### Allowed Practices
- Use `strings.xml` for all user-facing strings
- Define string constants using `const val` in companion objects or dedicated constant files

### Examples of Compliant Code
```kotlin
// Good: Using string resource
TextView.text = getString(R.string.welcome_message)

// Good: Using string constant
companion object {
    const val WELCOME_MESSAGE = "Welcome to the app"
}
```

### Examples of Non-Compliant Code
```kotlin
// Bad: Hardcoded string
TextView.text = "Welcome to the app"
```

## Rule 2: No Deprecated Methods

### Rationale
Deprecated methods are phased out due to:
- Security concerns
- Performance improvements
- Better alternative implementations
- API evolution

### Enforcement
- Lint will fail the build if any deprecated methods are used
- Developers must migrate to the recommended alternative methods

### Examples of Non-Compliant Code
```kotlin
// Bad: Using deprecated method
view.setOnClickListener { 
    // Deprecated method usage will trigger lint failure 
    oldDeprecatedMethod()
}
```

## Integration

### Prerequisites
- Android Studio
- Custom lint module added to your project

### Configuration
1. Add the lint module to your project's build configuration
2. Ensure lint checks are enabled in your gradle files

### Gradle Configuration Example
```groovy
android {
    lintOptions {
        checkReleaseBuilds false
        abortOnError true
        enable 'NoHardcodedStrings', 'NoDeprecatedMethods'
    }
}
```

## Troubleshooting

### Common Issues
- **Build Fails**: Review the lint report for specific violations
- **False Positives**: Use `@Suppress` annotations sparingly and with team review

### Suppressing Lint Warnings
```kotlin
@Suppress("NoHardcodedStrings")
fun exceptionHandler() {
    // Carefully considered use of hardcoded string
}
```

## Best Practices
- Regularly review and update string resources
- Use constants for repeated string values
- Keep deprecated method usage to an absolute minimum
- Prioritize using the latest recommended API methods

## Contributing
- Report any lint rule exceptions or improvement suggestions to in the issues
- Provide context when requesting exceptions to these rules
