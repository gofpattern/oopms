export DEST=./OOPMS-Quickstart/OOPMS-4.0.5-quick-start/uPortal-4.0.5/DeployPortlet/
export PRJ=PlannerPortlet
cd $PRJ
ant dist
cd ..
cp -R $PRJ/dist/* $DEST

export PRJ=TimesheetPortlet
cd $PRJ
ant dist
cd ..
cp -R $PRJ/dist/* $DEST

export PRJ=DMSPortlet
cd $PRJ
ant dist
cd ..
cp -R $PRJ/dist/* $DEST

export PRJ=ProjectEyePortlet
cd $PRJ
ant dist
cd ..
cp -R $PRJ/dist/* $DEST

export PRJ=RequirementPortlet
cd $PRJ
ant dist
cd ..
cp -R $PRJ/dist/* $DEST
