
/
CREATE TABLE OOPMS_STAGE ( 
	StageID        		VARCHAR2(10) NOT NULL,
	StageName          	VARCHAR2(100) NOT NULL,
	StandardStage       VARCHAR2(100) NOT NULL,
	PlannedEndDate      DATE NOT NULL,
	RePlannedEndDate    DATE NULL,
	ActualEndDate       DATE NULL,
	Description         VARCHAR2(600) NULL,
	Active             	BOOLEAN NOT NULL,
	ProjectID           VARCHAR2(10) NOT NULL,	
	)
/

/
CREATE TABLE OOPMS_PRODUCT ( 
	ProductID        		VARCHAR2(10) NOT NULL,
	WorkProduct				VARCHAR2(20) NOT NULL,
	ProductName          	VARCHAR2(100) NOT NULL,
	PlannedSizeUnit       	VARCHAR2(100) NOT NULL,
	PlannedSize      		NUMBER NOT NULL,
	RePlannedSize    		NUMBER NULL,
	ActualSizeUnit       	NUMBER NULL,
	ActualSize				NUMBER NULL,
	Description         	VARCHAR2(600) NULL,
	Active             		BOOLEAN NOT NULL,
	ProjectID           	VARCHAR2(10) NOT NULL,	
	)
/

/
CREATE TABLE OOPMS_DELIVERABLE ( 
	ProductID        		VARCHAR2(10) NOT NULL,
	FirstCommittedDate		DATE NOT NULL,
	LastCommittedDate       DATE NULL,
	ActualDate       		DATE NULL,
	Status      			VARCHAR2 NOT NULL,
	Note    				VARCHAR2(600) NULL,	
	Active             		BOOLEAN NOT NULL,
	ProjectID           	VARCHAR2(10) NOT NULL,	
	)
/

CREATE TABLE OOPMS_RISK ( 
	RiskID              	NUMBER NOT NULL,
	CONDITION            	VARCHAR2(600) NULL,
	CONSEQUENCE          	VARCHAR2(600) NULL,
	PROB                 	NUMBER NULL,
	IMPACT_TO            	NUMBER NULL,
	UNIT                 	NUMBER NULL,
	ESTIMATED_IMPACT     	NUMBER NULL,
	MITIGATION           	VARCHAR2(600) NULL,
	CONTIGENCY_PLAN      	VARCHAR2(600) NULL,
	TRIGGER_NAME         	VARCHAR2(600) NULL,
	DEVELOPER_ID         	NUMBER NULL,
	ASSESSMENT_DATE      	DATE NULL,
	STATUS               	NUMBER NULL,
	ACTUAL_RISK_SCENARIOR	VARCHAR2(600) NULL,
	ACTUAL_ACTION        	VARCHAR2(600) NULL,
	ACTUAL_IMPACT        	VARCHAR2(300) NULL,
	PROJECT_ID           	NUMBER NULL,
	PLANNED_IMPACT       	VARCHAR2(300) NULL,
	UNPLANNED            	NUMBER NULL,
	EXPOSURE             	NUMBER NULL,
	DEVELOPER_ACC        	VARCHAR2(60) NULL,
	PROCESS_ID           	NUMBER(3,0) NULL,
	BASELINED            	NUMBER(1,0) NULL,
	SOURCE_ID            	NUMBER NULL,
	TYPE                 	NUMBER NULL,
	THRESHOLD            	VARCHAR2(600) NULL,
	MITIGATION_BENEFIT   	VARCHAR2(600) NULL,
	ACTUAL_MITIGATION    	VARCHAR2(600) NULL,
	EXPOSURE_NEW         	NUMBER NULL,
	PRIORITY             	NUMBER(1,0) NULL,
	LAST_UPDATED_DATE    	DATE NULL,
	RISK_PRIORITY        	NUMBER(2,0) NULL,
	IMPACT               	NUMBER(2,0) NULL 
	)
