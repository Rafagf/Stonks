# Stonks

Stonks is a pet project app to track your favourite stocks.

It uses Kotlin Multiplatform Mobile to share code across iOS and Android.

### KMM library
- Repositories with both local and remote data sources (Data layer).
- UseCases exposed to clients to fetch and combine data for the different app features. 
- Libraries used: [ktor-client](https://ktor.io/docs/client.html), [kotlin-coroutines](https://kotlinlang.org/docs/coroutines-overview.html), [kotlin-serialization](https://github.com/Kotlin/kotlinx.serialization) and [sql-delight](https://github.com/cashapp/sqldelight)


### Android app
- Home screen with faved stocks and search screen to find stocks.
- Single activity architecture and (simplified, no reducers) MVI pattern
- [Compose](https://developer.android.com/jetpack/compose) for the UI
- Light/Dark theme

