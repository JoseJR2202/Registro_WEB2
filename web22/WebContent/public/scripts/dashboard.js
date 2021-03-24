/**
 * 
 */
//boton para enviar cambio al servidor
var registerForm=document.getElementById("Dashboard");
var boton=document.getElementById("boton");


const envio=(e)=>{
	e.preventDefault();
	if(registerForm.checkValidity()){
		var form=new FormData(registerForm);
		var datos={
			method:"POST",
			body:form
		}
		fetch("http://localhost:8080/web22/Dashboard",datos)
		.then(response =>response.json())
		.then(data=>{
			if(data.status==200){
				alert("funciono el boton")
			}
			else
				alert("no funciono")
				//agregar un mensaje en el json a recibir
		})
		.catch(err=>console.log('Error:',err));
	}else 
		alert("ocurrio un problema");
};

//Cuando cargue la pagina
window.onload=()=>{

	datos={
		method:'GET'
	}
	fetch("https://registroweb2.herokuapp.com/Dashboard",datos)
		.then(response =>response.json())
		.then(data=>{
			if(data.status==200){
				alert("funciono");
				const arr=["correo","nombre","nacimiento","EDAD","Ubicacion","pass"];
				const arr2=[data.correo,data.nombre,data.nacimiento,data.edad,data.ubicacion,data.pass];
				let i=0;
				for (const name of arr) {
					let part =document.getElementById(name);
					part.value=arr2[i++];
				}
				registerForm.addEventListener("submit", envio);
			}
			else{
				alert("ingrese sesion por favor");
				window.open("https://registroweb2.herokuapp.com/web22/public/views/Login.html","_self");
			}
		})
		.catch(err=>console.log('Error:',err));
}