
$(document).ready(function() {  

 $( "#datepicker" ).datepicker({
      changeMonth: true,
      changeYear: true,
      dateFormat: 'yy-mm-dd',

    });
    
 $("#category").change(function(){
	 var selctedValue = $(this).val();

	 if(selctedValue !=0){
		 $.ajax({  
		        type : "post",   
		        url : jsContextpath+"/expense-subcategories.html",   
		        data : "expenseCategoryId=" + $(this).val(),   
		        success : function(response) {  
		        	var list = jQuery.parseJSON( response );
		        	var html = "";
		        	 $.each( list.subcategories, function( key, value ) {
		        		//alert(key+ value.id + value.name);
		 				html += '<option value="' + value.id  + '">'
		 							+ value.name + '</option>';
		 				html += '</option>';
		        		});
		        	 $('#subCategory').html(html);
		        	
		        },  
		        error : function(e) {  
		         alert('Error: ' + e);   
		        }  
		       });
	 }
	   
 });
 
        
});
