
function checkValidDate(inputVal,errorEleId){
	$('#' + errorEleId).css('display','none');
if(inputVal != ''){
    var pattern=/(0|1)[0-9]\/[0-3][0-9]\/(19|20)[0-9]{2}/;
    if(!pattern.test(inputVal)){
        $('#' + errorEleId).css('display','inline');
        return false;
    } else {
        //check valid date
        var date_array = inputVal.split('/');
        var day = date_array[1];
        var month = date_array[0] - 1;
        var year = date_array[2];
        var source_date = new Date(year,month,day);
                
        if(year != source_date.getFullYear())
        {
            $('#' + errorEleId).css('display','inline');
            return false;
        }

        if(month != source_date.getMonth())
        {
            $('#' + errorEleId).css('display','inline');
            return false;
        }

        if(day != source_date.getDate())
        {
            $('#' + errorEleId).css('display','inline');
            return false;
        }
                   
        $('#' + errorEleId).css('display','none');
    }
}
return true;      
}

