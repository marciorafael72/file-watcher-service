# File-Watcher-Service

File-Watcher-Service is a service with a simple objective, listen a directory and process every created or updated .dat files in that directory.

# How It Works

The idea behind file-watcher-service is to create two (or many, if you want) different instance types from the same application. Let me explain, file-watcher-service use a docker-compose to create two instances of the same application, but one instance gonna be WATCHER and another instance will be WORKER.

# How WATCHER Instance works

Watcher Instance is responsible to listen a specific directory, when this directory receive a new .dat file or a existing file was updated, the WATCHER Instance get the file path and send to a queue on rabbit. Watcher Instance also can consume Rabbit Messages to process received file path, after process this file, produces a new file with metrics of the file that was processed

# How WORKER Instance works

Worker Instance has the same code as WATCHER, the difference is WORKER ONLY consumes Rabbit Messages to process received file path.

# Difference between WATCHER and WORKER

    WATCHER -> Listen a directory and send to rabbit the file path of any new or updated file in that directory, also consumes rabbit messages to process and create a metrics of the file that was received on rabbit queue

    WORKER ->  ONLY consumes rabbit messages to process and create a metrics of the file that was received on rabbit queue.


# Flow of WATCHER and WORKER

![alt text](https://i.imgur.com/coqtIAA.png)

# Running the project

Before run the project, you should apoint on docker-compose.yml in volumes section the directory you want to listen and a directory you want save metric files, the directory must be on the HOMEPATH and should contain this structure:
        
    /data/in  -> directory to listen
    /data/out -> directory to save metrics files
  
# Running with docker-compose
  
As sayd before, the idea behind file-watcher-service is use docker structure to create multiple instances of the same application, separating by WORKERS and WATCHER, allowing to scale the processing of files between workers, so if you want more processing, just scale more WORKERS

Run application with just one WATCHER and one WORKER you just need run the docker-compose without any specific comand, so go to project directory and run:
        
```
./gradlew build
docker-compose build
docker-compose up
```

If you want scale more workers you just need run:

```
./gradlew build
docker-compose build
docker-compose up --scale worker=NUMBER OF WORKERS YOU WANT
```
