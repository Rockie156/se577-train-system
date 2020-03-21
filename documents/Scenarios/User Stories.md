# Table of Contents
- [Closed Issues](#Closed-Issues)
  * [Add to Cart - Add Single Itinerary](#Add-to-Cart---Add-Single-Itinerary)
  * [Add to Cart - Indirect Itinerary](#Add-to-Cart---Indirect-Itinerary)
  * [Checkout - Purchase Single Ticket](#Checkout---Purchase-Single-Ticket)
  * [Manage User Information - Remove](#Manage-User-Information---Remove)
  * [Manage User Information - Update](#Manage-User-Information---Update)
  * [Manage User Information - View](#Manage-User-Information---View)
  * [Payment Management - Add](#Payment-Management---Add)
  * [Payment Management - Remove](#Payment-Management---Remove)
  * [Payment Management - View](#Payment-Management---View)
  * [Search - Find Indirect Paths](#Search---Find-Indirect-Paths)
- [Open Issues](#Open-Issues)
  * [Checkout - Multi-trip](#Checkout---Multi-trip)
  * [Checkout - Round Trip](#Checkout---Round-Trip)
  * [Display Itineraries - Items per Page](#Display-Itineraries---Items-per-Page)
  * [Display Itineraries - Sort by Fare / Duration](#Display-Itineraries---Sort-by-Fare-/-Duration)
  * [Display Itineraries - Sort by Train Name](#Display-Itineraries---Sort-by-Train-Name)
  * [Manage Customer - Update Traveler](#Manage-Customer---Update-Traveler)
  * [Manage Customer - View Travellers](#Manage-Customer---View-Travellers)
  * [Manage Itinerary - Add Station](#Manage-Itinerary---Add-Station)
  * [Manage Itinerary - Add route](#Manage-Itinerary---Add-route)
  * [Manage Itinerary - Modify existing itinerary](#Manage-Itinerary---Modify-existing-itinerary)
  * [Manage Order - Change Status](#Manage-Order---Change-Status)
  * [Manage Order - Change Status](#Manage-Order---Change-Status)
  * [Manage Order - Error Message](#Manage-Order---Error-Message)
  * [Manage User Profile - Billing](#Manage-User-Profile---Billing)
  * [Refactor to be setOrder instead of setOrder_Id](#Refactor-to-be-setOrder-instead-of-setOrder_Id)
  * [Search - Filter By Date](#Search---Filter-By-Date)
  * [Search - Multi-City](#Search---Multi-City)
  * [Search - Round Trip](#Search---Round-Trip)
  * [User Authentication - Laptop Sign In](#User-Authentication---Laptop-Sign-In)
  * [User Authentication - Mobile Sign In](#User-Authentication---Mobile-Sign-In)
  * [User Authentication - New Account](#User-Authentication---New-Account)
# Closed Issues
## [Add to Cart - Add Single Itinerary](https://github.com/SaffatHasan/se577-train-system/issues/29)
**GIVEN**
* The traveler does not have any items in their cart
* User has searched for a trip from SCC to SJC

**WHEN**
* A traveler selects "Add To Cart" from the Search Results page for the direct itinerary
  * FROM SJC  
  * TO SCC
  * Departure Time 12:05
  * Arrival Time 12:17

**THEN**
* The traveler is redirected to their cart and can see their cart with one item with the following columns
  * TO Station
  * DEPARTURE time
  * FROM station
  * ARRIVAL time
  * Quantity
  * Price
* The page has a "Checkout" link at the bottom
## [Add to Cart - Indirect Itinerary](https://github.com/SaffatHasan/se577-train-system/issues/59)
**GIVEN**
- The User has searched for valid trips between two stations
- The Search Results page contains at least one itinerary with more than 1 connections 

**WHEN**
- The user selects "Add to Cart" for the indirect Itinerary

**THEN**
- The indirect itinerary is added to their cart
## [Checkout - Purchase Single Ticket](https://github.com/SaffatHasan/se577-train-system/issues/33)
**GIVEN**
- The Traveller has a single, direct itinerary in their cart
- The Traveller hits Checkout and is presented with the checkout page
- The Traveller then enters their billing information

**WHEN**
- The Traveller hits the "Submit" button on the Checkout page

**THEN**
- The Order is saved to the database (linking Customer Input Email to the Itinerary)
- The customer is presented with their order details
## [Manage User Information - Remove](https://github.com/SaffatHasan/se577-train-system/issues/56)
GIVEN
The admin is logged in and navigates to the manage users section

WHEN
The admin selects "Manage Users"
Enters the information to remove a user
The admin clicks remove

THEN
The user is deleted 
## [Manage User Information - Update](https://github.com/SaffatHasan/se577-train-system/issues/57)
GIVEN
The admin is logged in and navigates to the manage users section

WHEN
The admin selects "Manage Users"
Enters the new information for a user

THEN
The user data is update in the database
## [Manage User Information - View](https://github.com/SaffatHasan/se577-train-system/issues/58)
GIVEN
The admin is logged in and navigates to the manage users section

WHEN
The admin selects "Manage Users"

THEN
The admin sees registered
## [Payment Management - Add](https://github.com/SaffatHasan/se577-train-system/issues/37)
GIVEN
The admin is logged in and navigates to the 'Manage Payments' section

WHEN
The admin enters a new payment option 

THEN
Customers are now allowed to pay using the enabled payment option.
## [Payment Management - Remove](https://github.com/SaffatHasan/se577-train-system/issues/38)
**GIVEN**
The admin is logged in and navigates to the "Manage Payments" section.

**WHEN**
Enters the name of the payment option to remove and clicks "Remove"

**THEN**
Customers are no longer allowed to pay using the 
 removed payment option.
## [Payment Management - View](https://github.com/SaffatHasan/se577-train-system/issues/39)
GIVEN
The admin is logged in and navigates to the manage payments

WHEN
The admin selects "Manage Payments"

THEN
The admin sees available options
## [Search - Find Indirect Paths](https://github.com/SaffatHasan/se577-train-system/issues/49)
**GIVEN**
- The user is on the search page
- The user selects "DAV" as their FROM station
- The user selects "DAL" as their TO station

**WHEN**
- The user hits "Submit"

**THEN**
- The user is presented with the following indirect itinerary with stops:
  - DAV -> SAC -> HNF -> LAX -> DAL
# Open Issues
## [Checkout - Multi-trip](https://github.com/SaffatHasan/se577-train-system/issues/74)
GIVEN
The Traveler has a working internet connection
The Traveler has a working connection to the site.

WHEN
The Traveler adds a multi- trip ticket to their cart.
The traveler completes the checkout process for their ticket

THEN
The Traveler gets a confirmation message for their purchase
## [Checkout - Round Trip](https://github.com/SaffatHasan/se577-train-system/issues/73)
GIVEN
The Traveler has a working internet connection
The Traveler has a working connection to the site.

WHEN
The Traveler added a round trip ticket to their cart.
The traveler completes the checkout process for their ticket

THEN
The Traveler gets a confirmation message for their purchase
## [Display Itineraries - Items per Page](https://github.com/SaffatHasan/se577-train-system/issues/19)
GIVEN
The Traveler has a working internet connection
The Traveler has a working connection to the site.

WHEN
The traveler searches for a trip  

THEN
The traveler sees 10 available itineraries per page upon a successful search use case.
## [Display Itineraries - Sort by Fare / Duration](https://github.com/SaffatHasan/se577-train-system/issues/20)
GIVEN
The Traveler has a working internet connection
The Traveler has a working connection to the site.

WHEN
The traveler sorts the displayed itinerary results 

THEN
The traveler can view modified results based on either the lowest fare or shortest trip..

## [Display Itineraries - Sort by Train Name](https://github.com/SaffatHasan/se577-train-system/issues/21)
GIVEN
The Traveler has a working internet connection
The Traveler has a working connection to the site.

WHEN
The traveler filters the displayed itinerary results based on a specific train name.

THEN
The traveler can continue their search
## [Manage Customer - Update Traveler](https://github.com/SaffatHasan/se577-train-system/issues/64)
GIVEN
The admin is logged in to the admin portal
The admin navigates to the 'Manage Customers' section

WHEN
The admin updates the information for a traveler

THEN
This new information is saved to the traveler's profile.
## [Manage Customer - View Travellers](https://github.com/SaffatHasan/se577-train-system/issues/65)
GIVEN
The admin is logged in to the admin portal
The admin navigates to the 'Manage Customers' section

WHEN
The admin sees a list of Travellers
## [Manage Itinerary - Add Station](https://github.com/SaffatHasan/se577-train-system/issues/63)
GIVEN
The admin is logged in to the admin portal
The admin navigates to the 'Manage Itineraries' section

WHEN
The admin adds two new stations in sequential order, the first NY Penn Station, the second is PHL 30th St Station.

THEN
These become available as part of routes and itineraries.
## [Manage Itinerary - Add route](https://github.com/SaffatHasan/se577-train-system/issues/62)
GIVEN
The admin is logged in to the admin portal
The admin navigates to the 'Manage Itineraries' section

WHEN
The admin adds a route between NYP and PHL30 with an existing train as the train. The admin specifies this route runs every weekday at 8 am with an arrival time of 10 am.

THEN
Customers see this change
## [Manage Itinerary - Modify existing itinerary](https://github.com/SaffatHasan/se577-train-system/issues/61)
GIVEN
The admin is logged in to the admin portal
The admin navigates to the 'Manage Itineraries' section

WHEN
The admin modifies the tickets on a route to update the price to $50 for business and $25 for the economy class.

THEN
Customers see this change
## [Manage Order - Change Status](https://github.com/SaffatHasan/se577-train-system/issues/69)
GIVEN
The admin is logged in to the admin portal
The admin navigates to the 'Manage Orders' section

WHEN
The admin changes the status of an order from SPAID to SHIPPED

THEN
The order is updated
## [Manage Order - Change Status](https://github.com/SaffatHasan/se577-train-system/issues/68)
GIVEN
The admin is logged in to the admin portal
The admin navigates to the 'Manage Orders' section

WHEN
The admin changes the status of an order from SHIPPED to REFUNDED.

THEN
The order is updated
## [Manage Order - Error Message](https://github.com/SaffatHasan/se577-train-system/issues/67)
GIVEN
The admin is logged in to the admin portal
The admin navigates to the 'Manage Orders' section

WHEN
The admin finds the order for a given customer
The admin adds  the message “Customer did not receive order. Shipped again.” to the notes of the order.  

THEN
The order is updated
## [Manage User Profile - Billing](https://github.com/SaffatHasan/se577-train-system/issues/14)
**GIVEN**
- The User is logged in
- The User is viewing the Manage Profile page
- The user enters their billing information:
  - Credit Card number
  - Expiration Date
  - Expiration Month

**WHEN**
- The User hits submit

**THEN**
- The User's default billing information is saved
- The User is redirected to their previous page
## [Refactor to be setOrder instead of setOrder_Id](https://github.com/SaffatHasan/se577-train-system/issues/52)
Could be refactored to be setOrder instead of setOrder_Id

_Originally posted by @SaffatHasan in https://github.com/SaffatHasan/se577-train-system/pull/48_
## [Search - Filter By Date](https://github.com/SaffatHasan/se577-train-system/issues/34)
**GIVEN**
- The traveler is on the search page
- The traveler selects a FROM station, a TO station, and selects a date of departure

**WHEN**
- The traveler hits submit

**THEN**
- The traveler is presented with the search results page with itineraries that are valid for the specified date of departure
## [Search - Multi-City](https://github.com/SaffatHasan/se577-train-system/issues/17)
**GIVEN**
- The User is on the Search page
- The User selects the 'multi-city trip' option from the dropdown menu
- The user selects a FROM station and a TO station and a date
- The user then selects 'Add Segment'
- The user selects a FROM station and a TO station and a date for the second segment

**WHEN**
- The User hits Submit

**THEN**
- The user is presented with matching itineraries for each segment with a SELECT button next to each matching itinerary for each segment
## [Search - Round Trip](https://github.com/SaffatHasan/se577-train-system/issues/18)
GIVEN
The Traveler has a working internet connection
The Traveler has a working connection to the site.

WHEN
The traveler searches for round trip tickets from Penn Station to Philadelphia for Friday of 2 months in the future

THEN
The traveler sees results matching their search
## [User Authentication - Laptop Sign In](https://github.com/SaffatHasan/se577-train-system/issues/71)
GIVEN
The Traveler has a working internet connection
The Traveler has a working mobile device

WHEN
The Traveler attempts sign in from a laptop computer

THEN
The sign in is successful and the Traveler is taken to the landing page.
## [User Authentication - Mobile Sign In](https://github.com/SaffatHasan/se577-train-system/issues/70)
GIVEN
The Traveler has a working internet connection
The Traveler has a working mobile device

WHEN
The Traveler attempts sign in from a mobile browser

THEN
The sign in is successful and the Traveler is taken to the landing page.
## [User Authentication - New Account](https://github.com/SaffatHasan/se577-train-system/issues/72)
GIVEN
The Traveler has a working internet connection
The Traveler has a working connection to the site.

WHEN
The Traveler completes the new account workflow

THEN
The the Traveler can accumulate their travel plans. .