/
COMMENT ON TABLE OOPMS_RISK IS 'Project risks (Insight)'
/
COMMENT ON COLUMN RISK.BASELINED IS 'defined as a common risk'
/

CREATE TABLE OOPMS_ISSUE ( 
	ISSUEID    	NUMBER(10,0) NOT NULL,
	WORKUNITID 	NUMBER NOT NULL,
	DESCRIPTION	VARCHAR2(750) NOT NULL,
	STATUSID   	NUMBER(5,0) NOT NULL,
	PRIORITYID 	NUMBER(5,0) NOT NULL,
	TYPEID     	NUMBER(5,0) NOT NULL,
	OWNER      	VARCHAR2(50) NULL,
	STARTDATE  	DATE NULL,
	DUEDATE    	DATE NULL,
	COMMENTS   	VARCHAR2(750) NULL,
	CLOSEDDATE 	DATE NULL,
	REFERENCE  	VARCHAR2(400) NULL,
	CREATOR    	VARCHAR2(50) NULL,
	RISKID     	NUMBER NULL,
	PROCESS_ID 	NUMBER(3,0) NULL,
	WU_ID      	NUMBER NULL 
	)
/
COMMENT ON TABLE OOPMS_ISSUE IS '(Insight)'
/
COMMENT ON COLUMN ISSUE.WORKUNITID IS 'Owner WU'
/
COMMENT ON COLUMN ISSUE.RISKID IS 'When Issues are generated from occured risks'
/
COMMENT ON COLUMN ISSUE.WU_ID IS 'the wu the issue is related to (see PQA issues)'
/

/
CREATE TABLE OOPMS_ASSIGNMENT ( 
	ASSIGNMENT_ID        	NUMBER NOT NULL,
	PROJECT_ID           	NUMBER NOT NULL,
	DEVELOPER_ID         	NUMBER NOT NULL,
	TYPE                 	NUMBER(2,0) NOT NULL,
	BEGIN_DATE           	DATE NULL,
	END_DATE             	DATE NULL,
	USAGE                	NUMBER NULL,
	RESPONSE             	NUMBER(3,0) DEFAULT 0 NULL,
	NOTE                 	VARCHAR2(600) NULL,
	PROJECT_POSITION_CODE	VARCHAR2(100) NULL,
	TEAM_ID              	NUMBER(22,0) NULL,
	QUALIFICATION        	VARCHAR2(600) NULL 
	)
/
COMMENT ON TABLE OOPMS_ASSIGNMENT IS 'Contain importance information of project team assignments, effect to Dashboard, FI and Timesheet'
/
COMMENT ON COLUMN ASSIGNMENT.RESPONSE IS 'Responsibility'
/
COMMENT ON COLUMN ASSIGNMENT.NOTE IS 'For Insight comments'
/


CREATE TABLE OOPMS_DEVELOPER ( 
	DEVELOPER_ID       	NUMBER NOT NULL,
	NAME               	VARCHAR2(255) NOT NULL,
	GROUP_NAME         	VARCHAR2(30) NOT NULL,
	ACCOUNT            	VARCHAR2(60) NULL,
	DESIGNATION        	VARCHAR2(90) NULL,
	ROLE               	VARCHAR2(10) NULL,
	PASSWORD           	VARCHAR2(255) NULL,
	STATUS             	NUMBER NULL,
	EMAIL              	VARCHAR2(80) NULL,
	BEGIN_DATE         	DATE NULL,
	QUIT_DATE          	DATE NULL,
	ORG_POSITION_CODE  	VARCHAR2(100) NULL,
	UNIT_ID            	NUMBER(22,0) NULL,
	USER_TYPE_CODE     	VARCHAR2(100) NULL,
	LOCATION           	VARCHAR2(50) NULL,
	PHONE_NO           	VARCHAR2(16) NULL,
	ALTERNATIVE_EMAIL  	VARCHAR2(80) NULL,
	PASSWORD_HINT      	VARCHAR2(100) NULL,
	DISABLED           	NUMBER(1,0) NULL,
	PASSWORD_DATE      	DATE NULL,
	CHANGE_PW          	NUMBER(1,0) NULL,
	ACCOUNT_EXPIRED    	CHAR(1) NULL,
	ACCOUNT_LOCKED     	CHAR(1) NULL,
	CREDENTIALS_EXPIRED	CHAR(1) NULL,
	PASSWORD_BACKUP    	VARCHAR2(255) NULL,
	STAFF_ID           	VARCHAR2(60) NULL 
	)
