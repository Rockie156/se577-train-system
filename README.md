# Train Demo

Train Demo is a web application that simulates planning a trip and purchasing tickets for a train or bus ride. It uses real Amtrak data retrieved via a [FOIA request](https://www.muckrock.com/foi/united-states-of-america-10/machine-readable-amtrak-schedules-fares-and-stoproute-alignments-25904/). It is only partially implemented. The remaining features are left as an exercise.

## Getting Started

1. Fork this repository.

2. Clone your forked repo.

3. Download the latest version of [Postgres](https://www.postgresql.org/download/) for your OS. Install with the default options and remember the admin password you set.

4. Open pgAdmin 4 (this comes with Postgres) and create a new database called "TrainDemo".

5. Unzip `amtrak_sql.zip` and run `init_tables.sql` then `init_data.sql` on your new database. This populates our database with some initial tables and data. The easiest way to run queries is with Query Tool (`Tools > Query Tool`) in pgAdmin 4.

6. Update `src/main/resources/application.properties` with your Postgres password. Make sure not to commit changes to this file to source control.

7. Add your GitHub `client-id` and `client-secret` to your `application.properites`. See the "Authentication" section.

8. Run the project in IntelliJ, Eclipse, or on the command line with
    ```
    mvnw clean package
    java -jar target/TrainDemo-0.0.1-SNAPSHOT.jar
    ```

9. Visit `localhost:8080` in a browser.

## Authentication

Included already is authentication using GitHub. For more info on how this works see [here](https://spring.io/guides/tutorials/spring-boot-oauth2/). You will have to add a new app to your GitHub account before you can run this application.

> To use GitHub’s OAuth 2.0 authentication system for login, you must first [Add a new GitHub app](https://github.com/settings/developers).
> 
> Select "New OAuth App" and then the "Register a new OAuth application" page is presented. Enter an app name and description. Then, enter your app’s home page, which should be http://localhost:8080, in this case. Finally, indicate the Authorization callback URL as http://localhost:8080/login/oauth2/code/github and click Register Application.
> 
> The OAuth redirect URI is the path in the application that the end-user’s user-agent is redirected back to after they have authenticated with GitHub and have granted access to the application on the Authorize application page.
 
Don't forget to set the `client-id` and `client-secret` in your `application.properites`. Do not commit these keys to source control. 

## Technology

Below are most of the technologies used in this project. This project intentionally has much of the same tech stack as Shopizer.

- Java - Most people know Java already. Using Java has the added benefit of allowing us to analyze our project with depends and DV8.

- Spring Boot - Spring Boot is a popular and opinionated web framework for Java.

- PostgreSQL - A robust, open-source relational database with excellent documentation.

- Hibernate - A full Object Relational Mapper (ORM). This is what powers our Repository pattern. (However, this kind of abstraction is not without it's issues. See [here](https://en.wikipedia.org/wiki/Object-relational_impedance_mismatch).)

- Java Servlet Pages (JSP) - This provides server-side templating so our controllers can respond directly with HTML. This is the easiest way to get started. You are free to switch to a more modern templating system like Thymeleaf or switch entirely to a Single-Page Application like React or Angular for your frontend. Its up to you. But however you proceed, you will probably have to use JS at some point to make AJAX calls.

- Twitter Bootstrap - The current HTML index.html is written using [Bootstrap](https://getbootstrap.com/docs/4.4/)'s styling. Bootstrap is an easy way to create a professional looking website.

## Vocab

These are terms used in this project. The following come from our Amtrak data.

1. __Agency__ - An agency is an organization that provides transportation services. Usually this is just Amtrak.

2. __Stop__ - A stop, also called a station, is a physical location where someone enters or exits a train or bus.

3. __Trip__ - A trip is an ordered set of stops, each with an arriving and departing time. A trip also has a *calendar* which details which days of the week a trip is active on.

4. __Route__ - A route represents the physical tracks a train runs on. A route has many trips. In most cases, each of a route's trips have the same stops, but there is no guarantee of this. A route is operated by an agency.

To complete this application, you will have to introduce many new terms to describe concepts in your system. I suggest a few here.

5. __Connection__ - A connection connects two trips together at a specific stop. For example, to get from AAA to CCC, you may have to take a trip from AAA to BBB, then another from BBB to CCC. We would say you have a connection at BBB.

6. __Path__ - A path describes how a customer would actually get from stop A to B. So (at a minimum) a path would include a departure date and time, a set of trips, and a set of connections.

7. __Ticket__ - A ticket is what a customer uses to board the train. A customer may require many tickets if their path has many trips.

8. __Reservation__ - A reservation is what a customer actually pays for. It may include many tickets for many people.

## Use Cases

These use cases outline what and how users will do on this website.

### User signs in

1. User selects one of many supported OAuth2 provider such as Google, Twitter, Facebook, etc.
2. System redirects user to providers site.
3. User signs in with provider's credentials.
4. Provider redirects user back to our system.

### User edits billing address

1. User selects an existing billing address or selects to create a new one.
2. User enters or updates all fields of their billing address.
3. System responds with a success or failure depending on if (1) the user completed all required fields, and (2) the fields compose a valid address.

### User edits payment information

1. User selects an existing payment method or selects to create a new one.
2. User enters or updates all relevant fields pertaining to credit card info.
3. User selects a billing address or creates a new one.
4. System responds with success or failure depending on if they entered a valid credit card.
5. (Note: We can't just store credit card info willy-nilly. We need to investigate industry best practices. Maybe see what Shopizer does?)

### User views all available destinations (the locations of stops)

1. User requests to see destinations with an optional search query.
2. System responds with a (possibly filtered) list of destinations.
3. User selects a destination.
4. System responds with all details of destination including routes that serve this destination and nearby attractions.
5. User may select to visit this destination.
6. System redirect user to the dialog for making a reservation.

### User shares location

1. System prompts user to share location. (Most likely using the browser's Geolocation API.)
2. User accepts or declines.
3. If the user accepts, the system will remember their location and suggest nearby stations during relevant dialogs.

### User makes a reservation

1. User enters their desired destination stop, source stop, and a departing date. User also specifies if they want one-way or round-trip tickets. If they want round-trip tickets, the system prompts the user to enter their date of return.
2. System responds with a list of paths that satisfy the user's request. Each path includes all constituting details such as stops, trips, connections, dates, times and total path time. (You will have to implement some kind of algorithm to find these paths. However, this is not an Algorithms class so you will not be graded on this. As long as you can find some kind of path between two stops. But if you do want to try a proper solution, consider Dijkstra's or Microsoft's [Raptor](https://www.microsoft.com/en-us/research/wp-content/uploads/2012/01/raptor_alenex.pdf).)
3. User selects their desired path along with the class of seat (coach, business, etc.) they want for each leg (trip) of their journey. If round-trip was selected, the user must select from a two lists of paths. One for each direction.
4. User enters how many passengers of each type (adult, child, infant, etc.) and any relevant discounts (student, veteran, senior, etc.). User may also enter a discount code.
5. User enters if they are checking any bicycles and if so how many.
6. System responds with a itemized list of their reservation with calculated costs on each item. User may select to go back to previous screens to edit any choices made.
7. User pays for the reservation.
    1. If the user is singed in, they can pay with saved payment info, pay with new payment info, pay with PayPal, or save the reservation to their account to pay for another time.
    2. If the user is not singed in, the system will prompt them to sign in or continue as a guest. If they continue as a guest, they can enter their payment info or pay with PayPal.
8. User confirms purchase.
9. System will attempt to complete the transaction.
    1. If the purchase was successful, the system returns a receipt and the purchased ticket(s). System also emails receipt and tickets to user.
    2. If the purchase was unsuccessful, the system returns details on why it could not complete the purchase. System prompts user to update payment information.

### User views past purchases

1. User requests to see past purchases.
2. System responds with complete list of purchases.
3. User may request to see more details for a purchase.
4. System responds with all details of a purchase including associated receipts and tickets.

### User cancels a reservation

1. User views past purchases.
2. User chooses to cancel a selected reservation.
3. System prompts user for a reason for their cancellation.
4. User chooses from a list of prefilled reasons or enters their own.
5. System saves their reason for cancelling.
6. System attempts to refund money to user.
    1. If successful, system give a success message to the user and internally voids all tickets associated with the reservation.
    2. If unsuccessful, system provides an explanation to user.

## Integration

To complete the above use cases, your system will have to integrate with many existing systems. For authorization, you will have to integrate with (multiple) OAuth2 providers. For processing payments, you will have to integrate with a credit card processor (such as Strip) and PayPal. (You could even support Venmo, cryptocurrencies, or mail-in-checks.) To provide attractions near destinations, you will need to integrate with something like Google Maps or Yelp. You should not expect these services to always to be online or fully functional. Your system should gracefully handle outages and unexpected behavior.