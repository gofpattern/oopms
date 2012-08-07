export ProjectEye_Ver=0.0.1
export RequirementPortlet_Ver=0.0.1
export PlannerPortlet_Ver=0.0.1
export TimesheetPortlet_Ver=0.0.1
export DMSPortlet_Ver=0.0.1

ant deployPortletApp -DportletApp="./DeployPortlet/ProjectEyePortlet-$ProjectEye_Ver/ProjectEyePortlet.war"
ant deployPortletApp -DportletApp="./DeployPortlet/RequirementPortlet-$RequirementPortlet_Ver/RequirementPortlet.war"
ant deployPortletApp -DportletApp="./DeployPortlet/PlannerPortlet-$PlannerPortlet_Ver/PlannerPortlet.war"
ant deployPortletApp -DportletApp="./DeployPortlet/DMSPortlet-$DMSPortlet_Ver/DMSPortlet.war"
ant deployPortletApp -DportletApp="./DeployPortlet/TimesheetPortlet-$TimesheetPortlet_Ver/TimesheetPortlet.war"