/
COMMENT ON TABLE OOPMS_DEVELOPER IS 'User profiles, using with the whole FMS packages'
/
COMMENT ON COLUMN DEVELOPER.ROLE IS '10 binary numbers scheme'
/
COMMENT ON COLUMN DEVELOPER.EMAIL IS 'Used for email features (Call Log)'
/



CREATE TABLE OOPMS_LANGUAGE_CODE ( 
	LANG_CODE  	VARCHAR2(30) NOT NULL,
	DESCRIPTION	VARCHAR2(2000) NULL 
	)
/

CREATE TABLE OOPMS_MODULE ( 
	MODULE_ID             	NUMBER NOT NULL,
	PROJECT_ID            	NUMBER NOT NULL,
	NAME                  	VARCHAR2(500) NOT NULL,
	WP_ID                 	NUMBER NULL,
	PLANNED_DEFECT        	NUMBER NULL,
	REPLANNED_DEFECT      	NUMBER NULL,
	PLANNED_REVIEW_DATE   	DATE NULL,
	PLANNED_RELEASE_DATE  	DATE NULL,
	PLANNED_TEST_END_DATE 	DATE NULL,
	PLANNED_SIZE          	NUMBER NULL,
	REPLANNED_SIZE        	NUMBER NULL,
	ACTUAL_REVIEW_DATE    	DATE NULL,
	ACTUAL_RELEASE_DATE   	DATE NULL,
	ACTUAL_TEST_END_DATE  	DATE NULL,
	ACTUAL_SIZE           	NUMBER NULL,
	REUSE                 	NUMBER NULL,
	IS_DELIVERABLE        	NUMBER NULL,
	DELIVERY_LOCATION     	VARCHAR2(150) NULL,
	NOTE                  	VARCHAR2(1800) NULL,
	STATUS                	NUMBER NULL,
	COMMITED_DELIVERY_DATE	DATE NULL,
	ACTUAL_DELIVERY_DATE  	DATE NULL,
	CONDUCTOR             	VARCHAR2(150) NULL,
	REVIEWERS             	VARCHAR2(600) NULL,
	APPROVERS             	VARCHAR2(600) NULL,
	PLANNED_SIZE_UNIT_ID  	NUMBER NULL,
	ACTUAL_SIZE_UNIT_ID   	NUMBER NULL,
	PLANNED_SIZE_TYPE     	NUMBER NULL,
	ACTUAL_SIZE_TYPE      	NUMBER NULL,
	REPLANNED_RELEASE_DATE	DATE NULL,
	BASELINE              	VARCHAR2(150) NULL,
	BASELINE_STATUS       	NUMBER(1,0) NULL,
	BASELINE_NOTE         	VARCHAR2(600) NULL,
	REPLAN_DEFECT_RATE    	NUMBER NULL,
	NOTE_DEFECT_RATE      	VARCHAR2(300) NULL,
	REASION               	VARCHAR2(600) NULL,
	ACTION                	VARCHAR2(600) NULL,
	WORKPRODUCT_CODE      	VARCHAR2(100) NULL,
	NEW_PLAN_SIZE_TEST    	NUMBER NULL,
	NEW_PLAN_SIZE_REV     	NUMBER NULL,
	IS_DEFECT_REVIEW      	NUMBER DEFAULT 0 NULL,
	IS_DEFECT_TEST        	NUMBER DEFAULT 0 NULL 
	)
