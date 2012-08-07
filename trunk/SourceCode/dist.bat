SET DEST=OOPMS-Quickstart/OOPMS-4.0.5-quick-start/uPortal-4.0.5/DeployPortlet
SET PRJ=PlannerPortlet
cd %PRJ%
call ant dist
cd ..
xcopy %PRJ%/dist %DEST%

SET PRJ=TimesheetPortlet
cd %PRJ%
call ant dist
cd ..
xcopy %PRJ%/dist %DEST%

SET PRJ=DMSPortlet
cd %PRJ%
call ant dist
cd ..
xcopy %PRJ%/dist %DEST%

SET PRJ=ProjectEyePortlet
cd %PRJ%
call ant dist
cd ..
xcopy %PRJ%/dist %DEST%

SET PRJ=RequirementPortlet
cd %PRJ%
call ant dist
cd ..
xcopy %PRJ%/dist %DEST%
