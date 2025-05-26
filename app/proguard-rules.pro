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

#============= my Rules ============

-keepattributes SourceFile,LineNumberTable

# ------------- DAO и Entity classes -----------
-keep class com.example.marvelheros.data.local.** { *; }
-keep class com.example.marvelheros.data.local.dao.** { *; }
-keep class com.example.marvelheros.data.local.entity.** { *; }

# ---------- Room annotations ------------
-keepclassmembers class * {
    @androidx.room.* <methods>;
    @androidx.room.* <fields>;
}

-keepattributes Signature, InnerClasses, EnclosingMethod, *Annotation*, RuntimeVisibleAnnotations

# -------------Moshi ----------------
-keep @com.squareup.moshi.JsonClass class * { *; }
-keep class **JsonAdapter { *; }
-keep class com.squareup.moshi.** { *; }
-keepclassmembers class * {
    @com.squareup.moshi.* <fields>;
    @com.squareup.moshi.* <methods>;
}

# ---------- Kotlin metadata --------
-keep class kotlin.Metadata { *; }

# ------------ Retrofit -------------
-keep interface com.example.marvelheros.data.api.** { *; }
-keepclassmembers interface * {
    @retrofit2.http.* <methods>;
}
-keep interface retrofit2.http.* { *; }
-keep class retrofit2.** { *; }

# ------------ OkHttp ------------
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn retrofit2.**

# ---------- Data Model -----------
-keep class com.example.marvelheros.data.model.** { *; }

# --------- Hilt & Dagger ---------
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class dagger.** { *; }
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory { *; }
-keep class * extends dagger.hilt.internal.GeneratedComponent { *; }

# --------- Room ---------
-keep class androidx.room.** { *; }
-dontwarn androidx.room.paging.**

# --------- ViewModel & SavedStateHandle ---------
-keep class androidx.lifecycle.SavedStateHandle { *; }

# --------- Jetpack Compose ---------
-keep class androidx.compose.** { *; }
-keep class androidx.activity.ComponentActivity { *; }

# --------- Debug logs ---------
-assumenosideeffects class android.util.Log {
    public static int v(...);
    public static int d(...);
    public static int i(...);
    public static int w(...);
    public static int e(...);
}
-keepclassmembers class * {
    public <init>(...);
}
-keepattributes Signature
-keepattributes RuntimeVisibleAnnotations
# =========================================================
-printusage build/outputs/mapping/release/usage.txt