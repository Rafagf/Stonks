# Stonks

Stonks is a pet project app to track your favourite stocks.

It uses [Kotlin Multiplatform Mobile (KMM)](https://kotlinlang.org/docs/kmm-overview.html) to share code across iOS and Android.

### KMM library
- Repositories with both local and remote data sources (Data layer).
- UseCases exposed to clients to fetch and combine data for the different app features (Domain layer). 
- Libraries used: [ktor-client](https://ktor.io/docs/client.html), [kotlin-coroutines](https://kotlinlang.org/docs/coroutines-overview.html), [kotlin-serialization](https://github.com/Kotlin/kotlinx.serialization) and [sql-delight](https://github.com/cashapp/sqldelight)


### Android app
- Home screen with faved stocks and search screen to find stocks (Presentation and UI layers).
- Single activity architecture 
- Light/Dark theme
- Libraries used: Stonks shared library, [Compose](https://developer.android.com/jetpack/compose) and [kotlin-coroutines](https://kotlinlang.org/docs/coroutines-overview.html)

| Home screen (Light)      | Home screen (Dark)  | Search screen (Light)      | Search screen (Dark)  |
| ----------- | ----------- | ----------- | ----------- |
| ![stonks-faved-light](https://user-images.githubusercontent.com/6362660/137635185-bc350637-321b-4bef-b656-caa726173689.png)|![stonks-faved-dark](https://user-images.githubusercontent.com/6362660/137635127-0dee3cc9-7924-46e1-914c-0a09fed09c3f.png)| ![stonks-search-light](https://user-images.githubusercontent.com/6362660/137635188-363b6dab-b3ec-4179-a544-309daee5549b.png) |![stonks-search-dark](https://user-images.githubusercontent.com/6362660/137635129-6912cc77-f4f1-4c18-82d2-89332064ff2a.png)

### Next steps
1. Decoupling apps and library into different repositories, updating library dependencies with [Dependabot](https://dependabot.com/)
2. iOS app
