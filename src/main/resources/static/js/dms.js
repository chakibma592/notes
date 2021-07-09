window.onload = function() {
    if (window.jQuery) {  
        // jQuery is loaded  
        alert("Yeah!");
    } else {
        // jQuery is not loaded
        alert("Doesn't Work");
    }
} 
function ddVersDms2(obj) {
	  	var str = obj.toString().replace('-','');
	   	var tab = str.split('.');
		var deg = tab[0];
		var min;
		var sec;
	    if( tab[1]=='0')
	    {
	    	min='0';
	    	sec='0';
	    }
	    else
	    	{
	    	min = Number('0.'+tab[1])*60;
	 	   
		   	var str2 = min.toString();
		   	if(str2.indexOf('.99999')>0)
		   		{
		   		min=Math.round(min);
		   		min = min.toString();
		   		sec='0';
		   		}
		   	else
		   		{
		   		var tab2 = str2.split('.');
			   	min=tab2[0];
			   
			   	if(tab2[1].indexOf('0')==0) sec='0';
			   	else
			   		{
			   	 	sec = Number('0.'+tab2[1])*60;
			   		sec = sec.toString().split('.');
			   		sec=sec[0];
			   		}
		   		}
	    	}
	    
     var tabRt = new Array();
     tabRt[0]=deg;
     tabRt[1]=min;
     tabRt[2]=sec;
   
     return tabRt[0]+'°'+tabRt[1]+'\''+tabRt[2]+'"';
    }
    $(document).ready(function(){
    	var rows = $('.table tbody tr');
    	for(var i=0;i<rows.length;i++)
    		{
  				var col = $(rows[i]).find(':nth-child(3)');
  				var col2 = $(rows[i]).find(':nth-child(4)');
  				$(col).html(ddVersDms2($(col).html()));
  				$(col2).html(ddVersDms2($(col2).html()));
    		} 
    });
    function calLng()
    {
    	 var lng;
    	 var longitude_degres= $('#longitude_degres').val();  
         var longitude_minutes= $('#longitude_minutes').val(); 
         var longitude_secondes= $('#longitude_secondes').val(); 
         longitude_degres = parseFloat(longitude_degres) || 0;
         longitude_minutes = parseFloat(longitude_minutes) || 0;
         longitude_secondes = parseFloat(longitude_secondes) || 0;
         if ($('#ouest').is(':checked')) estouest = -1;
         else estouest = 1;
         lng = estouest * (longitude_degres + longitude_minutes / 60 + longitude_secondes / 3600);
         console.log("Longitude DB : "+lng);
         $('#longitude').val(lng);
    }
    
    function calLat()
    {
    	 var lat;
    	 var latitude_degres= $('#latitude_degres').val(); 
    	 var latitude_minutes= $('#latitude_minutes').val(); 
    	 var latitude_secondes= $('#latitude_secondes').val(); 
    	 latitude_degres = parseFloat(latitude_degres) || 0;
    	 latitude_minutes = parseFloat(latitude_minutes) || 0;
    	 latitude_secondes = parseFloat(latitude_secondes) || 0;
         if ($('#sud').is(':checked')) nordsud = -1;
         else nordsud = 1;  
         lat = nordsud * (latitude_degres + latitude_minutes / 60 + latitude_secondes / 3600);
         console.log("Latitude DB : "+lat);
         $('#latitude').val(lat);
    }
//    function dmsversdd() {
//      var lat;
//      var lng;
//      var latitude_degres= $('#latitude_degres').val(); 
//      var latitude_minutes= $('#latitude_minutes').val(); 
//      var latitude_secondes= $('#latitude_secondes').val(); 
//      var longitude_degres= $('#longitude_degres').val();  
//      var longitude_minutes= $('#longitude_minutes').val(); 
//      var longitude_secondes= $('#longitude_secondes').val(); 
//
//      if ($('#sud').is(':checked')) nordsud = -1;
//      else nordsud = 1;
//      if ($('#ouest').is(':checked')) estouest = -1;
//      else estouest = 1;
//     
//      latitude_degres = parseFloat(latitude_degres) || 0;
//      latitude_minutes = parseFloat(latitude_minutes) || 0;
//      latitude_secondes = parseFloat(latitude_secondes) || 0;
//      longitude_degres = parseFloat(longitude_degres) || 0;
//      longitude_minutes = parseFloat(longitude_minutes) || 0;
//      longitude_secondes = parseFloat(longitude_secondes) || 0;
//      lat = nordsud * (latitude_degres + latitude_minutes / 60 + latitude_secondes / 3600);
//      lng = estouest * (longitude_degres + longitude_minutes / 60 + longitude_secondes / 3600);
//      $('#longitude').val(lng);
//      $('#latitude').val(lat);
//      //$('form').submit();
//    }
   function ddVersDms(obj) {
	   
	   
	   	var str = obj.toString().replace('-','');
	   	var tab = str.split('.');
		var deg = tab[0];
		var min;
		var sec;
	    if( tab[1]=='0')
	    {
	    	min='0';
	    	sec='0';
	    }
	    else
	    	{
	    	min = Number('0.'+tab[1])*60;
	 	   
		   	var str2 = min.toString();
		   	if(str2.indexOf('.99999')>0)
		   		{
		   		min=Math.round(min);
		   		min = min.toString();
		   		sec='0';
		   		}
		   	else
		   		{
		   		var tab2 = str2.split('.');
			   	min=tab2[0];
			   
			   	if(tab2[1].indexOf('0')==0) sec='0';
			   	else
			   		{
			   	 	sec = Number('0.'+tab2[1])*60;
			   		sec = sec.toString().split('.');
			   		sec=sec[0];
			   		}
		   		}
	    	}
	   	
	   	
	   
	     var tabRt = new Array();
	     tabRt[0]=deg;
	     tabRt[1]=min;
	     tabRt[2]=sec;
	     return tabRt;
	    }
   $(document).ready(function(){
   ("#module").change(function(){
	    var categoryId = $(this).val();
	    $.ajax({
	        type: 'GET',
	        url: "/matieres/loadMatiereByModule/" + categoryId,
	        contentType:"application/json; charset=utf-8"
	        dataType:"json",
	        success: function(data){
	            var slctSubcat=$('#matiere'), option="";
	            slctSubcat.empty();
	            for(var i=0; i<data.length; i++){
	                option = option + "<option value='"+data[i].id + "'>"+data[i].matierename + "</option>";
	            }
	            slctSubcat.append(option);
	        },
	        error:function(){
	            alert("error");
	        }

	    });
	});
   });
  
   