/*
Time and date.
*/
var months = new Array(12);
months[0] = "Jan";
months[1] = "Feb";
months[2] = "Mar";
months[3] = "Apr";
months[4] = "May";
months[5] = "Jun";
months[6] = "Jul";
months[7] = "Aug";
months[8] = "Sep";
months[9] = "Oct";
months[10] = "Nov";
months[11] = "Dec";

var days = new Array(7);
days[0] = "Sun";
days[1] = "Mon";
days[2] = "Tue";
days[3] = "Wed";
days[4] = "Thu";
days[5] = "Fri";
days[6] = "Sat";

var now = new Date();
var day = days[now.getDay()];
var date = now.getDate().toString();
if (date.length < 2) {
    date = "0" + date;
}
var month = months[now.getMonth()];
var year = now.getYear();
if (year < 2000) {
    year = year + 1900;
}
var toDay = day + ", " + date + " " + month +  " " + year +" ";