This is a prototype of DMS Portlet using Spring MVC framework

Features:
+ Display the login screen
+ Display Login form if non-authenticated.
  If authenticated user, display screen "/DMSPortlet/WebContent/WEB-INF/jsp/ViewDefectList.jsp"
+ Setting authenticated user for development environment is "pham.nguyen.truong.giang"
+ Demo preparing data for screen: text, combo-box (Project)
+ Process hyperlinks to go to screen: ViewAllDefect, ViewOpenDefect, ViewLeadkageDefect
  Demo using hyperlinks with parameters
+ Process event "Add New" to display screen "Add new defect"
  Demo using renderUrl for button
  Load some initial data for "Add New Defect"
  Process button "Defect List" to go back the screen "Defect List2"
+ Add screen Batch Update Defect

How does Authentication work?
================================
In development environment (Eclipse + Glashfish + Open Portlet Container), logon user is declared in file /DMSPortlet/resource/dev.properties.
If username is "guest" or BLANK, it mean non-authentication.

Refer:
- http://www.open-ones.com/ishare/posts/list/41.page
- http://www.open-ones.com/ishare/posts/list/54.page