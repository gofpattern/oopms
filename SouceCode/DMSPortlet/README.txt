This is a prototype of DMS Portlet using Spring MVC framework

Features:
+ Display the login screen
+ Display Login form if non-authenticated.
  If authenticated user, display screen "/DMSPortlet/WebContent/WEB-INF/jsp/ViewDefectList.jsp"
+ Setting authenticated user for development environment is "pham.nguyen.truong.giang"
+ Demo preparing data for screen: text, combo-box (Project)

How does Authentication work?
================================
In development environment (Eclipse + Glashfish + Open Portlet Container), logon user is declared in file /DMSPortlet/resource/dev.properties.
If username is "guest" or BLANK, it mean non-authentication.

Refer:
- http://www.open-ones.com/ishare/posts/list/41.page
- http://www.open-ones.com/ishare/posts/list/54.page