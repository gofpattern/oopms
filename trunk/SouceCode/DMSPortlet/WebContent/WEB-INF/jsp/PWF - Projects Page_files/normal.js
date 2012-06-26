var gsSplit=" ";
var giDatePos=0;
var gbPadZero=false;
var giMonthMode=1;
var gbShortYear=false;

var gbAutoPos=false;
var gbPopDown=true;
var gPosOffset=[0,0];
var gbFixedPos=false;

var gbAutoClose=true;


var gMonths=["January","February","March","April","May","June","July","August","September","October","November","December"];
var gWeekDay=["Su","Mo","Tu","We","Th","Fr","Sa"];

var gBegin=[1980,1,1];
var gEnd=[2030,12,31];
var gsOutOfRange="Sorry, you may not go beyond the designated range!";
var guOutOfRange=null;

var giFirstDOW=1;

var gcCalBG="#CACACA";
var guCalBG=null;
var gcCalFrame="#CACACA";
var gsInnerTable="border=0 cellpadding=2 cellspacing=1";
var gsOuterTable=NN4?"border=1 cellpadding=3 cellspacing=0":"border=0 cellpadding=1 cellspacing=2";

var gbHideTop=false;
var giDCStyle=0;
var gsCalTitle="gMonths[gCurMonth[1]-1]+' '+gCurMonth[0]";
var gbDCSeq=true;
var gsYearInBox="i";
var gsNavPrev="<INPUT id='navPrev' type='button' value='&lt;' class='fields' onmousedown='showPrevMon()' onmouseup='stopShowMon()' onmouseout='stopShowMon();if(this.blur)this.blur()'>";
var gsNavNext="<INPUT id='navNext' type='button' value='&gt;' class='fields' onmousedown='showNextMon()' onmouseup='stopShowMon()' onmouseout='stopShowMon();if(this.blur)this.blur()'>";

var gbHideBottom=false;
var gsBottom="<span style='padding-left:5px;padding-bottom:4px;'>Today:&nbsp; <A href='javascript:void(0)' class='BottomAnchor' onclick='if(this.blur)this.blur();if(!fSetDate(gToday[0],gToday[1],gToday[2]))alert(\"You cannot select today!\");return false;' onmouseover='return true;' >"+gMonths[gToday[1]-1]+" "+gToday[2]+", "+gToday[0]+"</A></span>";

var giCellWidth=18;
var giCellHeight=14;
var giHeadHeight=14;
var giWeekWidth=14;
var giHeadTop=1;
var giWeekTop=0;

var gcCellBG="#FFFFFF";
var gsCellHTML="";
var guCellBGImg="";
var gsAction=" ";
var gsDays="dayNo";

var giWeekCol=-1;
var gsWeekHead="#";
var gsWeeks="weekNo";

var gcWorkday="black";
var gcSat="black";
var gcSatBG="#ECECEC";
var gcSun="black";
var gcSunBG="#ECECEC";

var gcOtherDay="silver";
var gcOtherDayBG=gcCellBG;
var giShowOther=2;

var gbFocus=true;
var gcToggle="CCFF00";

var gcFGToday="black";
var gcBGToday="CCFF00";
var guTodayBGImg="";
var giMarkToday=1+2;
var gsTodayTip="Today";

var gcFGSelected="black";
var gcBGSelected="#ECECEC";
var guSelectedBGImg="";
var giMarkSelected=2;
var gsSelectedTip="";

var gbBoldAgenda=false;
var gbInvertBold=false;
var gbShrink2fit=true;
var gdSelect=[0,0,0];
var giFreeDiv=0;
var gAgendaMask=[-1,-1,-1,-1,null,-1,null];

var giResizeDelay=KO3?150:50;
var gbFlatBorder=false;
var gbInvertBorder=false;
var gbShareAgenda=false;
var gsAgShared="gContainer._cxp_agenda";
var gbCacheAgenda=false;
var giShowInterval=250;