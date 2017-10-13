

/******************************************************************************************************************
 * @Author  imran
 * @Created Dec 14, 2014
 * This function used for required field validator on textbox, textarea 
 * Recieve three argument --> < fieldId    : Id of the html textbox, textarea (string)
 *                            < errorDivId : Id of the html element where you want to show the error message(string)
 *                            < message    : Error message   (string)
 ******************************************************************************************************************/
function RequiredFieldTextBox(fieldId,errorDivId,message)
{
    var errorDiv=$("#body #"+errorDivId);
    var textBox=$("#body #"+fieldId);
    errorDiv.html("");
    if($.trim(textBox.val())=='')
    {
        //errorDiv.style.color="red";
        errorDiv.html(message);
        textBox.focus();
        return false;
    }
    else
    {
        errorDiv.html("");
        return true;
    }	
}


/*******************************************************************************************************************
 * @Author  imran
 * @Created Dec 14, 2014
 * This function used for Email validation
 * Recieve threee argument -->< fieldId    : Id of the html combobox (string)
 *                            < errorDivId : Id of the html element where you want to show the error message(string)
 *                            < message    : Error message   (string)
 *                             
 ********************************************************************************************************************/
function EmailValidation(fieldId,errorDivId,message)
{	
	var errorDiv=$("#body #"+errorDivId);
	var email=$("#body #"+fieldId).val();
	var atpos=email.indexOf("@");
	var dotpos=email.lastIndexOf(".");
	if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length)
  	{		
            //errorDiv.style.color="red";
			$("#body #"+fieldId).focus();
            errorDiv.html(message);	
            return false;
  	}
	else
	{
		 errorDiv.html("");
                 return true;
	}
}


/***********************************************************************************************************************
 * @Author  imran khan
 * @created Sept 12, 2012
 * This function used for compare two string 
 * if not then return <false> else return <true>
 * Recieve four argument -->  < fieldId         : Id of the html input element for compare (string)
 *                            < compareFieldId  : Id of the html input element to  compare (string)
 *                            < errorDivId      : Id of the html element where you want to show the error message(string)
 *                            < message         : Error message   (string)
 ************************************************************************************************************************/
function CompareValidator(fieldId,compareFieldId,errorDivId,message)
{
	var errorDiv = $("#body #"+errorDivId);
    var field = $("#body #"+fieldId);
    var comparefield = $("#body #"+compareFieldId);
 
    errorDiv.html("");
    if(field.val()!=comparefield.val())
    {
        errorDiv.style.color = "red";
        errorDiv.html(message);
        comparefield.focus();
        return false;
    }
    else
    {
    	field.focus();
        errorDiv.html("");
        return true;
    }
}


/*******************************************************************************************************************
* @Author  imran khan
* @created Sept 12, 2012
* This function used for get the form input data in the query string format ->< "Name=satya&password=123435&Id=sat001">
* Recieve variable no of argument  <Ids of the input fields>
* Make a Query string with input data and return that query string 
*******************************************************************************************************************/
function getFormData()
{
   var data="";
   var element;
    for(var i=0; i < arguments.length; i++) 
       {
           if(i==0)
               {
                   element = document.getElementById(arguments[i]);
                   data    = arguments[i]+'='+element.value;
               }
               else
                   {
                        element = document.getElementById(arguments[i]);
                        data    = data+'&'+arguments[i]+'='+element.value;
                   }
       }
       return data;   
   
}

/*******************************************************************************************************************
 *  @Author  :  imran khan
 *  @Created :  Sept 16, 2012
 * this function prevent the user to enter the char other than integer
 * This function used for check weather the entered char is integer or not
 * if its is a integer then return the coresponding key code
 * else return nothing 
 * Recieve one argument --> < fieldId    : Id of the html input element (string)
 *******************************************************************************************************************/
function checkForInt(evt) 
{
    var charCode = ( evt.which )    ? evt.which : evt.keyCode;        
    return( (charCode >= 48 && charCode <= 57)|| charCode== 8 || charCode==9 || charCode==13 );
}



