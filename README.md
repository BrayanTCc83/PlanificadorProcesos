# PlanificadorProcesos
Planificador de procesos para realizar el planificador de procesos de la materia de Sistemas Operativos.

## Sobre el proyecto
El proyecto fue desarrollado para la materia de Sistemas Operativos, consiste en un simulador de planificador de procesos haciendo uso del algoritmo de la política de planificación Round Robin, implementando un gestor de procesos y un gestor de memoria (muy rudimentario), para este fin.

## Documentación
La documentación correspondiente a este proyecto se puede encontrar en:
[Documentación](https://docs.google.com/document/d/1LmWvrZofBSCJoATmQdrziWC4EHy_jDRCppJTGdCcu9I/edit?usp=sharing)

## Previos
Para poder usar el planificador de procesos, antes de compilar y ejecutar, es necesario tener en cuenta las siguientes consideraciones.

1. La estructura del proyecto fue creada a mano, por lo que contiene archivos con instrucciones de compilación adaptadas al mismo, si se intenta abrir con programas como Eclipse o NetBeans es posible que la estructura sea incompatible.

2. Las instrucciones de compilación están descritas en el correspondiente archivo `makefile`, por tanto para poder compilarlo correctamente (compilación automática y no manual) es necesario tener instalada la herramienta [gnu make](https://www.gnu.org/software/make/manual/make.html).
- En caso de estar en un sistema Linux, es común que la herramienta ya venga instalada, de lo contrario realice la instalación con su gestor de paquetes correspondiente, para Ubuntu (o distribuciones basadas en Debian)
> sudo apt install make

- Para Fedora (o distrubuciones basadas en Red Hat)
> sudo dnf install make

- Para Arch (o distribuciones basadas en Manjaro)
> sudo pacman -S make

- En caso de trabajar con un sistema MacOS, el programa se incluye en el paquete de herramientas de Xcode, en caso de aún no tenerlo instalado puede usar:
> xcode-sellect --install

- Finalmente, si se encuentra en Windows, es recomendable trabajar con un manejador de paquetes como [Chocolately](https://chocolatey.org/), para facilitar esta y otras instalaciones posteriores, Asumiendo que se tiene chocolately instalado, es posible que requiera de las herramientas de compilación de gnu. En tal caso, installe mingw desde su terminal  como administrador:
> choco install mingw
- Una vez instalado mingw, ejecute en la terminal:
> choco install make

3. Es necesario tener Java instalado correctamente para trabajar, esto incluye el kit de desarrollo de Java y la Java virtual machine o entorno de ejecución de Java, para ello:

> [!Important]
> Es necesario revisar que la instalación de Java corresponda a la versión 22 ya que es la que ha sido empleada en este proyecto.

- Desde Ubuntu (o distribuciones basadas en Fedora)
> sudo apt install openjdk-22-jdk

- Desde Fedora (o distribuciones basadas en RedHat)
> sudo dnf install java-22-openjdk

- Desde Arch (o distribuciones basadas en Manjaro)
> sudo pacman -S jdk-openjdk

- Desde MacOS
> brew install openjdk@22

> sudo ln -sfn $(brew --prefix openjdk@22)/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk.jdk

- Desde Windows
> choco install javaruntime openjdk

## Compilar y ejecutar
### Desarrollo
En caso de no querer generar un archivo .jar, es posible simplemente probar el proyecto en modo de desarrollo, para ello es necesario ejecutar el siguiente comando:
> make run

Al usar este comando se generará una carpeta de archivos temporales .class y se ejecutará el contenido de estas. Los archivos temporales no se eliminarán, para eliminarlos, debe realizarse manualmente o bien al volver a ejecutar "make run", serán remplazados por las versiones actualizadas de los mismos.

### Producción
Para generar los archivos necesarios para producción, debe de ejecutarse el comando:
> make compile

Esté permitirá generar un .jar que puede ser transferido a cualquier dispositivo con una máquina virtual de java con la versión requerida.

Para ejecutar el jar obtenido es necesario ejecutar el siguiente comando si se encuentra desde la terminal, o bien dar doble click sobre el archivo.
> java -jar .\build\planificadorprocesos.jar

## Colaboradores
- **[Andrea Mata](https://github.com/AndreaMataRam)**
- **[Brayan Téllez](https://github.com/BrayanTCc83)**
- **[Janeth Irandy Reyes](https://github.com/IrandyR)**
- **[Jorge Arturo Romero Cervantes](https://github.com/JoarTactic)**
