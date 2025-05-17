# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#my text


# ======================== ProGuard Rules ========================

# --- WebView JS interface (если используется) ---
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# --- Debug stack traces (по желанию) ---
# Сохраняем line number в stack trace
-keepattributes SourceFile,LineNumberTable

# Скрываем имена файлов (опционально)
#-renamesourcefileattribute SourceFile

# ======================== Main Libraries ========================

# --------- Hilt & Dagger ---------
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class dagger.** { *; }
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory { *; }
-keep class * extends dagger.hilt.internal.GeneratedComponent { *; }

# --------- Retrofit ---------
-keep class retrofit2.** { *; }
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn retrofit2.**

# --------- Moshi ---------
-keepattributes Signature, InnerClasses, RuntimeVisibleAnnotations
-keep class com.squareup.moshi.** { *; }
-keep class kotlin.Metadata { *; }

# Сохраняем адаптеры, если @JsonClass(generateAdapter = true)
-keep @com.squareup.moshi.JsonClass class * {
    *;
}
-keep class **JsonAdapter { *; }

# Сохраняем generic-структуры в моделях
-keepclassmembers class * {
    @com.squareup.moshi.* <fields>;
}

# Сохраняем свои модели (замени на реальный пакет!)
-keep class com.example.marvelheros.data.model.** { *; }
-keep class com.example.marvelheros.data.api.** { *; }

# --------- Room ---------
-keep class androidx.room.** { *; }
-keepclassmembers class * {
    @androidx.room.* <methods>;
    @androidx.room.* <fields>;
}
-dontwarn androidx.room.paging.**

# --------- ViewModel & SavedStateHandle ---------
-keep class androidx.lifecycle.SavedStateHandle { *; }

# --------- Jetpack Compose ---------
-keep class androidx.compose.** { *; }
-keep class androidx.activity.ComponentActivity { *; }

# ======================== Optional ========================

# --------- Debug logs (удалить из релиза) ---------
-assumenosideeffects class android.util.Log {
    public static int v(...);
    public static int d(...);
    public static int i(...);
    public static int w(...);
    public static int e(...);
}
# Сохраняем модели, адаптеры и аннотации Moshi
-keep class com.example.marvelheros.data.model.** { *; }
-keep class **JsonAdapter { *; }
-keep class com.squareup.moshi.** { *; }
-keepclassmembers class * {
    @com.squareup.moshi.* <fields>;
}
-keepattributes RuntimeVisibleAnnotations
-keep class **JsonAdapter { *; }
# =========================================================
-printusage build/outputs/mapping/release/usage.txt