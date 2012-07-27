Moi nguoi add them table Tasks(script ben duoi) vao DB cua FMS de chay nhe.
Table Tasks cu rename thanh Tasks_old la duoc.
CREATE TABLE
    TASKS
    (
        TASKID NUMBER NOT NULL,
        TASKNAME VARCHAR2(100),
        STATUS_ID NUMBER,
        DEVELOPERID NUMBER,
        STAGEID NUMBER,
        PRODUCT NUMBER,
        PRODUCTSIZE NUMBER,
        COMPLETENESSSTATUS NUMBER,
        STARTDATE DATE,
        PLANNEDENDDATE DATE,
        ENDDATE DATE,
        PLANNEDEFFORT NUMBER,
        ACTUALEFFORT NUMBER,
        DESCRIPTION NVARCHAR2(1000),
        PROJECTID NUMBER,
        ACTIVE NUMBER(1),
        PROCESS_ID NUMBER,
        CURRENTEFFORT NUMBER,
        PRIMARY KEY (TASKID)
    )