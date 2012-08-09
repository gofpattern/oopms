REM SET DRIVE=D:
SET PORTAL_HOME=/home/thachle/jPackages/uPortal-4.0.5-quick-start/uPortal-4.0.5
SET WAR_FILEPATH=DMSPortlet.war
REM /home/thachle/Projects/Open-Ones/oopms/trunk/SourceCode/DMSPortlet/dist/DMSPortlet-0.0.1/DMSPortlet.war

cd %PORTAL_HOME%
REM %DRIVE%
ant deployPortletApp -DportletApp="%WAR_FILEPATH%"
@PAUSE