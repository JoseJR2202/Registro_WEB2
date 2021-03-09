/**
 * 
 */
var registerForm=document.getElementById("Login")
var boton=document.getElementById("boton")

const enviar=(e)=>{
	e.preventDefault();
	if(registerForm.checkValidity()){
		var form=new FormData(registerForm);
	
		fetch("http://localhost:8080/web22/Login",{
			method:"POST",
			body:form,
			mode:"no-cors",
			headers:{
				'Accept': 'application/json',
    			'Content-Type': 'application/json'
			}
		}).then(response =>{
			console.log(response.json())
		}).catch(err=>console.log(err))
	}else 
		alert("ocurrio un problema");
}

boton.addEventListener("click", enviar);