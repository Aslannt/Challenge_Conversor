# Challenge Conversor

Este es un proyecto de una aplicación de escritorio en Java que permite la conversión entre diferentes divisas. La interfaz gráfica está construida usando Swing, y la aplicación obtiene las tasas de cambio a través de una API de intercambio de divisas.

## Características

- Selección de moneda de origen y destino
- Conversión de cantidades entre diferentes divisas
- Interfaz gráfica intuitiva
- Resultado de la conversión mostrado en tiempo real

## Requisitos

- Java 8 o superior
- Biblioteca Gson para la manipulación de JSON (puedes descargarla de [aquí](https://github.com/google/gson))

## Instalación

1. Clona este repositorio en tu máquina local:
   ```bash
   git clone https://github.com/tu_usuario/ChallengeConversor.git
   ```
2. Navega al directorio del proyecto:
  ```bash
  cd ChallengeConversor.
  ```
3. Asegúrate de tener la biblioteca Gson en tu ruta de compilación. Puedes descargar el archivo gson-2.10.1.jar y agregarlo a tu proyecto.

##Uso

1. Compila el código:
```bash
javac -cp .;gson-2.10.1.jar CurrencyConverterGUI.java CurrencyConverter.java
```
2. Ejecuta la aplicación:
```bash
java -cp .;gson-2.10.1.jar CurrencyConverterGUI
```

##Estructura del Código
- CurrencyConverterGUI.java: Contiene la interfaz gráfica y la lógica de la interfaz de usuario.
- CurrencyConverter.java: Maneja la lógica de conversión de divisas y la obtención de tasas de cambio desde la API.
  
## Capturas de Pantalla

![image](https://github.com/Aslannt/Challenge_Conversor/assets/133821222/6500f0c6-c6c5-497a-b001-594611457518)
![image](https://github.com/Aslannt/Challenge_Conversor/assets/133821222/5744868d-056b-4815-8d5b-0613787e52ac)

## Muchas gracias por ver.

### Deivid Vanegas.
