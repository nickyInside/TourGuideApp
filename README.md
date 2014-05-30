TourGuideApp
============

TourGuideApp guides a user along a walking tour of points of interest on the UBC Point Grey Campus

OVERVIEW:
This app uses a web-based routing service to determine the route between selected points of interest. This routing service is accessed through a REST API. The Java code queries the routing service for a walking tour around the selected points and parses waypoints out of the returned route for display on the map.

KEY COMPONENTS:
-> Routing Service: Implementing the route using a web service that provides a REST (Representational State Transfer) API.

-> Mapping Functionality: Implementing an open street map (similar to Google Maps) using classes from the OSMDroid. Then test the application by deploying it to the Android emulator.

-> Providing Directions to User: Implementing the ability to provide the user with directions to a point of interest when that POI marker is long pressed.

-
