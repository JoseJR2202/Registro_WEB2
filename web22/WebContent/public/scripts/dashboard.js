/**
 * 
 */
const escribir=()=>{
	let form = document.getElementById("Dashboard");
	const arr=["correo","nombre","nacimiento","EDAD","Ubicacion","pass"];
	for (const name of arr) {
		let part =document.createElement("input");
		part.id=name;
		part.type="text";
		part.value=name;
		let label=document.createElement("label");
		label.innerText=name + ": ";
		form.appendChild(label);
		form.appendChild(part);
		form.appendChild(document.createElement("br"));
	}
}

//Cuando cargue la pagina
window.onload=()=>{

	datos={
		method:'GET'
	}
	fetch("https://registroweb2.herokuapp.com/Dashboard",datos)
		.then(response =>response.json())
		.then(data=>{
			if(data.status==200){
				alert("funciono")
				let form = document.getElementById("Dashboard");
				const arr=["correo","nombre","nacimiento","EDAD","Ubicacion","pass"];
				const arr2=[data.correo,data.nombre,data.nacimiento,data.edad,data.ubicacion,data.pass];
				let i=0;
				for (const name of arr) {
					let part =document.createElement("input");
					part.id=name;
					part.type="text";
					part.value=arr2[i++];
					let label=document.createElement("label");
					label.innerText=name + ": ";
					form.appendChild(label);
					form.appendChild(part);
					form.appendChild(document.createElement("br"));
				}
			}
			else
				alert("no funciono")
		})
		.catch(err=>console.log('Error:',err));
}