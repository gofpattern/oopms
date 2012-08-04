#./env.sh
export ORACLE_HOME=/usr/lib/oracle/xe/app/oracle/product/10.2.0/server
export PATH=$PATH:$ORACLE_HOME/bin

export USERNAME=FMS
export PASSWD=fms
export DBNAME=XE

sqlplus $USERNAME/$PASSWD@$DBNAME @FMS_03_InsertData.sql