# AppAndroidAvanzado
## Resumen de requisitos y observaciones



  ### Todas las llamadas a red se deberán hacer con Retrofit.
  Hecho. Pero se me ha quedado 1 cosa en el tintero. Cuando hacemos el login, puede venir un String,
  o un diccionario si existe un error. Lo gestioné como string desde el principio, y al intentar hacer la gestión de errores a última hora, pues no he tenido otro
  remedio que meter un try/catch... 
  
  ### Siempre que se pueda se deberán paralelizar las corrutinas para no introducir esperas innecesarias.
  Hecho.

  ### El modelo de datos local deberá estar actualizado con el remoto, es decir, si actualizo un favorito en local, se deberá hacer una petición al remoto para incluir el cambio. Podéis realizar pruebas entre dispositivos para comprobar que se actualiza todo bien.
  Hecho.
  
  ### La base de datos local deberá realizarse con Room.
  Hecho.
  
  ### Se deberá mantener la coherencia de separación por capas según Clean. Se valorará la aplicación de todos los principios SOLID. Es necesario que se implementen las clases usando la inyección de dependencias con Hilt.
  Hecho.
  
  ### El interfaz visual es abierto y no hay ninguna limitación, quedará a decisión del alumno que componentes usar, qué información mostrar y la representación de la misma.
  Horrible y a mejorar.
  
  ### Cada alumno deberá registrar su app en la api de Google Maps.
  Hecho.
  
  ### El testing es obligatorio. Qué y cuánto probar queda a cargo del alumno pero tiene que haber por lo menos test unitarios reales, mocks y fakes.
  Un desastre y esto es lo que más me preocupa y frustra. 
  
  
 Creo que no me dejo ningún otro marrón (que yo sepa!! xD, alguno más sacarás)
 Muchas gracias por tu paciencia y disculpa el desastre!
