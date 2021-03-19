 function validate() {
      
         if( document.myForm.First_Name.value == "" ) {
            alert( "Please provide your First Name!" );
            document.myForm.First_Name.focus() ;
            return false;
         }
         if( document.myForm.Middle_Name.value == "" ) {
            alert( "Please provide your Middle Name!" );
            document.myForm.Middle_Name.focus() ;
            return false;
         }
         if( document.myForm.Last_Name.value == "" ) {
            alert( "Please provide your Last Name!" );
            document.myForm.Last_Name.focus() ;
            return false;
         }
         if( document.myForm.Email_ID.value == "" ) {
            alert( "Please provide your Email!" );
            document.myForm.EMail.focus() ;
            return false;
         }
         if( document.myForm.Mobile_Number.value == "" ) {
            alert( "Please provide your Email!" );
            document.myForm.Mobile_Number.focus() ;
            return false;
         }
         if( document.myForm.Mobile_Number.value == "" ) {
            
            alert( "Please provide a Mobile Number." );
            document.myForm.Mobile_Number.focus() ;
            return false;
         }
         if( document.myForm.Birth_Date.value == "" ) {
            
            alert( "Please provide a Date of Birth Number." );
            document.myForm.Birth_Date.focus() ;
            return false;
         }
         if( document.myForm.City_Name.value == "" ) {
            alert( "Please provide your City Name!" );
            document.myForm.City_Name.focus() ;
            return false;
         }
         if( document.myForm.State_Name.value == "" ) {
            alert( "Please provide your State Name!" );
            document.myForm.State_Name.focus() ;
            return false;
         }
         
         if( document.myForm.Country_Name.value == "" ) {
            alert( "Please provide your Country Name!" );
            document.myForm.Country_Name.focus() ;
            return false;
         }
         return( true );
      }




