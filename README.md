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
