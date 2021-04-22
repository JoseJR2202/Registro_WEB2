const iniciar_sesion = document.getElementById('iniciar_sesion');
const modal_container = document.getElementById('modal_container');
const modal_container_2 = document.getElementById('modal_container_2');
const close = document.getElementById('cerrar');

//registro
iniciar_sesion.addEventListener('click', () => {
    modal_container.classList.add('show');
});

close.addEventListener('click', () => {
    modal_container.classList.remove('show');
});



//Registro
var registerForm=document.getElementById("registro")
var boton=document.getElementById("enviar")

const enviar=(e)=>{
	console.log(email)
	e.preventDefault();
	if(registerForm.checkValidity()){
		var form=new FormData(registerForm);
		let myheaders=new Headers();
		var datos={
			method:"POST",
			headers:myheaders,
			body:form
		}
		fetch("/registro",datos)
		.then(response =>response.json())
		.then(data=>{
			if(data.status==200){
				alert(data.mensaje)
			}
			else
				alert(data.mensaje);
		})
		.catch(err=>console.log('Error:',err));
	}else 
		alert("ocurrio un problema");
}

registerForm.addEventListener("submit", enviar);


//login
/* var sesionForm=document.getElementById("inicio_sesion")
var ingreso=document.getElementById("ingresar")

const iniciar=(e)=>{
	e.preventDefault();
	if(sesionForm.checkValidity()){
		var form=new FormData(sesionForm);
		let myheaders=new Headers();
		var datos={
			method:"POST",
			headers:myheaders,
			body:form
		}
		fetch("/login",datos)
		.then(response =>response.json())
		.then(data=>{
			if(data.status==200){
				alert(data.mensaje)
				window.open("localhost:8000/public/Dasboard.html","_self");
			}
			else
				alert(data.mensaje);
		})
		.catch(err=>console.log('Error:',err));
	}else 
		alert("ocurrio un problema");
}

sesionForm.addEventListener("submit", iniciar); */