/*******************************************************************************************************************
 *  @Author  :  imran khan
 *  @Created :  Sept 16, 2012
 * this function prevent the user to enter the char other than alphabet
 * This function used for check weather the entered char is alphabet or not
 * if its is a alphabet then return the coresponding key code
 * else return nothing 
 * Recieve one argument --> < fieldId    : Id of the html element (string)
 *******************************************************************************************************************/
function checkForAlphabet(evt)
{
    var charCode = ( evt.which ) ? evt.which : evt.keyCode ;
    return((charCode >= 65 && charCode <= 90) || (charCode >= 97 && charCode <= 122) || charCode== 8 || charCode==32 || charCode==46 );
}


/*******************************************************************************************************************
 * @Author  imran khan
 * @Created Sept 06, 2012
 * This function used for validate the input charactor in the input  
 * Recieve four argument -->  < fieldId    : Id of the html combobox (string)
 *                            < errorDivId : Id of the html element where you want to show the error message(string)
 *                            < message    : Error message   (string)
 *                            < validChar  : List of all the valid char as a string(string)
 *******************************************************************************************************************/
function FixedCharTextBox(fieldId,errorDivId,message,validChar)
{
    var errorDiv=$("#body #"+errorDivId);
    var checkOK = validChar;
    var checkStr = $("#body #"+fieldId).val();
    var allValid = true;
    var allNum = "";
    for (i = 0;  i < checkStr.length;  i++)
    {
        ch = checkStr.charAt(i);
        
        for (j = 0;  j < checkOK.length;  j++)
        {   
            if (ch == checkOK.charAt(j))
                break;
        }
        
        if (j == checkOK.length)
        {
            allValid = false;
            break;
        }
        
        if (ch != ",")
        {
            allNum += ch;
        }
    }
    if (!allValid)
    {	
        errorDiv.html(message);	
        $("#body #"+fieldId).focus();
        return false;
    }
    else
    {
        errorDiv.html("");
        return true;
    }
}


/*******************************************************************************************************************
 * @author  imran khan
 * @created Sept 06, 2012
 * This function used for input length
 * Recieve five argument -->  < fieldId    : Id of the html combobox (string)
 *                            < errorDivId : Id of the html element where you want to show the error message(string)
 *                            < message    : Error message   (string)
 *                            < length     : Valid length (int)
 *                            < type       : type of checking ,must be one of the three--'MAX' 'MIN' 'EQUAL' (string)
 *                             
 ********************************************************************************************************************/
function LengthTextBox(fieldId,errorDivId,message,length,type)
{
	var errorDiv=$("#body #"+errorDivId);
	var textBox=$("#body #"+fieldId);
	switch(type)
	{
		case "MAX":
		{
			if(textBox.val().length>length)
			{			 
                            //errorDiv.style.color="red";
                            errorDiv.html(message);
                            textBox.focus();
                            return false;
			}
			else
			{
						textBox.focus();
                            errorDiv.html("");
                            return true;
			}			
		}		
		break;
		case "MIN":
		{
			if(textBox.val().length<length)
			{
			 //errorDiv.style.color="red";
			 errorDiv.html(message);
			 textBox.focus();
			 return false;
			}
			else
			{
				textBox.focus();
				errorDiv.html("");
                                return true;
			}
		}
		break;
		case "EQUAL":
		{
			if(textBox.val().length!=length)
			{
                           // errorDiv.style.color="red";
                            errorDiv.html(message);
                            textBox.focus();
                            return false;
			}
			else
			{
				textBox.focus();
				errorDiv.html("");
                                return true;
			}
		}
		break;
		default :
		{
		
		}
		break;
	//if(textbox.value.length)
    }
} 



function RequiredSelect(eleId, errorId, message){
	var ele=$("#body #"+eleId)
	var error=$("#body #"+errorId);
	error.html("");
	
	if(ele.val()=="NULL"||ele.val()==0||ele.val()==null){
		error.html(message);
		ele.focus();
		return false;
	}
	return true;
	
}