/
COMMENT ON TABLE OOPMS_MODULE IS 'Project products (Insight)'
/
COMMENT ON COLUMN MODULE.STATUS IS '1 Pending, 2 Accepted,  3 Rejected, 4 Cancelled'
/
COMMENT ON COLUMN MODULE.PLANNED_SIZE_UNIT_ID IS 'links to table Estimation mothod or table language depending on size type'
/
COMMENT ON COLUMN MODULE.ACTUAL_SIZE_UNIT_ID IS 'links to table Estimation mothod or table language depending on size type'
/
COMMENT ON COLUMN MODULE.PLANNED_SIZE_TYPE IS '1-estimation method, 0-language'
/
COMMENT ON COLUMN MODULE.ACTUAL_SIZE_TYPE IS '1-estimation method, 0-language'
/


CREATE TABLE OOPMS_PROJECT ( 
	ProjectID            	VARCHAR2(10) NOT NULL,
	Type                  	VARCHAR2 NOT NULL,
	Code                  	VARCHAR2(60) NOT NULL,
	Name                  	VARCHAR2(150) NOT NULL,
	Status 					VARCHAR2 NOT NULL,
	DirectCustomer			VARCHAR2(150) NULL,
	EndCustomer             VARCHAR2(150) NULL,
	BusinessDomain			VARCHAR2(150) NULL,
	PlannedStartDate       	DATE NOT NULL,
	PlannedEndDate			DATE NOT NULL,
	ScopeAndObjective       VARCHAR2(600) NULL,
	ProjectSize				NUMBER NULL,		
	ProjectEvaluation		NUMBER NULL,
	ModuleID				VARCHAR2(10) NOT NULL,
	CostID					VARCHAR2(10) NOT NULL
	)
/
COMMENT ON TABLE OOPMS_PROJECT IS 'Main project information, effect to all FMS tools'
/
COMMENT ON COLUMN PROJECT.START_DATE IS 'Planned'
/
COMMENT ON COLUMN PROJECT.LEADER IS 'Leader account'
/
COMMENT ON COLUMN PROJECT.BASE_FINISH_DATE IS 'Planned'
/
COMMENT ON COLUMN PROJECT.PLAN_FINISH_DATE IS 'Replanned'
/
COMMENT ON COLUMN PROJECT.BASE_EFFORT IS 'Planned'
/
COMMENT ON COLUMN PROJECT.PLAN_EFFORT IS 'Replanned'
/
COMMENT ON COLUMN PROJECT.TOTALBUG IS 'Temporary column for submitting Weelky report from Insight to Dashboard'
/
COMMENT ON COLUMN PROJECT.TOTALWEIGHTEDBUG IS 'Temporary column for submitting Weelky report from Insight to Dashboard'
/
COMMENT ON COLUMN PROJECT.TOTALREQUIREMENT IS 'Temporary column for submitting Weelky report from Insight to Dashboard'
/
COMMENT ON COLUMN PROJECT.COMMITTEDREQUIREMENT IS 'Temporary column for submitting Weelky report from Insight to Dashboard'
/
COMMENT ON COLUMN PROJECT.DESIGNEDREQUIREMENT IS 'Temporary column for submitting Weelky report from Insight to Dashboard'
/
COMMENT ON COLUMN PROJECT.CODEDREQUIREMENT IS 'Temporary column for submitting Weelky report from Insight to Dashboard'
/
COMMENT ON COLUMN PROJECT.TESTEDREQUIREMENT IS 'Temporary column for submitting Weelky report from Insight to Dashboard'
/
COMMENT ON COLUMN PROJECT.DEPLOYEDREQUIREMENT IS 'Temporary column for submitting Weelky report from Insight to Dashboard'
/
COMMENT ON COLUMN PROJECT.ACCEPTEDREQUIREMENT IS 'Temporary column for submitting Weelky report from Insight to Dashboard'
/
COMMENT ON COLUMN PROJECT.TOTALDEFECT IS 'Temporary column for submitting Weelky report from Insight to Dashboard'
/
COMMENT ON COLUMN PROJECT.TOTALWEIGHTEDDEFECT IS 'Temporary column for submitting Weelky report from Insight to Dashboard'
/
COMMENT ON COLUMN PROJECT.FATALPENDINGDEFECT IS 'Temporary column for submitting Weelky report from Insight to Dashboard'
/
COMMENT ON COLUMN PROJECT.SERIOUSPENDINGDEFECT IS 'Temporary column for submitting Weelky report from Insight to Dashboard'
/
COMMENT ON COLUMN PROJECT.MEDIUMPENDINGDEFECT IS 'Temporary column for submitting Weelky report from Insight to Dashboard'
/
COMMENT ON COLUMN PROJECT.COSMETICPENDINGDEFECT IS 'Temporary column for submitting Weelky report from Insight to Dashboard'
/
COMMENT ON COLUMN PROJECT.TOTALFATALDEFECT IS 'Temporary column for submitting Weelky report from Insight to Dashboard'
/
COMMENT ON COLUMN PROJECT.TOTALSERIOUSDEFECT IS 'Temporary column for submitting Weelky report from Insight to Dashboard'
/
COMMENT ON COLUMN PROJECT.TOTALMEDIUMDEFECT IS 'Temporary column for submitting Weelky report from Insight to Dashboard'
/
COMMENT ON COLUMN PROJECT.TOTALCOSMETICDEFECT IS 'Temporary column for submitting Weelky report from Insight to Dashboard'
/
COMMENT ON COLUMN PROJECT.SCHEDULE_STATUS IS 'Temporary column for submitting Weelky report from Insight to Dashboard'
/
COMMENT ON COLUMN PROJECT.EFFORT_STATUS IS 'Temporary column for submitting Weelky report from Insight to Dashboard'
/
COMMENT ON COLUMN PROJECT.PLAN_START_DATE IS 'Replanned'
/


