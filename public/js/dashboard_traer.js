/* var registerForm=document.getElementById("Dashboard")
var boton=document.getElementById("boton")

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
		fetch("/Dashboard/enviar",datos)
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
} */

//Cuando cargue la pagina
window.onload=()=>{

	datos={
		method:'GET'
	}
	fetch("/Dashboard/traer",datos)
		.then(response =>response.json())
		.then(data=>{
			if(data.status==200){
				alert("funciono");
				const arr=["nombre","correo","pass","EDAD"];
				const arr2=[data.nombre,data.correo,data.password,data.edad];
				let i=0;
				for (const name of arr) {
					let part =document.getElementById(name);
					part.value=arr2[i++];
				}
                // registerForm.addEventListener("submit", enviar);
			}
		})
		.catch(err=>console.log('Error:',err));
}
