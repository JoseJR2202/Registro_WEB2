const express = require('express');
const path= require('path');
require('./BD/conecction');
const usuario= require('./modelos/usuarios')
const multer  = require('multer')
const upload = multer()
const passport= require('passport')
const session= require('express-session')

const app = express();

const PORT = 8000;

app.use(session({
	secret: 'clave',
	resave: true,
	saveUninitialized: true
}));
//los midlwers debe ir en el orden correcto
app.use(express.static('public'));
app.use(express.json());
app.use(express.urlencoded({extended:true}))
app.use(passport.initialize());
app.use(passport.session());


app.use((req, res, next) => {                                                                 
    res.header("Access-Control-Allow-Origin", "*");                                        
    res.header('Access-Control-Allow-Methods', "POST, GET, PUT, DELETE, OPTIONS");     
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");    
    res.header("Access-Control-Allow-Credentials", true);              
    next();        
});

app.get('/', (req,res)=>{
    res.sendFile('./public/index.html', { root: __dirname });                               
})

app.post('/registro',upload.none(), async (req,res)=>{
    const usuar= new usuario({
            correo: req.body.email,
            nombre: req.body.username,
            password: req.body.password,
            edad: null,
            total_ganado: null
        }
    )
    console.log(req.body.email)
    await usuar.save();
    console.log(req.body.email)
    res.send({"Status":200, "mensaje":"Usuario Registrado"})
    
})

app.post('/login', upload.none() ,async(req,res)=>{
    console.log(req.body.email_i)
    const cursor =await usuario.find({correo:req.body.email_i, password:req.body.password_i}, {correo:1})
    console.log(cursor);
    console.log(cursor.length);
    if(cursor.length!=0){
        req.session.correo=req.body.email_i;
        req.session.password=req.body.password_i;
        res.redirect("/Dashboard");
    }
        
    else
        res.redirect("/")
    
})

app.get('/Dashboard',(req,res)=>{
    console.log(req.session.correo)
    if(req.session.correo!=null)
        res.sendFile('./public/Dashboard.html', { root: __dirname }) 
    else     
        res.redirect("/")             
})

app.post('/Dashboard/enviar', upload.none(), async (req,res)=>{
    //si llego a este punto la sesion debe de estar activa, asi que no es necesario asegurarse
    const cursor =await usuario.findOne({correo:req.session.correo, password:req.session.password}, {_id:0})
    console.log(cursor);
    if(req.body.nombre==cursor.nombre && req.body.EDAD==cursor.edad && req.body.pass==cursor.password){
        res.send({"status":201,"mensaje":"Datos no actualizados, los datos ingresados son iguales a los encontrados en la Base de Datos"})
    }else{
        const act=await usuario.updateOne({correo:req.session.correo, password:req.session.password},
                            {edad:req.body.EDAD,nombre:req.body.nombre,password:req.body.pass})
        console.log(act);
        res.send({"status":200,"mensaje":"Datos actualizados"});
    }                   
})

app.get('/Dashboard/traer', async (req,res)=>{
    const cursor =await usuario.findOne({correo:req.session.correo, password:req.session.password}, {_id:0})
    console.log(cursor.correo)
    res.send({'status':200,'correo':cursor.correo,'nombre':cursor.nombre,'password':cursor.password,'edad':cursor.edad})                   
})


app.use((req, res) => {
  res.status(404).send({'message': 'Errorrrrr 404'});
});

app.listen(PORT, ()=>{
    console.log("corriendo")
})
