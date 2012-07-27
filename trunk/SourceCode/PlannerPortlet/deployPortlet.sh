export PORTAL_HOME=/home/thachle/jPackages/uPortal-4.0.5-quick-start/uPortal-4.0.5
export WAR_FILEPATH=/home/thachle/Projects/Open-Ones/oopms/trunk/SourceCode/DMSPortlet/dist/DMSPortlet-0.0.1/DMSPortlet.war

cd $PORTAL_HOME
ant deployPortletApp -DportletApp="$WAR_FILEPATH"