CREATE TABLE OOPMS_REQUIREMENT ( 
	RequirementID 	VARCHAR2(10) NOT NULL,
	Description		VARCHAR2(200) NOT NULL,	
	Type           	VARCHAR2 NOT NULL,
	Size       		NUMBER NOT NULL,	
	Release_Notes   VARCHAR2(600) NULL,
	Document 		VARCHAR2(600) NOT NULL,
	Effort         	NUMBER NULL,	
	Created_Date    	DATE NULL,	
	Designed_Date   	DATE NULL,
	Coded_Date     	DATE NULL,
	Tested_Date    	DATE NULL,
	Deployed_Date  	DATE NULL,
	Accepted_Date  	DATE NULL,
	Cancelled_Date 	DATE NULL,
	ProjectID 		VARCHAR2(10) NOT NULL,
	Status			VARCHAR2 NOT NULL,
	Active 			BOOLEAN NOT NULL,
	RequirementCode    	VARCHAR2(100) NOT NULL,
	RequirementName		VARCHAR2(600) NOT NULL,
	Priority			NUMBER NULL,
	Due_Date	 		DATE NULL
	)
/
COMMENT ON TABLE OOPMS_REQUIREMENT IS 'Project requirements (Insight)'
/



CREATE TABLE OOPMS_TASK ( 
	TaskID     	VARCHAR2(10) NOT NULL,
	TaskName	VARCHAR2(100) NOT NULL,
	Status     	NUMBER(1,0) NOT NULL,
	AssignmentID VARCHAR(10) NULL,
	StageID		VARCHAR2(10) NULL,
	Product 	NUMBER NULL,
	ProductSize NUMBER NULL,
	CompletenessStatus NUMBER NULL,
	StartDate	DATE NULL,
	PlannedEndDate DATE NULL
	EndDate     DATE NULL,
	PlannedEffort NUMBER NULL,
	ActualEffort NUMBER NULL,
	Description	VARCHAR2(200) NULL,
	ProjectID   VARCHAR2(10) NOT NULL,
	Active      BOOLEAN NOT NULL 
	)
/
COMMENT ON TABLE OOPMS_TASK IS 'Tasks (Insight)'
/

quit