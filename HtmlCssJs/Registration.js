function validate() {
    var fullName = document.forms["form"]["fullName"].value;
    var username = document.forms["form"]["userName"].value;
    var email =document.forms["form"]["email"].value;
    var phone = document.forms["form"]["phone"].value;
    var password = document.forms["form"]["password"].value;
    var confirm = document.forms["form"]["confirm"].value;
    var gender = document.forms["form"]["gender"].value;
    if (fullName == "") {
      alert("FullName is empty");
    }
    else if(username==""){
        alert("username is empty");
    }
    else if(email==""){
        alert("email is empty");
    } 
    else if(phone==""){
        alert("phone number is empty");
    }
    else if(password==""){
        alert("password is empty");
    }
    else if(confirm==""){
        alert("confirm password is empty");
    }
    else if(gender==""){
        alert("gender is empty")
    }
    else{
        alert("submitted");
    }
}