# BarKeep Core Module

This is the Core module of the BarKeep app.\
The core module exposes a REST api to handle all interaction with database.

It uses Spring boot to serve the API.

Spring security is implemented with **Basic Authentication**.\
*This is not safe from Man-In-The-Middle attacks when not on https*

The database is a H2 database and handled by Hibernate and Spring Data JPA.\
This is used to make persistence easier and safer, and handles relations between our entities.
