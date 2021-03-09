/**
 * 
 */
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

const enviar=(e)=>{
	e.preventDefault();
	if(registerForm.checkValidity()){
		var form=new FormData(registerForm);
		var datos={
			method:"POST",
			body:form
		}
		fetch("https://registroweb2.herokuapp.com/Registro",datos)
		.then(response =>response.json())
		.then(data=>{
			if(data.status==200)
				alert("funciono")
			else
				alert("no funciono")
		})
		.catch(err=>console.log('Error:',err));
	}else 
		alert("ocurrio un problema");
}

registerForm.addEventListener("submit", enviar);