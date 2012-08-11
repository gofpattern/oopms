Please run use SQL SCRIPT to create 4 new table in FMS database before using projectEyePortlet

For whose have already create table from old scripte file please run this SQL to fix the column name

alter table OOPMS_COST_DAILY_EXPENSE rename column TYPE to OOPMS_COST_TYPE_ID;