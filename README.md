# BuyMe

BuyMe is a web application built with Spring Boot that simulates a simple auction system.

It allows users to create accounts from which they can create and post new auctions and place bids on those auctions.

This project is inspired by an assignment completed for CS336 at Rutgers University--New Brunswick.

## Technologies Used

* Spring Boot
* Spring Data
* Java
* MongoDB
* JSP (with JSTL)
* HTML + CSS
* JavaScript + jQuery (with DataTables)

## Configuration

By default, the MongoDB port is set to `localhost:27017`. To change the hostname, port number, or any other authentication settings, modify the relevant values in `src/main/resources/application.properties`.

## Building and Running

The project is a simple Maven project. To build it, navigate to the repository's directory and use the following command to receive a `jar` file.

    $ mvn clean install

To run the web server, locate the created `jar` file and run the following command.

    $ java -jar [path-to-jar-file]

While the `jar` is running, by default, you will be able to access the web application at `http://localhost:8080/`.

## License

    MIT License

    Copyright (c) 2022 Victor Chen

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
