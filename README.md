# Login Page Demo for DXC technology

This repository contains both the front and backend of the login application assessment requested.
The demo can be accessed at the following URL: https://phenomenal-pony-11b2ff.netlify.app/

## Demo Features
- Login functionality
- Session stored in a cookie
- Single page application
- Welcome page shows username, fullname and roles
- Managers are able to view a list of users that are under them
- Http Basic security
- Supports en and zh locales. Defaults to either en or zh depending on user browser locale, else it defaults to en.
- locales can be swapped using buttons
- Manager account credentials(username:admin, password:password)
- Non-Admin account credentials(username:notAdmin, password:password)

## Technology used
- Spring boot 2.7.6: Java backend framework
    - Spring Security
    - Spring Session
    - Spring web

- Firestore: NoSQL DB
- Netlify: Frontend hosting
- GCP App engine: Backend hosting
- Axios: REST API requests
- Vue: Javascript frontend framework

## Languages used
- Java:Backend
- Javascript/CSS/HTML/Vue: Frontend
