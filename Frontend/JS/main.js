'use strict'
const Url="ws://localhost:5050/chat"
const username=document.getElementById("username").value.trim();
const password=document.getElementById("password").value.trim();
const email=document.getElementById("email").value.trim();

async function signUpUser(){
    const response = await fetch(`${Url}/addUser`,{
        method:'POST',
        body:JSON.stringify({username:username,password:password,status:"OFFLINE",email:email})
    });
    if(response.ok){
        window.onload = function() {
            window.location.href = "LoginPage.html";
        };
    }
    else{
        alert("User already exists");
    }
}
