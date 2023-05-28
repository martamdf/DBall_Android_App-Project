# AppAndroidAvanzado
## Resumen de requisitos y observaciones



  ### Todas las llamadas a red se deberán hacer con Retrofit.
  Hecho. En esta parte no ha habido grandes problemas. Pero se me han quedado un par de cosas en el tintero. Una de ellas, cuando hacemos el login, puede venir un String,
  o un diccionario si existe un error. Lo gestioné como string desde el principio, y al intentar hacer la gestión de errores a última hora, pues no he tenido otro
  remedio que meter un try/catch... La segunda es ver si se podría incluir de algún modo la parte del login en el NetworkModule. Como Moshi y alguna confi cambiaba, pues 
  no me he atrevido a meter mano por estar ya justa de tiempo.
  
  ### Siempre que se pueda se deberán paralelizar las corrutinas para no introducir esperas innecesarias.
  Hecho. Pero por ejemplo en la vista de detalle, creo que debería haber separado la request de las localizaciones para evitar que se retrase la muestra del detalle
  del heroe.

  ### El modelo de datos local deberá estar actualizado con el remoto, es decir, si actualizo un favorito en local, se deberá hacer una petición al remoto para incluir el cambio. Podéis realizar pruebas entre dispositivos para comprobar que se actualiza todo bien.
  Hecho. En la pantalla de detalle. En la Lista de heroes hay también un corazoncito pero es solo informativo.
  
  ### La base de datos local deberá realizarse con Room.
  Hecho.
  
  ### Se deberá mantener la coherencia de separación por capas según Clean. Se valorará la aplicación de todos los principios SOLID. Es necesario que se implementen las clases usando la inyección de dependencias con Hilt.
  Hecho a medias. Tendría que refactorizar la parte del login.
  
  ### El interfaz visual es abierto y no hay ninguna limitación, quedará a decisión del alumno que componentes usar, qué información mostrar y la representación de la misma.
  Horrible y a mejorar.
  
  ### Cada alumno deberá registrar su app en la api de Google Maps.
  Esto me acabo de dar cuenta que no es meter el api en la aplicación... Supongo que es la parte de "capar" para que la apikey solo la use nuestra app con el 
  código que metamos en la pagina de google maps developers. No lo he hecho. 
  
  ### El testing es obligatorio. Qué y cuánto probar queda a cargo del alumno pero tiene que haber por lo menos test unitarios reales, mocks y fakes.
  Un desastre y esto es lo que más me preocupa y frustra. 
  
  
  Muchas gracias por tu paciencia y disculpa el desastre!
