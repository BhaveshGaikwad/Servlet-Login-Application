/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
    function validateform() {
      
         if( document.myForm1.password.value != document.myForm1.password2.value) {
            alert( "Password did not match" );
            document.myForm.password2.focus() ;
            return false;
         }
           return( true );
      }

