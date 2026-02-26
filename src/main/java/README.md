===========Bus Tracking System===========

--Project Overview
The Bus Tracking System is a Spring Boot–based backend application designed to manage and monitor school/organization bus operations.

It provides features such as:
Bus management
Driver and conductor management
Student registration with QR code generation
Student bus scan tracking
Parent email notifications
Bus route and stop management
Bus live location tracking
Student fee payment management

This project was developed by Meer Murtuza Ali during his tenure as a Backend Intern at PJSoftTech.

===========Technologies Used===========
Java 17+
Spring Boot
Spring Data JPA
MySQL
Maven
Cloudinary (QR code storage)
ZXing (QR code generation)
Java Mail Sender (Email notifications)

===========Project Structure===========
bus_tracking_system
│
├── controller → REST Controllers (API endpoints)
├── service → Business logic layer
├── repository → JPA repositories
├── model → Entity classes
├── util → Utility classes (QR generation)
└── BusTrackingSystemApplication.java

===========Features & Modules===========

--Student Management
Add student
Get student by ID
Generate QR code automatically
Email parent on bus scan

--Student Scan
Scan student via QR
Store scan record
Count students in bus
Send email notification to parent

--Bus Management
Add bus
Get all buses
Get bus by ID
Get daily running buses
Update bus
Delete bus

--Driver Management
Add driver
Get driver details
Login (BCrypt password encryption)
Update driver
Delete driver

--Conductor Management
Add conductor
Get conductor details
Update conductor
Delete conductor

--Route Management
Add route
Get route details
Update route
Delete route

--Bus Stop Management
Add bus stop
Mark stop as reached
Count reached stops
Delete stop

--Bus Location Tracking
Update bus location
Get latest location
Get location history

--Bus Supplier & Service Provider
Add supplier/provider
Get details
Update
Delete

--Student Fee Payment
Pay student fees
Track payment records

===========API Base URL===========
http://localhost:64443

===========API Endpoints===========

Student APIs
Method	    Endpoint	                        Description
POST	    /student/add                        Add new student (QR generated automatically)
GET	        /student/{id}	                    Get student by ID
GET	        /student/all	                    Get all students
PUT	        /student/update/{id}	            Update student details
DELETE	    /student/delete/{id}	            Delete student

Student Scan APIs
Method	    Endpoint	                        Description
POST	    /scan/{studentId}	                Scan student QR and store record
GET	        /scan/count?busId={busId}	        Count students inside bus

Bus APIs
Method	    Endpoint	                        Description
POST	    /bus/add	                        Add new bus
GET	        /bus/all	                        Get all buses
GET	        /bus/{id}	                        Get bus by ID
GET	        /bus/daily-running	                Get running buses
PUT	        /bus/update/{id}	                Update bus details
DELETE	    /bus/delete/{id}	                Delete bus

Bus Location APIs
Method	    Endpoint	                        Description
POST	    /bus-location/update	            Update bus location
GET	        /bus-location/latest/{busId}	    Get latest bus location
GET	        /bus-location/history/{busId}	    Get location history

Bus Stop APIs
Method	    Endpoint	                        Description
POST	    /busstop/add                        Add bus stop
GET	        /busstop/bus/{busId}	            Get stops by bus ID
PUT	        /busstop/reach/{stopId}             Mark stop as reached
GET	        /busstop/count/{busId}              Count reached stops
DELETE	    /busstop/delete/{id}                Delete bus stop

Driver APIs
Method	    Endpoint                            Description
POST	    /driver/add                         Add new driver
POST	    /driver/login                       Driver login
GET	        /driver/all                         Get all drivers
GET	        /driver/{id}                        Get driver by ID
PUT	        /driver/update/{id}                 Update driver
DELETE	    /driver/delete/{id}                 Delete driver

Conductor APIs
Method	    Endpoint	                        Description
POST	    /conductor/add                      Add conductor
GET	        /conductor/all                      Get all conductors
GET	        /conductor/{id}                     Get conductor by ID
PUT	        /conductor/update/{id}              Update conductor
DELETE	    /conductor/delete/{id}              Delete conductor

Route APIs
Method	    Endpoint	                        Description
POST	    /route/add	                        Add route
GET	        /route/all	                        Get all routes
GET	        /route/{id}	                        Get route by ID
PUT	        /route/update/{id}	                Update route
DELETE	    /route/delete/{id}	                Delete route

Bus Supplier APIs
Method	    Endpoint	                        Description
POST	    /supplier/add	                    Add bus supplier
GET	        /supplier/all	                    Get all suppliers
GET	        /supplier/{id}	                    Get supplier by ID
PUT	        /supplier/update/{id}	            Update supplier
DELETE	    /supplier/delete/{id}	            Delete supplier

Service Provider APIs
Method	    Endpoint	                        Description
POST	    /service-provider/add	            Add service provider
GET	        /service-provider/all	            Get all providers
GET	        /service-provider/{id}	            Get provider by ID
PUT	        /service-provider/update/{id}	    Update provider
DELETE	    /service-provider/delete/{id}	    Delete provider

Student Fee Payment APIs
Method	    Endpoint	                        Description
POST	    /fee/pay/{studentId}	            Pay student fees
GET	        /fee/{studentId}/all	            Get fee payment history

===========Cloudinary Configuration (QR Upload)===========
Add your Cloudinary credentials in configuration.

===========Security===========
Driver passwords are encrypted using BCrypt.
No sensitive data is stored in plain text.

===========Testing===========
APIs can be tested using:
Postma

===========Deployment===========
The application is packaged as an executable JAR and can be deployed to:
AWS EC2
Render
Railway
DigitalOcean
Any server with Java installed

===========Developer Information===========
PJSoftTech

===========License===========
This project is developed for learning and internship purposes.

