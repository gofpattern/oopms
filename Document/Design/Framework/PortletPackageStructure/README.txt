This is a skeleton directory structure for a Spring MVC based portlet. It includes the needed
directory structure, build script, base set of libraries and Eclipse project configuration for
starting a portlet.

********** Project Structure **********
The structure of this project follows the Maven Web Application project structure recommendations

1. Libraries
    lib/compile - libraries only needed at compile time but are not deployed with the portlet.
                    ex: servlet API and portlet API
    lib/runtime - libraries needed for compilation and runtime that are deployed with the portlet,
                  most libraries fall into this location.
    lib/test    - libraries needed for running unit tests. These are not deployed with the portlet.

2. Source
    src/main/java       - Java source files used at runtime 
    src/main/resources  - non-Java files that need to be on the classpath at runtime
    src/test/java       - Java source files used for unit tests 
    src/test/resources  - non-Java files that need to be on the classpath for unit tests

3. Spring Configuration
    src/main/webapp/WEB-INF/context/applicationContext.xml - The root Spring application context.
        DAOs, buisness logic and controllers should all be declared in this file.
    
    src/main/webapp/WEB-INF/context/portlet/ - Per-portlet Spring contexts go here. There should be
        one file for each <portlet> definition in the portlet.xml. A handler-mapping with the
        nessesary controllers should be defined in each of these files.

4. JSPs
    src/main/webapp/WEB-INF/jsp - All JSPs used when rendering the application are placed under
        this directory.
        
5. Web Resources
    src/main/webapp/css - CSS files
    src/main/webapp/js  - JavaScript files
    src/main/webapp/img - Images


********** Getting Started **********
1. Update the name of your portlet. There are several files that need to be updated with the
    name of your portlet.
    
    -build.xml
    -build.properties
    -src/main/webapp/WEB-INF/web.xml
    -src/main/webapp/WEB-INF/portlet.xml
    
2. Rename this file to match the portlet application context setting in portlet.xml
    -src/main/webapp/WEB-INF/context/portlet/skeletonPortletContext.xml
