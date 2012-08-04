These scripts are used to create the schema and intial data for FMS.
The scripts are fixed bugs by Open-Ones Group (http://www.open-ones.com).

Process to run the scripts:
Step 1: Prepare an account with DBA privileges

For Windows
==============================
Step 2: Check to modify the parameters in env.bat
Step 3: Execute Run_FMS_01_Schema.bat, Run_FMS_02_Package.bat, Run_FMS_03_InsertData.bat, Run_FMS_04_FMS_Fix.bat

For Linux
==============================
For Linux, parameters are declared within scripts Run_FMS_01_Schema.sh, Run_FMS_02_Package.sh, Run_FMS_03_InsertData.sh, Run_FMS_04_FMS_Fix.sh.
Check to modify the parameters within each script. Then execute Run_FMS_01_Schema.sh, Run_FMS_02_Package.sh, Run_FMS_03_InsertData.sh, Run_FMS_04_FMS_Fix.sh.

Contact
thachln@gmail.com