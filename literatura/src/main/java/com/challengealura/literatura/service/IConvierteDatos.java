package com.challengealura.literatura.service;

//Interfaz con el metodo para convertir los datos necesarios
public interface IConvierteDatos {
    //Aqui le pasamos el json con los datos, y la clase record para guardas los datos
    <T> T obtenerDatos(String json, Class<T> clase);
}
