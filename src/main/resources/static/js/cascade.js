$(document).ready(function() {
	   $("#module").change(function() {
	      var moduleId = $(this).val();
	      var s = '<option value=' + -1 + '>SELECT</option>';
	      if (moduleId > 0) {
	      	$.ajax({
	        url : 'listmatieres',
	        data : { "moduleId" : moduleId },
	        success : function(result) {
	        	var result = JSON.parse(result);
	        	for (var i = 0; i < result.length; i++) {
	        	  s += '<option value="' + result[i][0] + '">'+ result[i][1]+ '</option>';
	        	}
	        	$('#matieres').html(s);
	        }
	      });
	     }
	     //reset data
	     $('#matieres').html(s);
	    

	   });
