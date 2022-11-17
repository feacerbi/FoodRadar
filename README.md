
Food Radar
==================

## Preliminary Considerations

- No user login will be necessary or user specific data (security + privacy).
- The user's favorite restaurants can be saved locally and then compared with the restaurants coming from the API using a unique identifier.
- The favorites are device specific and not user specific.
- No need to save the restaurants results locally, since they need to be updated constantly from the server.

## System Design

A draft of the project's system design can be found [here](system_design.jpeg).

## Services

- Restaurant Service: retrieve the list of restaurants, given the current location.
    - Remote data source fetching from Wolt server.
    - No local data source.
    - Raw model: Restaurant(id, name, shortDescription, imageUrl).
- Location Service: provide the current user's location.
    - Local data source fetching from fixed provided list of values in memory.
    - Updates every 10 seconds to simulate a real live location.
- Favorites Service: provide which restaurants are the favorites.
    - Local data source fetching from a local DB to persist app restarts.

## Modules

- App: gathers dependencies and launches the app.
- Core: hold the business logic of the app.
- Data: provide the actual data from external sources.
- Feature: self contained user-facing screens and flows.

## Architecture

- MVVM for the presentation layer with a single entry point and states provided by a Flow.
- Repositories + Data Sources for the domain/data layers, following a clean dependencies approach.
- Compose for the UI layer, using a reactive state and state holder, taking lifecycle into consideration.
- UseCases as the smallest unit of business logic, completely in Kotlin (no platform dependencies).
- Mappers on each layer's boundaries, to reduce coupling.

## Libraries

- Version Catalog to list and provide all libraries and versioning.
- Kotlin Serialization for json parsing when fetching the the remote server.
- Hilt to provide all dependencies and injections.
- Room as the Database.
- Coil for async image loading.