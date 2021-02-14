/**
 * 
 */
 const email = document.getElementById("correo");

email.addEventListener("input", function (event) {
  if (email.validity.typeMismatch) {
    email.setCustomValidity("ingresa una dirección de correo electrónico");
  } else {
    email.setCustomValidity("");
  }
});

var registerForm=document.getElementById("registro")
var boton=document.getElementById("boton")

const enviar=()=>{
	var form=new FormData(registerForm);
	
	fetch("http://localhost:8080/web22/Registro",{
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
}

boton.addEventListener("click", enviar);