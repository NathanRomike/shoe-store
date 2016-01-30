# Shoe Store - Java

##### 1.29.2016.

#### By **Nathan Romike**

## Description

> This application is to test my knowledge of building databases in java. Review my work and tell me what you think. Lets make awesome stuff together!

## Setup

* Clone repository to local machine.
* Start the database system with terminal command `$ postgres`, leave this window open and runnining.
* In a new terminal window run `$ psql`
* Within psql enter `=# CREATE DATABASE hair_salon;`
* In a new terminal within the hair_salon directory enter `$ psql hair_salon < hair_salon.sql`
* Return to the terminal running psql and enter `=# CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;`
* Compile the web application in terminal by entering `$ gradle run`, and navigate to [localhost](http://localhost:4567/).

## Technologies Used

* HTML
* CSS
* Bootstrap
* Java
* Velocity
* Spark
* FluentLenium

### Legal

Copyright (c) 2016 **_Nathan Romike_**

This software is licensed under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
