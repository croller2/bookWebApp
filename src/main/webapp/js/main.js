/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// A $( document ).ready() block.
$( document ).ready(function() {
    
  $('#authList').change(function(){
    if ($('#authList :checkbox:checked').length > 0){
        $('#updateAuthor').prop("disabled",false);
        console.log('enabled button');
    }
    else{
        $('#updateAuthor').prop("disabled",true);
        console.log('disabled button');
    }  
  });
  $('#addAuthorForm:input').change(function(){
    if ($('#authorName').val().length > 0){
        $('#addAuthorSubmit').prop("disabled",false);
    }else{
        $('#addAuthorSubmit').prop("disabled",true);
    }       
  });
});