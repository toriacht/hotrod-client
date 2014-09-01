brian-webapp:  A simple POC Hot Rod client for Infinispan 
======================================================
Author: xxx   
Technologies: Infinispan, JBoss, JEE   
Summary: Demonstrates hot rod client  
Target Product: JBoss   
Source: <https://github.com/toriacht/hotrod-cache-client/>  

What is it?
-----------

A simple POC that demonstrates a Hot Rod client to a remote Infinispan Cache. It provides a REST interface.


Notes
-------------------

Cache IP is hardcoded, but can be chnaged via REST, GET {{Host}}/brian-webapp/rest/cache/set?ip=x.x.x.x

 
Environment Requirements
-------------------------

1. Running HotRod Server
2. JBoss 7.X


Build and Deploy 
-------------------------

2. Build .war using mvn clean install
3. Deploy .war to JBoss (or any JEE container)
4. Invoke via REST (see below)


Use/Test
---------------

A simple Postman test suite available here <https://www.getpostman.com/collections/2494879fe58870bac16b>
Postman can be downloaded here <https://www.getpostman.com/>






